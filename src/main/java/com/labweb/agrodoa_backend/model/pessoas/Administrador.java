package com.labweb.agrodoa_backend.model.pessoas;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter; 

@Entity
@Table(name = "administrador")
@PrimaryKeyJoinColumn(name = "conta_idconta") // mapeia PK+FK >> exato nome da coluna no banco
@Getter
@Setter
public class Administrador extends Conta{
    private String github;
    private String linkedin;

    public Administrador(){} //construtor padrão JPA (usa reflexão para instanciar as entidades)

    public Administrador(String nome, String email, String senha, String github, String linkedin) {
        super(nome, email, senha);
        this.github = github;
        this.linkedin = linkedin;
    }

    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }
}
