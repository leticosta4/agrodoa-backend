package com.labweb.agrodoa_backend.dto.pessoas.usuario;

import java.util.ArrayList;
import java.util.stream.Collectors;

import com.labweb.agrodoa_backend.dto.RelacaoBeneficiarioDTO;
import com.labweb.agrodoa_backend.model.pessoas.Usuario;
import com.labweb.agrodoa_backend.model.pessoas.comportamento.RecebeAnuncios;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class BeneficiarioRespostaDTO extends UsuarioRespostaDTO{
    private ArrayList<RelacaoBeneficiarioDTO> relacoesAnuncios;

    public BeneficiarioRespostaDTO(RecebeAnuncios beneficiario) {
        super((Usuario) beneficiario);
        this.relacoesAnuncios = beneficiario.getRelacoesAnuncios()
             .stream()
             .map(RelacaoBeneficiarioDTO::new)
             .collect(Collectors.toCollection(ArrayList::new));
    }
}