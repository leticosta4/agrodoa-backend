package com.labweb.agrodoa_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.labweb.agrodoa_backend.config.JwtUtil;
import com.labweb.agrodoa_backend.dto.auth.LoginDTO;
import com.labweb.agrodoa_backend.dto.auth.LoginRespostaDTO;
import com.labweb.agrodoa_backend.dto.contas.usuario.UsuarioLoginDTO;
import com.labweb.agrodoa_backend.model.contas.Usuario;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtUtil jwt;

    @PostMapping("/login")
    public ResponseEntity<LoginRespostaDTO> login(@RequestBody LoginDTO loginDTO) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getEmail(), loginDTO.getSenha()
                )
        );

        Usuario userAutenticado = (Usuario) authentication.getPrincipal();

        String token = jwt.geraToken(userAutenticado.getEmail());
        UsuarioLoginDTO usuarioDados = new UsuarioLoginDTO(userAutenticado);

        System.out.println("\n\nRecebi login de: " + usuarioDados.getEmail() + "\n\n");

        return ResponseEntity.ok(new LoginRespostaDTO(token, usuarioDados));
    }

    //para requisições que precise estar logado add um header  =>  Authorization: Bearer {valorDoToken}
    
}
