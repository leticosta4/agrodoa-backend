package com.labweb.agrodoa_backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tipo")
@Getter
@Setter
@NoArgsConstructor
public class Tipo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //para indicar que o banco de dados deve gerar o valor da PK
    @Column(name = "idtipo")
    private String idTipo;

    @JoinColumn(name = "nome")
    private String nome;
    
    public Tipo(String nome) {
        this.nome = nome;
    }
}
