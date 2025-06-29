package com.labweb.agrodoa_backend.dto.pessoas.usuario;

import com.labweb.agrodoa_backend.dto.LocalDTO;
import com.labweb.agrodoa_backend.model.pessoas.Usuario;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UsuarioRespostaDTO { 
    private String nome;
    private String email;
    String cpfOuCnpj;
    String telefone;
    String nomeArquivoFoto;
    private String tipoUsuario;
    private LocalDTO local;
    
    public UsuarioRespostaDTO(Usuario user){
        this.nome = user.getNome();
        this.email = user.getEmail();
        this.cpfOuCnpj = user.getCpfOuCnpj();
        this.nomeArquivoFoto = user.getNomeArquivoFoto();
        this.telefone = user.getTelefone();
        this.tipoUsuario = user.getTipoUsuario().getNome();
        this.local = new LocalDTO(user.getCidade());
    }
}
