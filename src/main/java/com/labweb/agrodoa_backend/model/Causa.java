package com.labweb.agrodoa_backend.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
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
    private long idCausa;
    private String nome;
    private String descricao;
    private double meta;
    private LocalDateTime prazo;
    private String nomeArquivoFoto;
    private double valorArrecadado;
    //ver ainda como colocar o enum para status (concluida ou aberta)
   
    public Causa(String nome, String descricao, double meta, LocalDateTime prazo, String nomeArquivoFoto,
            double valorArrecadado) {
        this.nome = nome;
        this.descricao = descricao;
        this.meta = meta;
        this.prazo = prazo;
        this.nomeArquivoFoto = nomeArquivoFoto;
        this.valorArrecadado = valorArrecadado;
    }
}
