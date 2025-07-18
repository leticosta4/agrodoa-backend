package com.labweb.agrodoa_backend.dto.anuncio;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class AnuncioEditarDTO {
    @NotBlank(message = "O título do produto é obrigatório!")
    private String titulo;

    private String descricao;

    // private String nomeArquivoFoto;

    private LocalDate dataExpiracao;

    @NotNull(message = "É obrigatório informar se a entrega é feita pelo anunciante!")
    private int entregaPeloFornecedor; //talvez mudar para boolean

    @NotBlank(message = "O ID da cidade é obrigatório!")
    private String cidadeId;

    @NotNull(message = "O ID do produto é obrigatório!")
    private String produtoId;

    //o Usuario anunciante ja vai ser a pessoa logada e esses que tem ID seria o front que mandaria

    // public Anuncio transformaParaObjeto(Cidade cidade, Usuario anunciante, Produto produto){ //precisa disso? esses parametros sao temporarios
    //     return new Anuncio(titulo, descricao, nomeArquivoFoto, dataExpiracao, entregaPeloFornecedor, cidade, anunciante, produto);
    // }
}
