package com.labweb.agrodoa_backend.model;

import java.time.LocalDate;

import com.labweb.agrodoa_backend.model.contas.Conta;
import com.labweb.agrodoa_backend.model.enums.StatusCausa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    @Column(name = "meta_assinatura")
    private int metaAssinatura;

    private int assinaturasFeitas;

    @ManyToOne
    @JoinColumn(name = "conta_idconta")
    private Conta criador;

    public Causa(String nome, String descricao, LocalDate prazo, String nomeArquivoFoto, int metaAssinatura, Conta criador) {
        this.nome = nome;
        this.descricao = descricao;
        this.prazo = prazo;
        this.nomeArquivoFoto = nomeArquivoFoto;
        this.metaAssinatura = metaAssinatura;
        this.criador = criador;

        //valores padrao
        this.status = StatusCausa.ABERTA;
        this.assinaturasFeitas = 0;
    }
}
