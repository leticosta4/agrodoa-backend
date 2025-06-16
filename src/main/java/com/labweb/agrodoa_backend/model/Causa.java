package com.labweb.agrodoa_backend.model;

import java.time.LocalDateTime;

import com.labweb.agrodoa_backend.model.enums.StatusCausa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "causa")
@Getter
@Setter
@NoArgsConstructor
public class Causa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCausa;

    @Column(name = "nome")
    private String nome;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "meta")
    private double meta;

    @Column(name = "prazo")
    private LocalDateTime prazo;

    @Column(name = "nome_arquivo_foto")
    private String nomeArquivoFoto;

    @Column(name = "valor_arrecadado")
    private double valorArrecadado;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_causa")
    private StatusCausa status;
   
    public Causa(String nome, String descricao, double meta, LocalDateTime prazo, String nomeArquivoFoto,
            double valorArrecadado) {
        this.nome = nome;
        this.descricao = descricao;
        this.meta = meta;
        this.prazo = prazo;
        this.nomeArquivoFoto = nomeArquivoFoto;
        this.valorArrecadado = valorArrecadado;
        this.status = StatusCausa.ABERTA; //valor padrao
    }
}
