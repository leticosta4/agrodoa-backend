package com.labweb.agrodoa_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import com.labweb.agrodoa_backend.dto.pessoas.usuario.UsuarioRespostaDTO;
import com.labweb.agrodoa_backend.service.pessoas.UsuarioService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@CrossOrigin(origins = "*")
public class UsuarioController {
    @Autowired
    private UsuarioService userService;

    @GetMapping({"/usuarios"})  
    public ResponseEntity<List<UsuarioRespostaDTO>> listarUsuariosPorTipo(@RequestParam(required = false) String tipo){
        List<UsuarioRespostaDTO> usuarios = userService.buscarUsuarioFiltro(tipo);
        if(usuarios.isEmpty()){
             return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping({"/{userId}"})
    public ResponseEntity<UsuarioRespostaDTO> exibirUserPorId(@PathVariable String userId) { //ver ainda a diferenciação de MINHA CONTA (do user logado) e OUTRO PERFIL
        UsuarioRespostaDTO usuario = userService.acessarUsuarioPorId(userId);
        return ResponseEntity.ok(usuario);
    }    

    @DeleteMapping({"/{userId}/apagar_conta"})
    public ResponseEntity<Void> apagarContaUser(@PathVariable String userId) {
        userService.apagarPerfilUser(userId);
        return ResponseEntity.noContent().build(); // HTTP 204 No Content
    } 
    
    //editar
    //cadastrar -> vai ter o factory no service

}
