package com.labweb.agrodoa_backend.dto.pessoas.usuario;

import java.util.ArrayList;
import java.util.stream.Collectors;

import com.labweb.agrodoa_backend.dto.anuncio.AnuncioRespostaDTO;
import com.labweb.agrodoa_backend.model.pessoas.comportamento.PublicaAnuncios;
import com.labweb.agrodoa_backend.model.pessoas.Usuario;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

// @Getter
// @Setter
// @NoArgsConstructor
// public class FornecedorRespostaDTO extends UsuarioRespostaDTO{
//     private ArrayList<AnuncioRespostaDTO> anunciosPostados;

//     public FornecedorRespostaDTO(PublicaAnuncios fornecedor) {
//         super((Usuario) fornecedor);
//         this.anunciosPostados = fornecedor.getAnunciosPostados()
//             .stream()
//             .map(AnuncioRespostaDTO::new)
//             .collect(Collectors.toCollection(ArrayList::new));
//     }
// }
