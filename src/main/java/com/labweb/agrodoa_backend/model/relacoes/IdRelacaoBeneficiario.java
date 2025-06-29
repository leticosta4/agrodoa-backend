package com.labweb.agrodoa_backend.model.relacoes;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode  //precisa p o embeddable funcionar
@Embeddable 
public class IdRelacaoBeneficiario implements Serializable{ //revisar se precisa mesmo disso
    private String anuncioId;
    private String beneficiarioId;

    public IdRelacaoBeneficiario(String anuncioId, String beneficiarioId) {
        this.anuncioId = anuncioId;
        this.beneficiarioId = beneficiarioId;
    }
}
