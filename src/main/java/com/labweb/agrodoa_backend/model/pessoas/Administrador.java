package com.labweb.agrodoa_backend.model.pessoas;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter; 
import lombok.NoArgsConstructor;

@Entity
@Table(name = "administrador")
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
}
