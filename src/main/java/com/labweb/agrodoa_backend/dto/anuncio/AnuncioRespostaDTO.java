package com.labweb.agrodoa_backend.dto.anuncio;

import java.time.LocalDate;

import com.labweb.agrodoa_backend.dto.contas.usuario.AnuncianteDTO;
import com.labweb.agrodoa_backend.dto.local.LocalDTO;
import com.labweb.agrodoa_backend.dto.produto.ProdutoRespostaDTO;
import com.labweb.agrodoa_backend.model.Anuncio;
import com.labweb.agrodoa_backend.model.enums.StatusAnuncio;
import com.labweb.agrodoa_backend.model.enums.TipoAnuncio;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AnuncioRespostaDTO {
    private String idAnuncio;
    private String titulo;
    private String nomeArquivoFoto;
    private LocalDate dataExpiracao;
    private int entregaPeloFornecedor; //talvez mudar para boolean
    private StatusAnuncio status;
    private TipoAnuncio tipo;
    private AnuncianteDTO anunciante; //restrição do tipo ainda
    private ProdutoRespostaDTO produto;
    private LocalDTO local;

    public AnuncioRespostaDTO(Anuncio a){
        this.idAnuncio = a.getIdAnuncio();
        this.titulo = a.getTitulo();
        this.nomeArquivoFoto = a.getNomeArquivoFoto();
        this.dataExpiracao = a.getDataExpiracao();
        this.entregaPeloFornecedor = a.getEntregaPeloFornecedor();
        this.status = a.getStatus();
        this.tipo = a.getTipo();
        this.anunciante = new AnuncianteDTO(a.getAnunciante());
        this.produto = new ProdutoRespostaDTO(a.getProduto());
        this.local = new LocalDTO(a.getCidade());
    }
}