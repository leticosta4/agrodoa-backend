package com.labweb.agrodoa_backend.dto.auth;

import com.labweb.agrodoa_backend.dto.contas.usuario.UsuarioLoginDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginRespostaDTO { //saida
    private String token;
    private UsuarioLoginDTO userLogin;
}
