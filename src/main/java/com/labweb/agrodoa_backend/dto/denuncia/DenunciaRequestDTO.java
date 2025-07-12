package com.labweb.agrodoa_backend.dto.denuncia;

import jakarta.validation.constraints.NotBlank;

public class DenunciaRequestDTO {

    @NotBlank
    private String nomeMotivo; 


    public String getNomeMotivo() {
        return nomeMotivo;
    }

    public void setNomeMotivo(String nomeMotivo) {
        this.nomeMotivo = nomeMotivo;
    }
}