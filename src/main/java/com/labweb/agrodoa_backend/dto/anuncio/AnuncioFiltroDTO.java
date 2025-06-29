package com.labweb.agrodoa_backend.dto.anuncio;

import java.math.BigDecimal;

import lombok.Getter;

@Getter
public class AnuncioFiltroDTO {
    private String nome;
    private String cidade;
    private BigDecimal precoMin;
    //private String tipo;
}
