package com.labweb.agrodoa_backend.model.relacoes;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class IdRelacaoBeneficiario implements Serializable{ //revisar se precisa mesmo disso
    @Column(name = "anuncio_idanuncio")
    private Long anuncioId;

    @Column(name = "usuario_conta_idconta")
    private Long beneficiarioId;

    public IdRelacaoBeneficiario(Long anuncioId, Long beneficiarioId) {
        this.anuncioId = anuncioId;
        this.beneficiarioId = beneficiarioId;
    }

    //criar equals e hashCode p o embeddable funcionar
    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if (!(o instanceof IdRelacaoBeneficiario)) return false;

        IdRelacaoBeneficiario that = (IdRelacaoBeneficiario) o;
        return Objects.equals(anuncioId, that.anuncioId) &&
               Objects.equals(beneficiarioId, that.beneficiarioId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(anuncioId, beneficiarioId);
    }
}
