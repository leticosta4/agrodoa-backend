package com.labweb.agrodoa_backend.dto.pessoas.usuario;

import java.util.ArrayList;

import com.labweb.agrodoa_backend.dto.RelacaoBeneficiarioDTO;
import com.labweb.agrodoa_backend.model.pessoas.Beneficiario;
import com.labweb.agrodoa_backend.model.pessoas.comportamento.RecebeAnuncios;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class BeneficiarioRespostaDTO {
    private ArrayList<RelacaoBeneficiarioDTO> relacoesAnuncios;

    public BeneficiarioRespostaDTO(RecebeAnuncios beneficiario) {
        //essa pt interna ta dando erro mas deve sair dps que definir o construtor do RelacaoBeneficiarioDTO
        // super(beneficiario);
        // this.relacoesAnuncios = beneficiario.getRelacoesAnuncios()
        //     .stream()
        //     .map(RelacaoBeneficiarioDTO::new)
        //     .toList();
    }
}
