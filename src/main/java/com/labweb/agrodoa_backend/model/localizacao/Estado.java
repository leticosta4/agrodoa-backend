package com.labweb.agrodoa_backend.model.localizacao;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "estado")
public class Estado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEstado;
    private String nome;

    public Estado(){}

    public Estado(String nome) {
        this.nome = nome;
    }

    public Long getIdEstado() {
        return idEstado;
    }
    public void setIdEstado(Long idEstado) {
        this.idEstado = idEstado;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }


}
