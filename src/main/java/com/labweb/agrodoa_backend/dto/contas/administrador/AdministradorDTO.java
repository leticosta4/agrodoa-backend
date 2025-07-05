package com.labweb.agrodoa_backend.dto.contas.administrador;

import com.labweb.agrodoa_backend.model.contas.Administrador;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AdministradorDTO { //talvez nem seja necessário
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
