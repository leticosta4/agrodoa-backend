package com.labweb.agrodoa_backend.controller;

import com.labweb.agrodoa_backend.dto.causa.*;
import com.labweb.agrodoa_backend.model.Causa;
import com.labweb.agrodoa_backend.service.CausaService;

import lombok.RequiredArgsConstructor;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping
    public ResponseEntity<List<CausaRespostaDTO>> buscarComFiltros(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) Double metaMin,
            @RequestParam(required = false) Double metaMax) {
        
        List<CausaRespostaDTO> causas = causaService.buscarComFiltros(nome, metaMin, metaMax);
        return ResponseEntity.ok(causas);
    }

    @GetMapping("/{idCausa}")
    public ResponseEntity<CausaRespostaDTO> buscarPorId(@PathVariable String idCausa) {
        CausaRespostaDTO causaDTO = causaService.buscarPorId(idCausa);
        return ResponseEntity.ok(causaDTO);
    }


    //Provavelmente só adm deve poder
   @PostMapping(value = "/criar_causa", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CausaRespostaDTO> criarCausa(
        @RequestParam String nome,
        @RequestParam String descricao,
        @RequestParam double meta,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate prazo,
        @RequestParam("imagem") MultipartFile imagemFile) {
        CausaDTO dto = new CausaDTO();
        dto.setNome(nome);
        dto.setDescricao(descricao);
        dto.setMeta(meta);
        dto.setPrazo(prazo);
   
        Causa causaSalva = causaService.criarCausa(dto, imagemFile);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{idCausa}")
                .buildAndExpand(causaSalva.getIdCausa()) 
                .toUri();

        CausaRespostaDTO respostaDTO = new CausaRespostaDTO(causaSalva);

        return ResponseEntity.created(location).body(respostaDTO);
    }

    //concluir causa
}