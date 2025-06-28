package com.labweb.agrodoa_backend.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.labweb.agrodoa_backend.dto.AdministradorRespostaDTO;
import com.labweb.agrodoa_backend.service.pessoas.AdministradorService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@CrossOrigin(origins = "*")
public class AdministradorController {
    @Autowired
    private AdministradorService admService;

    @GetMapping({"/administradores"}) //dps expandir p /{idConta}/administradores tb
    public ResponseEntity<ArrayList<AdministradorRespostaDTO>> exibirAdms(@PathVariable(required = false) String userId) { 
        ArrayList<AdministradorRespostaDTO> listaAdmins = admService.listarTodos();
        return ResponseEntity.ok(listaAdmins);
    }
    
}
