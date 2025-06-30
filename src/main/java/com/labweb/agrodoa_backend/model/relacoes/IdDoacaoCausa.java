package com.labweb.agrodoa_backend.model.relacoes;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode  //precisa p o embeddable funcionar
public class IdDoacaoCausa implements Serializable{  //revisar se precisa fazer assim mesm
    private String usuarioId;
    private String causaId;

    public IdDoacaoCausa(String usuarioId, String causaId) {
        this.usuarioId = usuarioId;
        this.causaId = causaId;
    }
}
