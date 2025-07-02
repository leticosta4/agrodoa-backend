package com.labweb.agrodoa_backend.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.labweb.agrodoa_backend.model.local.Cidade;
import com.labweb.agrodoa_backend.model.local.Estado;
import com.labweb.agrodoa_backend.repository.local.CidadeRepository;
import com.labweb.agrodoa_backend.repository.local.EstadoRepository;

@RestController
@CrossOrigin(origins = "*")
public class LocalController {
    @Autowired
    EstadoRepository estadoRepo;

    @Autowired
    CidadeRepository cidadeRepo;

    @GetMapping("/estados")
    public ResponseEntity<List<String>> listarEstados() {
        List<String> nomesEstados = estadoRepo.findAll()
        .stream()
        .map(Estado::getNome)
        .collect(Collectors.toList());

        return ResponseEntity.ok(nomesEstados);
    }

    @GetMapping("/estados/{idEstado}/cidades")
    public ResponseEntity<List<String>> listarCidadesPorEstado(@PathVariable String idEstado) {
        List<String> nomesCidades = cidadeRepo.findAllByEstado_IdEstado(idEstado)
        .stream()
        .map(Cidade::getNome)
        .collect(Collectors.toList());
        
        return ResponseEntity.ok(nomesCidades);
    }

}
