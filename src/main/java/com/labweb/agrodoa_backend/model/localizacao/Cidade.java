package com.labweb.agrodoa_backend.model.localizacao;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cidade")
public class Cidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCidade;
    private String nome;
    //ver ainda como modelar com estado, local e user (e futuramente anuncio)

    public Cidade(){}

    public Cidade(String nome) {
        this.nome = nome;
    }
    public Long getIdCidade() {
        return idCidade;
    }
    public void setIdCidade(Long idCidade) {
        this.idCidade = idCidade;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

}
