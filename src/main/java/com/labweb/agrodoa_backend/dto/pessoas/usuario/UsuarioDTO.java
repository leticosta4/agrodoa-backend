package com.labweb.agrodoa_backend.dto.pessoas.usuario;

import com.labweb.agrodoa_backend.model.Tipo;
import com.labweb.agrodoa_backend.model.local.Cidade;
import com.labweb.agrodoa_backend.model.local.Estado;
import com.labweb.agrodoa_backend.model.pessoas.Usuario;

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

    public Usuario transformaParaObjeto(Tipo tipoUsuario, Estado estado, Cidade cidade){ 
        if (this.tipoUsuario == null || this.tipoUsuario == null) {
            throw new IllegalArgumentException("Tipo de usuário não informado.");
        }

        return UsuarioDTOFactory.criarUsuarioDTO(this, tipoUsuario, estado, cidade);
    }
}
