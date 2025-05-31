package com.labweb.agrodoa_backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

//ver ainda como modelar de acordo com o factory method


@Entity
@Table(name = "conta")
@Inheritance(strategy = InheritanceType.JOINED) //cria uma tabela para cada classe, com JOIN nas consultas, e faz as tables/classes filhas herdarem o mesmo id
public abstract class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //para indicar que o banco de dados deve gerar o valor da PK
    @Column(name = "idConta")
    private Long idConta;
    private String nome;
    private String email;
    private String senha;

    public Conta(){} //contrutor padrao JPA

    public Conta(String nome, String email, String senha){
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public Long getIdConta() {
        return idConta;
    }

    public void setIdConta(Long idConta) {
        this.idConta = idConta;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
