package com.labweb.agrodoa_backend.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.labweb.agrodoa_backend.model.contas.*;

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
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getEmail(), loginDTO.getSenha()
                )
        );

        Conta contaAutenticada = (Conta) authentication.getPrincipal();
        String token = jwt.geraToken(contaAutenticada.getEmail());

        if (contaAutenticada instanceof Usuario) { //token e dados do usuário.
            UsuarioLoginDTO usuarioDados = new UsuarioLoginDTO((Usuario) contaAutenticada);
            LoginRespostaDTO respostaCompleta = new LoginRespostaDTO(token, usuarioDados);

            System.out.println("\n\nLogin de USUÁRIO: " + contaAutenticada.getEmail() + "\n\n");
            return ResponseEntity.ok(respostaCompleta);
        } else if (contaAutenticada instanceof Administrador) { //so token
            Map<String, String> tokenAdm = Map.of("token", token);
            
            System.out.println("\n\nLogin de ADMINISTRADOR: " + contaAutenticada.getEmail() + "\n\n");
            return ResponseEntity.ok(tokenAdm);
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Tipo de conta não reconhecido."); //so por segurança
    }

    //para requisições que precise estar logado add um header  =>  Authorization: Bearer {valorDoToken}
    
}
