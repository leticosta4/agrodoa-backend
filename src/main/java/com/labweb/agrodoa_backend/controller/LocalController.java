package com.labweb.agrodoa_backend.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.labweb.agrodoa_backend.dto.local.CidadeDTO;
import com.labweb.agrodoa_backend.dto.local.EstadoDTO;
import com.labweb.agrodoa_backend.repository.local.CidadeRepository;
import com.labweb.agrodoa_backend.repository.local.EstadoRepository;


@RestController
@RequestMapping("/estados")
public class LocalController {
    @Autowired
    EstadoRepository estadoRepo;

    @Autowired
    CidadeRepository cidadeRepo;

    @GetMapping
    public ResponseEntity<List<EstadoDTO>> listarEstados() {
        List<EstadoDTO> estados = estadoRepo.findAll()
        .stream()
        .map(estado -> new EstadoDTO(estado.getIdEstado(), estado.getNome()))
        .collect(Collectors.toList());

        return ResponseEntity.ok(estados);
    }

    @GetMapping("/{idEstado}/cidades")
    public ResponseEntity<List<CidadeDTO>> listarCidadesPorEstado(@PathVariable String idEstado) {
        List<CidadeDTO> cidades = cidadeRepo.findAllByEstado_IdEstado(idEstado)
        .stream()
        .map(cidade -> new CidadeDTO(cidade.getIdCidade(), cidade.getNome()))
        .collect(Collectors.toList());
        
        return ResponseEntity.ok(cidades);
    }

}
