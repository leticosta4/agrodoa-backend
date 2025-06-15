package com.labweb.agrodoa_backend.model;

import java.time.LocalDateTime;

import com.labweb.agrodoa_backend.model.pessoas.Usuario;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "anuncio")
public class Anuncio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idAnuncio;
    private String titulo;
    private String nomeArquivoFoto;
    private LocalDateTime dataExpiracao;
    private int entregaPeloFornecedor; //talvez mudar para boolean

    @ManyToOne
    @JoinColumn(name = "id_anunciante")
    private Usuario anunciante; //restrição do tipo >> comando SQL

    @OneToOne
    @JoinColumn(name = "produto_idproduto")
    private Produto produto; 

    //ver ainda como colocar:
    //os enums para status (expirado, finalizado ou ativo) e o tipo de anuncio (AÇÃO no banco: venda ou anúncio) --representando como letras no banco, adicionar o status N de negociando
    //a chave da cidade e estado (local)

    public Anuncio(){}

    public Anuncio(String titulo, String nomeArquivoFoto, LocalDateTime dataExpiracao, int entregaPeloFornecedor, Usuario anunciante, Produto produto) {
        this.titulo = titulo;
        this.nomeArquivoFoto = nomeArquivoFoto;
        this.dataExpiracao = dataExpiracao;
        this.entregaPeloFornecedor = entregaPeloFornecedor;
        this.anunciante = anunciante;
        this.produto = produto;
    }

    public long getIdAnuncio() {
        return idAnuncio;
    }
    public void setIdAnuncio(long idAnuncio) {
        this.idAnuncio = idAnuncio;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getNomeArquivoFoto() {
        return nomeArquivoFoto;
    }
    public void setNomeArquivoFoto(String nomeArquivoFoto) {
        this.nomeArquivoFoto = nomeArquivoFoto;
    }
    public LocalDateTime getDataExpiracao() {
        return dataExpiracao;
    }
    public void setDataExpiracao(LocalDateTime dataExpiracao) {
        this.dataExpiracao = dataExpiracao;
    }
    public int getEntregaPeloFornecedor() {
        return entregaPeloFornecedor;
    }
    public void setEntregaPeloFornecedor(int entregaPeloFornecedor) {
        this.entregaPeloFornecedor = entregaPeloFornecedor;
    }
}
