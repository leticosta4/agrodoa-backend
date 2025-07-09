package com.labweb.agrodoa_backend.controller.contas;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.labweb.agrodoa_backend.dto.contas.administrador.AdministradorRespostaDTO;
import com.labweb.agrodoa_backend.service.contas.AdministradorService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/administradores")
public class AdministradorController {
    @Autowired
    private AdministradorService admService;

    @GetMapping
    public ResponseEntity<ArrayList<AdministradorRespostaDTO>> exibirAdms() { 
        ArrayList<AdministradorRespostaDTO> listaAdmins = admService.listarTodos(); //talvez mudar essa para devs
        return ResponseEntity.ok(listaAdmins);
    }

    //editar conta adm
    //desativar conta
    
}
