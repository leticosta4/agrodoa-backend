package com.labweb.agrodoa_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import com.labweb.agrodoa_backend.dto.pessoas.usuario.UsuarioDTO;
import com.labweb.agrodoa_backend.dto.pessoas.usuario.UsuarioRespostaDTO;
import com.labweb.agrodoa_backend.model.pessoas.Usuario;
import com.labweb.agrodoa_backend.service.pessoas.UsuarioService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService userService;

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
    
    @PostMapping({"/cadastrar_usuario"})
    public ResponseEntity<?> cadastrar(@RequestBody @Valid UsuarioDTO userDTO) {
        Usuario usuarioSalvo = userService.cadastrarUsuario(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioSalvo);
    }
    
    //editar
}
