package com.labweb.agrodoa_backend.dto;

import com.labweb.agrodoa_backend.dto.anuncio.AnuncioRespostaDTO;
import com.labweb.agrodoa_backend.model.relacoes.RelacaoBeneficiario;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class RelacaoBeneficiarioDTO {
    private String status;
    private AnuncioRespostaDTO anuncio;

    public RelacaoBeneficiarioDTO(RelacaoBeneficiario relacao) {
        this.status = relacao.getTipoRelacao().toString(); // ou qualquer campo relevante
        this.anuncio = new AnuncioRespostaDTO(relacao.getAnuncio());
    }
}
