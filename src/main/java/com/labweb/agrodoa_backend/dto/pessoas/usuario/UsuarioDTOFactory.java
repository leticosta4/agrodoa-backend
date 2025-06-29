package com.labweb.agrodoa_backend.dto.pessoas.usuario;

import com.labweb.agrodoa_backend.model.pessoas.*;

public class UsuarioDTOFactory {
    public static Usuario criarUsuarioDTO(UsuarioDTO tempUser){
        switch (tempUser.getTipoUsuario().getNome().toLowerCase()) {  //separar factory dps e chamar metodos
            case "fornecedor":
                return new Fornecedor(tempUser.getNome(), tempUser.getEmail(), tempUser.getSenha(), tempUser.getCpfOuCnpj(), tempUser.getNomeArquivoFoto(), tempUser.getTelefone(), tempUser.getTipoUsuario(), tempUser.getCidade());
            case "beneficiario":
                return new Beneficiario(tempUser.getNome(), tempUser.getEmail(), tempUser.getSenha(), tempUser.getCpfOuCnpj(), tempUser.getNomeArquivoFoto(), tempUser.getTelefone(), tempUser.getTipoUsuario(), tempUser.getCidade());
            case "hibrido":
                return new Hibrido(tempUser.getNome(), tempUser.getEmail(), tempUser.getSenha(), tempUser.getCpfOuCnpj(), tempUser.getNomeArquivoFoto(), tempUser.getTelefone(), tempUser.getTipoUsuario(), tempUser.getCidade());
            default:
                throw new IllegalArgumentException("Tipo inválido: " + tempUser.getTipoUsuario().getNome());
        }
    }
}
