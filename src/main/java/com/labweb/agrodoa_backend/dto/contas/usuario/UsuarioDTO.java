package com.labweb.agrodoa_backend.dto.contas.usuario;

import com.labweb.agrodoa_backend.model.Tipo;
import com.labweb.agrodoa_backend.model.contas.Usuario;
import com.labweb.agrodoa_backend.model.local.Cidade;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UsuarioDTO { 
    @NotBlank(message = "O campo nome é obrigatório!")
    private String nome;

    @NotBlank(message = "O campo email é obrigatório!")
    private String email;

    @NotBlank(message = "O campo senha é obrigatório!")
    private String senha;

    @NotBlank(message = "O campo CPF/CNPJ é obrigatório!")
    String cpfOuCnpj;

    @NotBlank(message = "O campo telefone é obrigatório!")
    String telefone;

    String nomeArquivoFoto;
    
    @NotBlank(message = "O campo estado é obrigatório!")
    private String estado;

    @NotBlank(message = "O campo cidade é obrigatório!")
    private String idCidade;

    @NotBlank(message = "O campo tipo é obrigatório!")
    private String tipoUsuario;

    public UsuarioDTO(Usuario user) {
        this.nome = user.getNome();
        this.email = user.getEmail();
        this.cpfOuCnpj = user.getCpfOuCnpj();
        this.telefone = user.getTelefone();
        this.nomeArquivoFoto = user.getNomeArquivoFoto();
        this.estado = user.getCidade().getEstado().getNome();
        this.idCidade = user.getCidade().getIdCidade();
        this.tipoUsuario = user.getTipoUsuario().getNome();
    }

    public Usuario transformaParaObjeto(Tipo tipoUsuario, Cidade cidade){ 
        if (this.tipoUsuario == null || this.tipoUsuario == null) {
            throw new IllegalArgumentException("Tipo de usuário não informado.");
        }

        return new Usuario(this.nome, this.email, this.senha, this.cpfOuCnpj, this.nomeArquivoFoto, this.telefone, cidade, tipoUsuario);
    }
}
