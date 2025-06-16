package com.labweb.agrodoa_backend.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "produto")
@Getter
@Setter
@NoArgsConstructor
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduto;

    @Column(name = "nome")
    private String nome;

    @Column(name = "quantidade")
    private int quantidade;

    @Column(name = "data_validade")
    private LocalDateTime dataValidade;

    @Column(name = "preco_unidade")
    private double precoUnidade;
    
    public Produto(String nome, int quantidade, LocalDateTime dataValidade, double precoUnidade) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.dataValidade = dataValidade;
        this.precoUnidade = precoUnidade;
    }
}
