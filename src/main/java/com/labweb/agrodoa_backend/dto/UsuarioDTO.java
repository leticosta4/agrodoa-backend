package com.labweb.agrodoa_backend.dto;

import com.labweb.agrodoa_backend.model.Tipo;
import com.labweb.agrodoa_backend.model.local.Cidade;
import com.labweb.agrodoa_backend.model.pessoas.Beneficiario;
import com.labweb.agrodoa_backend.model.pessoas.Fornecedor;
import com.labweb.agrodoa_backend.model.pessoas.Hibrido;
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
    private Cidade cidade;
    private Tipo tipoUsuario;
    int ehVoluntario; //talvez virar boolean?

    public Usuario transformaParaObjeto(){ 
        if (this.tipoUsuario == null || this.tipoUsuario.getNome() == null) {
            throw new IllegalArgumentException("Tipo de usuário não informado.");
        }

        switch (this.tipoUsuario.getNome().toLowerCase()) {
            case "fornecedor":
                return new Fornecedor(nome, email, senha, cpfOuCnpj, nomeArquivoFoto, telefone, ehVoluntario, tipoUsuario, cidade);
            case "beneficiario":
                return new Beneficiario(nome, email, senha, cpfOuCnpj, nomeArquivoFoto, telefone, ehVoluntario, tipoUsuario, cidade);
            case "hibrido":
                return new Hibrido(nome, email, senha, cpfOuCnpj, nomeArquivoFoto, telefone, ehVoluntario, tipoUsuario, cidade);
            default:
                throw new IllegalArgumentException("Tipo inválido: " + this.tipoUsuario.getNome());
        }
    }
}
