package com.labweb.agrodoa_backend.dto.denuncia;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DenunciaRequestDTO { //talvez n precise disso

    @NotBlank
    private String nomeMotivo; 

}