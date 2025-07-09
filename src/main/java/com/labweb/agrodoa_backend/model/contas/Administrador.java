package com.labweb.agrodoa_backend.model.contas;

import java.util.List;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter; 
import lombok.NoArgsConstructor;

@Entity
@Table(name = "administrador")
@DiscriminatorValue("ADMINISTRADOR")
@PrimaryKeyJoinColumn(name = "conta_idconta") // mapeia PK+FK >> exato nome da coluna no banco
@Getter
@Setter
@NoArgsConstructor
public class Administrador extends Conta{
    private String github;
    private String linkedin;

    public Administrador(String nome, String email, String senha, String github, String linkedin) {
        super(nome, email, senha);
        this.github = github;
        this.linkedin = linkedin;
    }

    @Override
    public List<String> getRoles(){
        return List.of("ROLE_ADMINISTRADOR");
    }
}

//tem os overrides ainda