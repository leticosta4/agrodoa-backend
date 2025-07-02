package com.labweb.agrodoa_backend.dto.produto;

import java.time.LocalDate;

import com.labweb.agrodoa_backend.model.Produto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProdutoDTO { //precisa de um atributo id?
    @NotBlank(message = "O nome do produto é obrigatório!")
    private String nome;

    @Positive(message = "A quantidade do produto deve ser um valor positivo!")
    @NotNull(message = "A quantidade do produto é obrigatório!")
    private int quantidade;

    private LocalDate dataValidade;

    @Positive(message = "O preço da unidade do produto deve ser um valor positivo!")
    @NotNull(message = "O preço da unidade do produto é obrigatório!")
    private Double precoUnidade;

    public Produto transformaParaObjeto(){ //precisa disso?
        return new Produto(nome, quantidade, dataValidade, precoUnidade);
    }
}
