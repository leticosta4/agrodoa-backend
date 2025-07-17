package com.labweb.agrodoa_backend.model;

import java.time.LocalDate;

import com.labweb.agrodoa_backend.model.enums.StatusCausa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
    @Column(name = "idcausa")
    private String idCausa;

    @Column(name = "nome")
    private String nome;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "prazo")
    private LocalDate prazo;

    @Column(name = "nome_arquivo_foto")
    private String nomeArquivoFoto;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_causa")
    private StatusCausa status;
   
    public Causa(String nome, String descricao, LocalDate prazo, String nomeArquivoFoto) {
        this.nome = nome;
        this.descricao = descricao;
        this.prazo = prazo;
        this.nomeArquivoFoto = nomeArquivoFoto;
        this.status = StatusCausa.ABERTA; //valor padrao
    }
}
