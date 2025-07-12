package com.labweb.agrodoa_backend.controller;

import com.labweb.agrodoa_backend.dto.causa.*;
import com.labweb.agrodoa_backend.model.Causa;
import com.labweb.agrodoa_backend.service.CausaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
    @PostMapping("/criar_causa")
    public ResponseEntity<CausaRespostaDTO> criarCausa(@Valid @RequestBody CausaDTO causaDTO) {
        
        Causa causaSalva = causaService.criarCausa(causaDTO);

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