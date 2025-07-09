package com.labweb.agrodoa_backend.controller.contas;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.labweb.agrodoa_backend.dto.contas.usuario.UsuarioDTO;
import com.labweb.agrodoa_backend.dto.contas.usuario.UsuarioRespostaDTO;
import com.labweb.agrodoa_backend.model.contas.Usuario;
import com.labweb.agrodoa_backend.service.contas.UsuarioService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService userService;

    @GetMapping
    public ResponseEntity<List<UsuarioRespostaDTO>> listarUsuariosPorTipo(@RequestParam(required = false) String tipo){
        List<UsuarioRespostaDTO> usuarios = userService.buscarUsuarioFiltro(tipo);
        if(usuarios.isEmpty()){
             return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping({"/ver_perfil/{idUser}"})
    public ResponseEntity<UsuarioRespostaDTO> exibirUserPorId(@PathVariable String idUser) { //ver ainda a diferenciação de MINHA CONTA (do user logado) e OUTRO PERFIL
        UsuarioRespostaDTO usuario = userService.acessarUsuarioPorId(idUser);
        return ResponseEntity.ok(usuario);
    }   

    @DeleteMapping({"/{idUser}/apagar_conta"})
    public ResponseEntity<Void> apagarContaUser(@PathVariable String idUser) {  //deveria ter apagar? ou só desativar?
        userService.apagarPerfilUser(idUser);
        return ResponseEntity.noContent().build(); // HTTP 204 No Content
    } 
    
    @PostMapping({"/cadastrar_usuario"})
    public ResponseEntity<UsuarioDTO> cadastrar(@RequestBody @Valid UsuarioDTO userDTO) {
        Usuario userSalvo = userService.cadastrarUsuario(userDTO);

        URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{idUser}")
            .buildAndExpand(userSalvo.getIdConta())
            .toUri();

        return ResponseEntity.created(location).body(new UsuarioDTO(userSalvo));
    }

    //editar
    //bloquear conta usuario >> para os ADMs
    //notificar usuario?
}
