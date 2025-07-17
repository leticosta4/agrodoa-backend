package com.labweb.agrodoa_backend.dto.negociacao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NegociacaoRespostaDTO {
    
    private Integer quantidade;

    public NegociacaoRespostaDTO(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
