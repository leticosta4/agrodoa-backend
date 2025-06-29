package com.labweb.agrodoa_backend.dto.anuncio;

import java.math.BigDecimal;

public class AnuncioFiltroDTO {
    private String nome;
    private String cidade;
    private BigDecimal precoMin;
    private String tipo;


    
    public String getNome() {
        return nome;
    }
    public String getCidade() {
        return cidade;
    }
    public BigDecimal getPrecoMin() {
        return precoMin;
    }
    public String getTipo() {
        return tipo;
    }


    
}
