package com.labweb.agrodoa_backend.model.pessoas;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//ver ainda como modelar de acordo com o factory method


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "conta")
@Inheritance(strategy = InheritanceType.JOINED) //cria uma tabela para cada classe, com JOIN nas consultas, e faz as tables/classes filhas herdarem o mesmo id
public abstract class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //para indicar que o banco de dados deve gerar o valor da PK
    @Column(name = "idConta")
    private Long idConta;

    @Column(name = "nome")
    private String nome;

    @Column(name = "email")
    private String email;

    @Column(name = "senha")
    private String senha;

    public Conta(String nome, String email, String senha){
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }
}
