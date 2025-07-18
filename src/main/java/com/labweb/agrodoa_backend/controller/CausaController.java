package com.labweb.agrodoa_backend.controller;

import com.labweb.agrodoa_backend.dto.causa.*;
import com.labweb.agrodoa_backend.model.Causa;
import com.labweb.agrodoa_backend.model.enums.StatusCausa;
import com.labweb.agrodoa_backend.service.CausaService;
import com.labweb.agrodoa_backend.service.contas.ContaDetailsService;

import lombok.RequiredArgsConstructor;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/causas")
@RequiredArgsConstructor
public class CausaController {

    private final CausaService causaService;
    private final ContaDetailsService contaService;

    @GetMapping
    public ResponseEntity<List<CausaRespostaDTO>> buscarComFiltros(@RequestParam(required = false) String nome, StatusCausa statusCausa) {
        List<CausaRespostaDTO> causas = causaService.buscarComFiltros(nome, statusCausa);
        return ResponseEntity.ok(causas);
    }

    @GetMapping("/{idCausa}")
    public ResponseEntity<CausaRespostaDTO> buscarPorId(@PathVariable String idCausa) {
        CausaRespostaDTO causaDTO = causaService.buscarPorId(idCausa);
        return ResponseEntity.ok(causaDTO);
    }


    @PostMapping(value = "/criar_causa", consumes = MediaType.MULTIPART_FORM_DATA_VALUE) //o nome da rota vai mudar no front dependendo do tipo de conta
    public ResponseEntity<CausaRespostaDTO> criarCausa(
        @RequestParam String nome,
        @RequestParam String descricao,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate prazo,
        @RequestParam("nomeArquivoFoto") MultipartFile nomeArquivoFoto,
        @AuthenticationPrincipal UserDetails userDetails) {
        String idConta = contaService.findIdByEmail(userDetails.getUsername());
        CausaDTO dto = new CausaDTO();

        dto.setNome(nome);
        dto.setDescricao(descricao);
        dto.setPrazo(prazo);
   
        Causa causaSalva = causaService.criarCausa(dto, nomeArquivoFoto, idConta);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{idCausa}")
                .buildAndExpand(causaSalva.getIdCausa()) 
                .toUri();

        CausaRespostaDTO respostaDTO = new CausaRespostaDTO(causaSalva);

        return ResponseEntity.created(location).body(respostaDTO);
    }


    @PatchMapping(value = "/{idCausa}/aprovar_criacao_causa")
    public ResponseEntity<Void> aprovarCriarCausa(@PathVariable String idCausa) {
        causaService.aprovarCriarCausa(idCausa);

        return ResponseEntity.ok().build();
    }


    @PatchMapping(value = "/{idCausa}/rejeitar_criacao_causa")
    public ResponseEntity<Void> rejeitarCriarCausa(@PathVariable String idCausa) {
        causaService.rejeitarCausa(idCausa);

        return ResponseEntity.ok().build();
    }

    //se voluntariar
    @PostMapping(value = "/{idCausa}/virar_voluntario") //o nome da rota vai mudar no front dependendo do tipo de conta
    public ResponseEntity<Void> virarVoluntario(@PathVariable String idCausa, @AuthenticationPrincipal UserDetails userDetails) {
        String idUser = contaService.findIdByEmail(userDetails.getUsername());
        causaService.virarVoluntario(idUser, idCausa);
        return ResponseEntity.ok().build();
    }

    @PatchMapping(value = "/{idCausa}/concluir")
    public ResponseEntity<Void> concluirCausa(@PathVariable String idCausa, @AuthenticationPrincipal UserDetails userDetails){
        String idUser = contaService.findIdByEmail(userDetails.getUsername());
        causaService.concluirCausa(idUser, idCausa);
        return ResponseEntity.ok().build();
    }
}