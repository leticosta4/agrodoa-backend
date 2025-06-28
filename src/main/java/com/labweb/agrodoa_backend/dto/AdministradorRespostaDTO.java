package com.labweb.agrodoa_backend.dto;

import com.labweb.agrodoa_backend.model.pessoas.Administrador;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AdministradorRespostaDTO {
    private String nome;
    private String email;
    private String github;
    private String linkedin;

    public AdministradorRespostaDTO(Administrador adm){
        this.nome = adm.getNome();
        this.email = adm.getEmail();
        this.github = adm.getGithub();
        this.linkedin = adm.getLinkedin();
    }
}
