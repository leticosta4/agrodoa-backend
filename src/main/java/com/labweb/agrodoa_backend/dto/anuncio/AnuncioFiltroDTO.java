package com.labweb.agrodoa_backend.dto.anuncio;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.labweb.agrodoa_backend.model.enums.StatusAnuncio;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class AnuncioFiltroDTO {
    private String nome;
    private String cidade;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dataExpiracao;

    private String status;

     public StatusAnuncio getStatusEnum() {
        try {
            return status == null ? StatusAnuncio.ATIVO : StatusAnuncio.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            return StatusAnuncio.ATIVO;
        }
    }

    

}
