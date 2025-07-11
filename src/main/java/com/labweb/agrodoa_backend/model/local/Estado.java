package com.labweb.agrodoa_backend.model.local;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "estado")
@Getter
@Setter
@NoArgsConstructor
public class Estado {
    @Id
    @Column(name = "idestado")
    private String idEstado;

    @Column(name = "nome")
    private String nome;
    
    public Estado(String nome) {
        this.nome = nome;
    }

}
