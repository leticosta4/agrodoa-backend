package com.labweb.agrodoa_backend.dto.contas.usuario;

import com.labweb.agrodoa_backend.model.contas.Usuario;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AnuncianteDTO {
    private String idAnunciante;
    private String nome;
    private String email;
    String telefone;
    String nomeArquivoFoto;
    
    public AnuncianteDTO(Usuario user){
        this.idAnunciante = user.getIdConta();
        this.nome = user.getNome();
        this.email = user.getEmail();
        this.nomeArquivoFoto = user.getNomeArquivoFoto();
        this.telefone = user.getTelefone();
    }
}
