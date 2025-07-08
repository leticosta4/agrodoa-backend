package com.labweb.agrodoa_backend.controller;

import com.labweb.agrodoa_backend.dto.CausaDTO;
import com.labweb.agrodoa_backend.model.Causa;
import com.labweb.agrodoa_backend.service.CausaService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

@RestController
@RequestMapping("/causas")
@RequiredArgsConstructor
public class CausaController {

    private final CausaService causaService;

    //Utiliza o pageable para reduzir o tamanho de retorno do banco
    @GetMapping
    public ResponseEntity<Page<CausaDTO>> buscarComFiltros(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) Double metaMin,
            @RequestParam(required = false) Double metaMax,
            Pageable pageable) {
        
        Page<CausaDTO> paginaDeCausas = causaService.buscarComFiltros(nome, metaMin, metaMax, pageable);
        return ResponseEntity.ok(paginaDeCausas);
    }

    @GetMapping("/{idCausa}")
    public ResponseEntity<CausaDTO> buscarPorId(@PathVariable String idCausa) {
        CausaDTO causaDTO = causaService.buscarPorId(idCausa);
        return ResponseEntity.ok(causaDTO);
    }


    //Provavelmente só adm deve poder
    @PostMapping("/criar_causa")
    public ResponseEntity<CausaDTO> criarCausa(@RequestBody CausaDTO causaDTO) {
        
        Causa causaSalva = causaService.criarCausa(causaDTO);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{idCausa}")
                .buildAndExpand(causaSalva.getIdCausa()) 
                .toUri();

        CausaDTO responseCausaDto = new CausaDTO(causaSalva);

        return ResponseEntity.created(location).body(responseCausaDto);
    }
}