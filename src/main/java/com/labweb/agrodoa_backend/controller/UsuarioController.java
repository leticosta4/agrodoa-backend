package com.labweb.agrodoa_backend.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
    public ResponseEntity<ArrayList<UsuarioRespostaDTO>> exibirUsers(@RequestParam(required = false) String tipo) { 
        ArrayList<UsuarioRespostaDTO> listaUsers;

        if(tipo != null){
            listaUsers = userService.listarTodosPorTipo(tipo); 
        } else {
            listaUsers = userService.listarTodos();
        }
        return ResponseEntity.ok(listaUsers);
    }    

    @GetMapping({"/usuarios/{userId}"})
    public ResponseEntity<UsuarioRespostaDTO> exibirUserPorId(@PathVariable String userId) { 
        UsuarioRespostaDTO usuario = userService.acessarUsuarioPorId(userId);
        return ResponseEntity.ok(usuario);
    }    
}
