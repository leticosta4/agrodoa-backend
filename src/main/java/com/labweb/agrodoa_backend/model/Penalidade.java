package com.labweb.agrodoa_backend.model;

import java.time.LocalDateTime;

import com.labweb.agrodoa_backend.model.pessoas.Usuario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "penalidade")
@Getter
@Setter
@NoArgsConstructor
public class Penalidade {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idpenalidade")
    private String idPenalidade;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Column(name = "motivo")
    private String motivo;

    @Column(name = "data_aplicacao")
    private LocalDateTime dataAplicacao;

    @Column(name = "avaliada")
    private boolean avaliada;

    public Penalidade(Usuario usuario, String motivo) {
        this.usuario = usuario;
        this.motivo = motivo;
        this.dataAplicacao = LocalDateTime.now();
        this.avaliada = false;
    }
}