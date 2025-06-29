package com.labweb.agrodoa_backend.dto.pessoas.usuario;

import java.util.ArrayList;

import com.labweb.agrodoa_backend.dto.RelacaoBeneficiarioDTO;
import com.labweb.agrodoa_backend.dto.anuncio.AnuncioRespostaDTO;
import com.labweb.agrodoa_backend.model.pessoas.Hibrido;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class HibridoRespostaDTO {
    private ArrayList<AnuncioRespostaDTO> anunciosPostados;
    private ArrayList<RelacaoBeneficiarioDTO> relacoesAnuncios;

    public HibridoRespostaDTO(Hibrido hibrido) {
        //essa pt interna ta dando erro mas deve sair dps que definir o construtor do AnuncioRespostaDTO e do RelacaoBeneficiarioDTO
        // super(hibrido);
        // this.anunciosPostados = hibrido.getAnunciosPostados()
        //     .stream()
        //     .map(AnuncioDTO::new)
        //     .toList();

        // this.relacoesAnuncios = hibrido.getRelacoesAnuncios()
        //     .stream()
        //     .map(RelacaoBeneficiarioDTO::new)
        //     .toList();
    }
}
