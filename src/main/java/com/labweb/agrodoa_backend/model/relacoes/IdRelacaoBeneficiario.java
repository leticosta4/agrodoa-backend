package com.labweb.agrodoa_backend.model.relacoes;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class IdRelacaoBeneficiario implements Serializable{
    @Column(name = "anuncio_idanuncio")
    private Long anuncioId;

    @Column(name = "usuario_conta_idconta")
    private Long beneficiarioId;

    public IdRelacaoBeneficiario() {}

    public IdRelacaoBeneficiario(Long anuncioId, Long beneficiarioId) {
        this.anuncioId = anuncioId;
        this.beneficiarioId = beneficiarioId;
    }

    //criar equals e hashCode p o embeddable funcionar
}
