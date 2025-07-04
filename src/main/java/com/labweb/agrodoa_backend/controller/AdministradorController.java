package com.labweb.agrodoa_backend.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.labweb.agrodoa_backend.dto.pessoas.administrador.AdministradorRespostaDTO;
import com.labweb.agrodoa_backend.service.pessoas.AdministradorService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/administradores") //dps expandir p /{idConta}/administradores tb
public class AdministradorController {
    @Autowired
    private AdministradorService admService;

    public ResponseEntity<ArrayList<AdministradorRespostaDTO>> exibirAdms() { 
        ArrayList<AdministradorRespostaDTO> listaAdmins = admService.listarTodos();
        return ResponseEntity.ok(listaAdmins);
    }
    
}
