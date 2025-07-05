package com.labweb.agrodoa_backend.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.labweb.agrodoa_backend.dto.contas.administrador.AdministradorRespostaDTO;
import com.labweb.agrodoa_backend.service.contas.AdministradorService;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@CrossOrigin(origins = "*")
public class AdministradorController {
    @Autowired
    private AdministradorService admService;

    @GetMapping({"/administradores"}) //dps expandir p /{idConta}/administradores tb
    public ResponseEntity<ArrayList<AdministradorRespostaDTO>> exibirAdms() { 
        ArrayList<AdministradorRespostaDTO> listaAdmins = admService.listarTodos();
        return ResponseEntity.ok(listaAdmins);
    }
    
}
