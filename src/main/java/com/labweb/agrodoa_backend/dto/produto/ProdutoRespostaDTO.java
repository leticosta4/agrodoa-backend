package com.labweb.agrodoa_backend.dto.produto;

import java.time.LocalDate;

import com.labweb.agrodoa_backend.model.Produto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProdutoRespostaDTO { //precisa de um atributo id?
    private String nome;
    private int quantidade;
    private LocalDate dataValidade;
    private double precoUnidade;

    public ProdutoRespostaDTO(Produto p){ //precisa disso?
        this.nome = p.getNome();
        this.quantidade = p.getQuantidade();
        this.dataValidade = p.getDataValidade();
        this.precoUnidade = p.getPrecoUnidade();
    }
}
