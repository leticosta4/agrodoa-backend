package com.labweb.agrodoa_backend.dto;

import com.labweb.agrodoa_backend.dto.anuncio.AnuncioResumidoDTO;
import com.labweb.agrodoa_backend.model.enums.TipoRelacaoBenef;
import com.labweb.agrodoa_backend.model.relacoes.RelacaoBeneficiario;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class RelacaoBeneficiarioDTO {
    private TipoRelacaoBenef tipoRelacao;
    private AnuncioResumidoDTO anuncio;

    public RelacaoBeneficiarioDTO(RelacaoBeneficiario relacao) {
        this.tipoRelacao = relacao.getTipoRelacao();
        this.anuncio = new AnuncioResumidoDTO(relacao.getAnuncio());
    }
}
