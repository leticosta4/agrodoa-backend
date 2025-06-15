package com.labweb.agrodoa_backend.model;

import com.labweb.agrodoa_backend.model.pessoas.Usuario;
import com.labweb.agrodoa_backend.model.Motivo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "denuncia")
@Getter
@Setter
public class Denuncia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idDenuncia;
    //adicionar o enum do status da denuncia

    @ManyToOne
    @JoinColumn(name = "idmotivo")
    private Motivo motivo; 

    @ManyToOne
    @JoinColumn(name = "conta_idconta")
    private Usuario denunciante;

    @ManyToOne
    @JoinColumn(name = "conta_idconta")
    private Usuario denunciado;
}
