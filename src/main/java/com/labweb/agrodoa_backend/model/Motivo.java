package com.labweb.agrodoa_backend.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "motivo")
@Getter
@Setter
@NoArgsConstructor
public class Motivo {
    @Id
    
    @Column(name = "idmotivo")
    private String idMotivo;

    @Column(name = "nome")
    private String nome;

    public Motivo(String nome){
        this.nome = nome;
    }
}
