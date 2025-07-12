package com.labweb.agrodoa_backend.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.labweb.agrodoa_backend.dto.denuncia.AvaliacaoDenunciaDTO;
import com.labweb.agrodoa_backend.dto.denuncia.DenunciaRespostaDTO;
import com.labweb.agrodoa_backend.service.DenunciaService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/denuncias")
public class DenunciaController {

    @Autowired
    private DenunciaService denunciaService;

    @GetMapping
    public ResponseEntity<List<DenunciaRespostaDTO>> listarDenuncias() {
        List<DenunciaRespostaDTO> listaDenuncias = denunciaService.listarTodas();
        return ResponseEntity.ok(listaDenuncias);
    }

    // @PatchMapping("/{idDenuncia}/avaliar")
    @PostMapping("/{idDenuncia}/avaliar")
    public ResponseEntity<Void> avaliarDenuncia(@PathVariable String idDenuncia,@Valid @RequestBody AvaliacaoDenunciaDTO avaliacaoDTO) {
        
        denunciaService.avaliarDenuncia(idDenuncia, avaliacaoDTO);
        return ResponseEntity.ok().build(); 
    }
}
