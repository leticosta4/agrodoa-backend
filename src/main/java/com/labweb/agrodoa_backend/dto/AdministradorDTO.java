package com.labweb.agrodoa_backend.dto;

import com.labweb.agrodoa_backend.model.pessoas.Administrador;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class AdministradorDTO {
    //fazendo esse mais de teste ja que não posso fazer de conta (abstrata)
    @NotBlank(message = "O campo nome é obrigatório!")
    private String nome;

    @NotBlank(message = "O campo email é obrigatório!")
    private String email;

    @NotBlank(message = "O campo senha é obrigatório!")
    private String senha;

    @NotBlank(message = "O campo github é obrigatório!")
    private String github;

    @NotBlank(message = "O campo linkedin é obrigatório!")
    private String linkedin;

    public Administrador transformaParaObjeto(){
        return new Administrador(nome, email, senha, github, linkedin);
    }
}
