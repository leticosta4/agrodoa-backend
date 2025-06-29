package com.labweb.agrodoa_backend.dto.anuncio;

import java.time.LocalDate;

import com.labweb.agrodoa_backend.model.Anuncio;
import com.labweb.agrodoa_backend.model.Produto;
import com.labweb.agrodoa_backend.model.enums.StatusAnuncio;
import com.labweb.agrodoa_backend.model.enums.TipoAnuncio;
import com.labweb.agrodoa_backend.model.local.Cidade;
import com.labweb.agrodoa_backend.model.pessoas.comportamento.PublicaAnuncios;

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
    private Cidade cidade;
    private PublicaAnuncios anunciante; //restrição do tipo >> comando SQL - talvez tenha que mudar depois pq tem o hibrido tb
    private Produto produto;

    public AnuncioRespostaDTO(Anuncio a){
        this.titulo = a.getTitulo();
        this.nomeArquivoFoto = a.getNomeArquivoFoto();
        this.dataExpiracao = a.getDataExpiracao();
        this.entregaPeloFornecedor = a.getEntregaPeloFornecedor();
        this.status = a.getStatus();
        this.tipo = a.getTipo();
        this.cidade = a.getCidade();
        this.anunciante = a.getAnunciante();
        this.produto = a.getProduto();
    }
}
