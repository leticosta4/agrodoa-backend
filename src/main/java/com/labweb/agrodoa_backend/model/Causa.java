package com.labweb.agrodoa_backend.model;

import java.time.LocalDateTime;

import com.labweb.agrodoa_backend.model.enums.StatusCausa;

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
    private String nome;
    private String descricao;
    private double meta;
    private LocalDateTime prazo;
    private String nomeArquivoFoto;
    private double valorArrecadado;

    @Enumerated(EnumType.STRING)
    private StatusCausa status;
    //ver ainda como colocar o enum para status (concluida ou aberta)
   
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
