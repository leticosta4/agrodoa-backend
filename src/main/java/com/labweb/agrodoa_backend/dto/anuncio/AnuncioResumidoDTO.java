package com.labweb.agrodoa_backend.dto.anuncio;

import com.labweb.agrodoa_backend.dto.produto.ProdutoRespostaDTO;
import com.labweb.agrodoa_backend.model.Anuncio;

import lombok.Getter;

@Getter
public class AnuncioResumidoDTO { //sem o anunciante para evitar loop de import
    private String idAnuncio;
    private String titulo;
    private String nomeArquivoFoto;
    private ProdutoRespostaDTO produto;

    public AnuncioResumidoDTO(Anuncio a) {
        this.idAnuncio = a.getIdAnuncio(); // Supondo que você tenha um getIdAnuncio()
        this.titulo = a.getTitulo();
        this.nomeArquivoFoto = a.getNomeArquivoFoto();
        this.produto = new ProdutoRespostaDTO(a.getProduto());
    }
}
