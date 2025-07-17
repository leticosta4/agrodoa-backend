package com.labweb.agrodoa_backend.dto.produto;

import java.time.LocalDate;

import com.labweb.agrodoa_backend.model.Produto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProdutoRespostaDTO {
    private String idProduto;
    private String nome;
    private int quantidade;
    private LocalDate dataValidade;

    public ProdutoRespostaDTO(Produto p){
        this.idProduto = p.getIdProduto();
        this.nome = p.getNome();
        this.quantidade = p.getQuantidade();
        this.dataValidade = p.getDataValidade();
    }
}
