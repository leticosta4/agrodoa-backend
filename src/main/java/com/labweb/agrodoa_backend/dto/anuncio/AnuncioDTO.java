package com.labweb.agrodoa_backend.dto.anuncio;

import java.time.LocalDate;

import com.labweb.agrodoa_backend.model.Anuncio;
import com.labweb.agrodoa_backend.model.Produto;
import com.labweb.agrodoa_backend.model.enums.StatusAnuncio;
import com.labweb.agrodoa_backend.model.enums.TipoAnuncio;
import com.labweb.agrodoa_backend.model.local.Cidade;
import com.labweb.agrodoa_backend.model.pessoas.Usuario;

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

    private StatusAnuncio status;
    private TipoAnuncio tipo;
    private Cidade cidade;
    private Usuario anunciante; //restrição do tipo >> comando SQL - talvez tenha que mudar depois pq tem o hibrido tb
    private Produto produto;

    //na duvida se realmente precisa dessas coisas todas que coloquei ai em cima

    public Anuncio transformaParaObjeto(){ //precisa disso?
        return new Anuncio(titulo, nomeArquivoFoto, dataExpiracao, entregaPeloFornecedor, tipo, cidade, anunciante, produto);
    }
}