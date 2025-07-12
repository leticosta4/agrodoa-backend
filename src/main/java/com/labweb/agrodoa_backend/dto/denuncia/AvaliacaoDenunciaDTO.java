package com.labweb.agrodoa_backend.dto.denuncia;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// @Getter
// @Setter
// @NoArgsConstructor 
public class AvaliacaoDenunciaDTO {

    @NotBlank(message = "O campo 'status' é obrigatório.")
    // @NotNull(message = "O campo 'status' não pode ser nulo.")
    private String nomeStatus; 

    public String getNomeStatus(){
        return nomeStatus;
    }

    public void setNomeStatus(String nomeStatus){
        this.nomeStatus = nomeStatus;
    }
}
