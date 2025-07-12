package com.labweb.agrodoa_backend.dto.denuncia;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor 
public class AvaliacaoDenunciaDTO {

    @NotBlank(message = "O campo 'status' é obrigatório.")
    private String status; 

    public AvaliacaoDenunciaDTO(String s){
        this.status = s;
    }
}
