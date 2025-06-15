package com.labweb.agrodoa_backend.model.relacoes;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class IdDoacaoCausa implements Serializable{  //revisar se precisa fazer assim mesm
    private Long usuarioId;
    private Long causaId;

    public IdDoacaoCausa(Long usuarioId, Long causaId) {
        this.usuarioId = usuarioId;
        this.causaId = causaId;
    }

    // equals e hashCode são obrigatórios para chave composta
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IdDoacaoCausa)) return false;
        IdDoacaoCausa that = (IdDoacaoCausa) o;
        return Objects.equals(usuarioId, that.usuarioId) &&
               Objects.equals(causaId, that.causaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuarioId, causaId);
    }
}
