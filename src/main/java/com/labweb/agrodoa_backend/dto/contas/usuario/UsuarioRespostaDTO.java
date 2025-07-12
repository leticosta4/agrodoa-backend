package com.labweb.agrodoa_backend.dto.contas.usuario;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.labweb.agrodoa_backend.dto.RelacaoBeneficiarioDTO;
import com.labweb.agrodoa_backend.dto.anuncio.AnuncioResumidoDTO;
import com.labweb.agrodoa_backend.dto.avaliacao.AvaliacaoDTO;
import com.labweb.agrodoa_backend.dto.local.LocalDTO;
import com.labweb.agrodoa_backend.model.contas.Usuario;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UsuarioRespostaDTO { //vai ser o exibido ao clicar em perfil
    private String idUser;
    private String nome;
    private String email;
    String cpfOuCnpj;
    String telefone;
    String nomeArquivoFoto;
    private String tipoUsuario;
    private LocalDTO local;
    private List<AnuncioResumidoDTO> anunciosPostados;
    private List<RelacaoBeneficiarioDTO> relacoesAnuncios;
    private List<AvaliacaoDTO> avaliacoes;
    
    public UsuarioRespostaDTO(Usuario user){
        this.idUser = user.getIdConta();
        this.nome = user.getNome();
        this.email = user.getEmail();
        this.cpfOuCnpj = user.getCpfOuCnpj();
        this.nomeArquivoFoto = user.getNomeArquivoFoto();
        this.telefone = user.getTelefone();
        this.tipoUsuario = user.getTipoUsuario().getNome();
        this.local = new LocalDTO(user.getCidade());

        if (user.getListaAnunciosPostados() != null) {
            this.anunciosPostados = user.getListaAnunciosPostados()
                                        .stream()
                                        .map(AnuncioResumidoDTO::new)
                                        .collect(Collectors.toList());
        }

        if (user.getRelacoesAnuncio() != null) {
            this.relacoesAnuncios = user.getRelacoesAnuncio()
                                        .stream()
                                        .map(RelacaoBeneficiarioDTO::new)
                                        .collect(Collectors.toList());
        }

        if(user.getAvaliacoes() != null){
            this.avaliacoes = user.getAvaliacoes()
                                    .stream()
                                    .map(AvaliacaoDTO::new)
                                    .collect(Collectors.toList());
        }
    }
}
