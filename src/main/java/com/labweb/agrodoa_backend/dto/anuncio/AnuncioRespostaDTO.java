package com.labweb.agrodoa_backend.dto.anuncio;

import java.time.LocalDate;

import com.labweb.agrodoa_backend.dto.local.LocalDTO;
import com.labweb.agrodoa_backend.dto.pessoas.usuario.UsuarioRespostaDTO;
import com.labweb.agrodoa_backend.dto.produto.ProdutoRespostaDTO;
import com.labweb.agrodoa_backend.model.Anuncio;
import com.labweb.agrodoa_backend.model.enums.StatusAnuncio;
import com.labweb.agrodoa_backend.model.enums.TipoAnuncio;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AnuncioRespostaDTO {
    private String titulo;
    private String nomeArquivoFoto;
    private LocalDate dataExpiracao;
    private int entregaPeloFornecedor; //talvez mudar para boolean
    private StatusAnuncio status;
    private TipoAnuncio tipo;
    private UsuarioRespostaDTO anunciante; //restrição do tipo >> validação no service com interface de comportamento
    private ProdutoRespostaDTO produto;
    private LocalDTO local;

    public AnuncioRespostaDTO(Anuncio a){
        this.titulo = a.getTitulo();
        this.nomeArquivoFoto = a.getNomeArquivoFoto();
        this.dataExpiracao = a.getDataExpiracao();
        this.entregaPeloFornecedor = a.getEntregaPeloFornecedor();
        this.status = a.getStatus();
        this.tipo = a.getTipo();
        this.anunciante = new UsuarioRespostaDTO(a.getAnunciante());
        this.produto = new ProdutoRespostaDTO(a.getProduto());
        this.local = new LocalDTO(a.getCidade());
    }
}