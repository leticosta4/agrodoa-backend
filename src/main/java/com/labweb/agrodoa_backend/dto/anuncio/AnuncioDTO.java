package com.labweb.agrodoa_backend.dto.anuncio;

import java.time.LocalDate;

import com.labweb.agrodoa_backend.model.Anuncio;
import com.labweb.agrodoa_backend.model.Produto;
import com.labweb.agrodoa_backend.model.contas.Usuario;
import com.labweb.agrodoa_backend.model.enums.TipoAnuncio;
import com.labweb.agrodoa_backend.model.local.Cidade;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AnuncioDTO {
    @NotBlank(message = "O título do produto é obrigatório!")
    private String titulo;

    private String nomeArquivoFoto;

    private LocalDate dataExpiracao;

    @NotNull(message = "É obrigatório informar se a entrega é feita pelo anunciante!")
    private int entregaPeloFornecedor; //talvez mudar para boolean

    @NotNull(message = "O tipo de anúncio é obrigatório!")
    private String tipoAnuncio;

    @NotBlank(message = "O ID da cidade é obrigatório!")
    private String cidadeId;

    @NotNull(message = "O ID do produto é obrigatório!")
    private String produtoId;

    //o Usuario anunciante ja vai ser a pessoa logada e esses que tem ID seria o front que mandaria

    public Anuncio transformaParaObjeto(TipoAnuncio tipo, Cidade cidade, Usuario anunciante, Produto produto){ //precisa disso? esses parametros sao temporarios
        return new Anuncio(titulo, nomeArquivoFoto, dataExpiracao, entregaPeloFornecedor, tipo, cidade, anunciante, produto);
    }
}