package com.labweb.agrodoa_backend.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "causa")
public class Causa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idCausa;
    private String nome;
    private String descricao;
    private double meta;
    private LocalDateTime prazo;
    private String nomeArquivoFoto;
    private double valorArrecadado;
    //ver ainda como colocar o enum para status (concluida ou aberta)
    
    public Causa(){}
    
    public Causa(String nome, String descricao, double meta, LocalDateTime prazo, String nomeArquivoFoto,
            double valorArrecadado) {
        this.nome = nome;
        this.descricao = descricao;
        this.meta = meta;
        this.prazo = prazo;
        this.nomeArquivoFoto = nomeArquivoFoto;
        this.valorArrecadado = valorArrecadado;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public double getMeta() {
        return meta;
    }
    public void setMeta(double meta) {
        this.meta = meta;
    }
    public LocalDateTime getPrazo() {
        return prazo;
    }
    public void setPrazo(LocalDateTime prazo) {
        this.prazo = prazo;
    }
    public String getNomeArquivoFoto() {
        return nomeArquivoFoto;
    }
    public void setNomeArquivoFoto(String nomeArquivoFoto) {
        this.nomeArquivoFoto = nomeArquivoFoto;
    }
    public double getValorArrecadado() {
        return valorArrecadado;
    }
    public void setValorArrecadado(double valorArrecadado) {
        this.valorArrecadado = valorArrecadado;
    }
}
