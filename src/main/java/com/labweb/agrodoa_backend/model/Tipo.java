package com.labweb.agrodoa_backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
    @Column(name = "idtipo")
    private String idTipo;

    @Column(name = "nome")
    private String nome;
    
    public Tipo(String nome) {
        this.nome = nome;
    }
}
