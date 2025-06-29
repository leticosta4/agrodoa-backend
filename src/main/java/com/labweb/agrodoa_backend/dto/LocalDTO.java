package com.labweb.agrodoa_backend.dto;

import com.labweb.agrodoa_backend.model.local.Cidade;

import lombok.Getter;

@Getter
public class LocalDTO {
    private String cidade;
    private String estado;

    public LocalDTO(Cidade c){
        this.cidade = c.getNome();
        this.estado = c.getEstado().getNome();
    }
}
