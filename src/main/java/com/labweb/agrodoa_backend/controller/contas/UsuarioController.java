package com.labweb.agrodoa_backend.controller.contas;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.labweb.agrodoa_backend.dto.contas.usuario.UsuarioDTO;
import com.labweb.agrodoa_backend.dto.contas.usuario.UsuarioRespostaDTO;
import com.labweb.agrodoa_backend.model.contas.Usuario;
import com.labweb.agrodoa_backend.model.enums.SituacaoUsuario;
import com.labweb.agrodoa_backend.service.contas.UsuarioService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
    public ResponseEntity<List<UsuarioRespostaDTO>> listarUsuariosPorTipo(
        @RequestParam(required = false) String tipo,
        @RequestParam(required = false) String situacao){

        List<UsuarioRespostaDTO> usuarios = userService.buscarUsuarioFiltro(tipo, situacao);
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

    @PatchMapping({"/{idUser}/desativar_conta"})
    public ResponseEntity<Void> desativarContaUser(@PathVariable String idUser) {  //deveria ter apagar? ou só desativar?
        boolean inativo = userService.alterarSituacao(idUser, SituacaoUsuario.INATIVO);
        
        if(inativo){
            return ResponseEntity.noContent().build(); //204
        }
        return ResponseEntity.notFound().build(); //404 - acho que não precisa
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
