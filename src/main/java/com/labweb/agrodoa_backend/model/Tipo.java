package com.labweb.agrodoa_backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tipo")
@Getter
@Setter
public class Tipo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //para indicar que o banco de dados deve gerar o valor da PK
    private Long idTipo;
    private String nome;

    public Tipo(){}
    
    public Tipo(String nome) {
        this.nome = nome;
    }
}
