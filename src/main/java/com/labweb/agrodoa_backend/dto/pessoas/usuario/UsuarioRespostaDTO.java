package com.labweb.agrodoa_backend.dto.pessoas.usuario;

import java.util.ArrayList;

import com.labweb.agrodoa_backend.dto.RelacaoBeneficiarioDTO;
import com.labweb.agrodoa_backend.dto.anuncio.AnuncioRespostaDTO;
import com.labweb.agrodoa_backend.dto.local.LocalDTO;
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
    private ArrayList<AnuncioRespostaDTO> anunciosPostados;
    private ArrayList<RelacaoBeneficiarioDTO> relacoesAnuncios;
    
    public UsuarioRespostaDTO(Usuario user){
        this.nome = user.getNome();
        this.email = user.getEmail();
        this.cpfOuCnpj = user.getCpfOuCnpj();
        this.nomeArquivoFoto = user.getNomeArquivoFoto();
        this.telefone = user.getTelefone();
        this.tipoUsuario = user.getTipoUsuario().getNome();
        this.local = new LocalDTO(user.getCidade());

        //essa pt de baixo ta dando erro pq os dtos ainda não estao mt bem definidos
        // this.anunciosPostados = user.getListaAnunciosPostados();
        // this.relacoesAnuncios = user.getRelacoesAnuncio();
    }
}
