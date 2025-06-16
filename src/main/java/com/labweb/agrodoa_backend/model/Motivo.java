package com.labweb.agrodoa_backend.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMotivo;
    private String nome;

    public Motivo(String nome){
        this.nome = nome;
    }
}
