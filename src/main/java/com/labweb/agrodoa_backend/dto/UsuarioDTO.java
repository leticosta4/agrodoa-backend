package com.labweb.agrodoa_backend.dto;

import com.labweb.agrodoa_backend.model.Tipo;
import com.labweb.agrodoa_backend.model.local.Cidade;
import com.labweb.agrodoa_backend.model.pessoas.Beneficiario;
import com.labweb.agrodoa_backend.model.pessoas.Fornecedor;
import com.labweb.agrodoa_backend.model.pessoas.Hibrido;
import com.labweb.agrodoa_backend.model.pessoas.Usuario;

import jakarta.validation.constraints.NotBlank;

public class UsuarioDTO { 
    @NotBlank(message = "O campo nome é obrigatório!")
    private String nome;

    @NotBlank(message = "O campo email é obrigatório!")
    private String email;

    @NotBlank(message = "O campo senha é obrigatório!")
    private String senha;

    @NotBlank(message = "O campo CPF/CNPJ é obrigatório!")
    String cpfOuCnpj;

    String nomeArquivoFoto;

    String telefone;

    private Cidade cidade;

    private Tipo tipoUsuario;

    public Usuario transformaParaObjeto(){ 
        if(this.tipoUsuario.getNome().equals("fornecedor")){
            return new Fornecedor(nome, email, senha, cpfOuCnpj, nomeArquivoFoto, telefone, 0, tipoUsuario, cidade);
        } else if(this.tipoUsuario.getNome().equals("beneficiario")){
            return new Beneficiario(nome, email, senha, cpfOuCnpj, nomeArquivoFoto, telefone, 0, tipoUsuario, cidade);
        } else if(this.tipoUsuario.getNome().equals("hibrido")){
            return new Hibrido(nome, email, senha, cpfOuCnpj, nomeArquivoFoto, telefone, 0, tipoUsuario, cidade);
        } else {
            throw new IllegalArgumentException("Tipo inválido: " + this.tipoUsuario.getNome());
        }
        
    }
}
