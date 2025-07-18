package com.labweb.agrodoa_backend.controller.contas;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;

import com.labweb.agrodoa_backend.dto.contas.administrador.AdministradorRespostaDTO;
import com.labweb.agrodoa_backend.service.contas.AdministradorService;
import com.labweb.agrodoa_backend.service.contas.ContaDetailsService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/administradores")
public class AdministradorController {
    @Autowired private AdministradorService admService;
    @Autowired private ContaDetailsService contaService;

    @GetMapping
    public ResponseEntity<ArrayList<AdministradorRespostaDTO>> exibirAdms() { 
        ArrayList<AdministradorRespostaDTO> listaAdmins = admService.listarTodos(); //talvez mudar essa para devs
        return ResponseEntity.ok(listaAdmins);
    }

    @GetMapping("/meu_perfil")
    public ResponseEntity<AdministradorRespostaDTO> buscaPorId(@AuthenticationPrincipal UserDetails userDetails) { 
        String idAdm = contaService.findIdByEmail(userDetails.getUsername());
        return ResponseEntity.ok(admService.buscaAdmPorId(idAdm));
    }

    //editar conta adm
    //desativar conta
    
}
