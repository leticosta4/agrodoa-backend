package com.labweb.agrodoa_backend.dto;

import com.labweb.agrodoa_backend.model.Tipo;
import com.labweb.agrodoa_backend.model.local.Cidade;
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
    private Cidade cidade;
    private Tipo tipoUsuario;
    int ehVoluntario; //talvez virar boolean?

    public UsuarioRespostaDTO(Usuario user){
        if (user.getTipoUsuario() == null || user.getTipoUsuario().getNome() == null) {
            throw new IllegalArgumentException("Tipo de usuário não informado.");
        }

        switch (this.getTipoUsuario().getNome().toLowerCase()) {
            case "fornecedor":
                atribuiValoresComuns(user);
                //lista de anuncios postados 
            case "beneficiario":
                atribuiValoresComuns(user);
                //lista de relacoes com anuncio
            case "hibrido":
                atribuiValoresComuns(user);
                //lista de anuncios postados e de relacoes com anuncio
            default:
                throw new IllegalArgumentException("Tipo inválido: " + this.tipoUsuario.getNome());
        }
    }

    public void atribuiValoresComuns(Usuario user){
        this.nome = user.getNome();
        this.email = user.getEmail();
        this.cpfOuCnpj = user.getCpfOuCnpj();
        this.nomeArquivoFoto = user.getNomeArquivoFoto();
        this.telefone = user.getTelefone();
        this.ehVoluntario = user.getEhVoluntario();
        this.tipoUsuario = user.getTipoUsuario();
        this.cidade = user.getCidade();
    }
}
