package com.labweb.agrodoa_backend.dto.pessoas.usuario;

import org.springframework.beans.factory.annotation.Autowired;

import com.labweb.agrodoa_backend.model.Tipo;
import com.labweb.agrodoa_backend.model.local.Cidade;
import com.labweb.agrodoa_backend.model.local.Estado;
import com.labweb.agrodoa_backend.model.pessoas.*;
import com.labweb.agrodoa_backend.repository.TipoRepository;
import com.labweb.agrodoa_backend.repository.local.CidadeRepository;
import com.labweb.agrodoa_backend.repository.local.EstadoRepository;

public class UsuarioDTOFactory {
    @Autowired
    static TipoRepository tipoRepo;
    @Autowired
    static EstadoRepository estadoRepo;
    @Autowired
    static CidadeRepository cidadeRepo;
    
    public static Usuario criarUsuarioDTO(UsuarioDTO tempUser, Tipo tipoUsuario, Estado estado, Cidade cidade){
        switch (tempUser.getTipoUsuario().toLowerCase()) {
            case "fornecedor":
                return new Fornecedor(tempUser.getNome(), tempUser.getEmail(), tempUser.getSenha(), tempUser.getCpfOuCnpj(), tempUser.getNomeArquivoFoto(), tempUser.getTelefone(), tipoUsuario, cidade);
            case "beneficiario":
                return new Beneficiario(tempUser.getNome(), tempUser.getEmail(), tempUser.getSenha(), tempUser.getCpfOuCnpj(), tempUser.getNomeArquivoFoto(), tempUser.getTelefone(), tipoUsuario, cidade);
            case "hibrido":
                return new Hibrido(tempUser.getNome(), tempUser.getEmail(), tempUser.getSenha(), tempUser.getCpfOuCnpj(), tempUser.getNomeArquivoFoto(), tempUser.getTelefone(), tipoUsuario, cidade);
            default:
                throw new IllegalArgumentException("Tipo inválido: " + tipoUsuario);
        }
    }
}
