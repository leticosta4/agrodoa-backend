package com.labweb.agrodoa_backend.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "motivo")
@Getter
@Setter
public class Motivo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idMotivo;
    private String nome;

    public Motivo(){}

    public Motivo(String nome){
        this.nome = nome;
    }
}
