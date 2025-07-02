package com.labweb.agrodoa_backend.dto.anuncio;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import com.labweb.agrodoa_backend.model.enums.StatusAnuncio;
import com.labweb.agrodoa_backend.model.enums.TipoAnuncio;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class AnuncioFiltroDTO {
    private String nome;
    private String cidade;

    @NumberFormat(style = NumberFormat.Style.NUMBER)
    private BigDecimal precoMin;

    @NumberFormat(style = NumberFormat.Style.NUMBER)
    private BigDecimal precoMax;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dataExpiracao;

    private String tipo;
    private String status;

    

     public TipoAnuncio getTipoEnum() {
        try {
            return tipo == null ? null : TipoAnuncio.valueOf(tipo.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

     public StatusAnuncio getStatusEnum() {
        try {
            return status == null ? null : StatusAnuncio.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            return StatusAnuncio.ATIVO;
        }
    }

    

}
