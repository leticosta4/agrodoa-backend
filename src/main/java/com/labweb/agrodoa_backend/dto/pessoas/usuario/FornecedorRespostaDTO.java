package com.labweb.agrodoa_backend.dto.pessoas.usuario;

import java.util.ArrayList;

import com.labweb.agrodoa_backend.dto.anuncio.AnuncioRespostaDTO;
import com.labweb.agrodoa_backend.model.pessoas.comportamento.PublicaAnuncios;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class FornecedorRespostaDTO extends UsuarioRespostaDTO{
    private ArrayList<AnuncioRespostaDTO> anunciosPostados;

    public FornecedorRespostaDTO(PublicaAnuncios fornecedor) {
        //essa pt interna ta dando erro mas deve sair dps que definir o construtor do AnuncioRespostaDTO
        // super(fornecedor);
        // this.anunciosPostados = fornecedor.getAnunciosPostados()
        //     .stream()
        //     .map(AnuncioRespostaDTO::new)
        //     .toList();
    }
}
