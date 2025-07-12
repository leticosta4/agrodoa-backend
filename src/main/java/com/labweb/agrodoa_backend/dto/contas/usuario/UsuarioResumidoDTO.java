package com.labweb.agrodoa_backend.dto.contas.usuario;

import com.labweb.agrodoa_backend.model.contas.Usuario;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UsuarioResumidoDTO {
    private String idAnunciante;
    private String nome;
    private String email;
    private String cpfOuCnpj;
    String telefone;
    
    public UsuarioResumidoDTO(Usuario user){
        this.idAnunciante = user.getIdConta();
        this.nome = user.getNome();
        this.email = user.getEmail();
        this.cpfOuCnpj = user.getCpfOuCnpj();
        this.telefone = user.getTelefone();
    }
}
