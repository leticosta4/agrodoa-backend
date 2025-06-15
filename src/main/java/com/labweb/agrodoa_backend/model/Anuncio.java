package com.labweb.agrodoa_backend.model;

import java.time.LocalDateTime;
import java.util.List;

import com.labweb.agrodoa_backend.model.local.Cidade;
import com.labweb.agrodoa_backend.model.pessoas.Fornecedor;
import com.labweb.agrodoa_backend.model.relacoes.Negociacao;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "anuncio")
@Getter
@Setter
@NoArgsConstructor
public class Anuncio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idAnuncio;
    private String titulo;
    private String nomeArquivoFoto;
    private LocalDateTime dataExpiracao;
    private int entregaPeloFornecedor; //talvez mudar para boolean
    //enum de status (expirado, finalizado ou ativo)
    //enum de tipo (AÇÃO no banco: venda ou anúncio)

    @ManyToOne
    @JoinColumn(name = "cidade_idcidade", referencedColumnName = "idcidade")
    private Cidade cidade;

    @ManyToOne
    @JoinColumn(name = "id_anunciante")
    private Fornecedor anunciante; //restrição do tipo >> comando SQL

    @OneToOne
    @JoinColumn(name = "produto_idproduto", referencedColumnName = "idcidade")
    private Produto produto; 

    @OneToMany(mappedBy = "anuncio", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Negociacao> negociacoes;

    public Anuncio(String titulo, String nomeArquivoFoto, LocalDateTime dataExpiracao, int entregaPeloFornecedor, Cidade cidade, Fornecedor anunciante, Produto produto) {
        this.titulo = titulo;
        this.nomeArquivoFoto = nomeArquivoFoto;
        this.dataExpiracao = dataExpiracao;
        this.entregaPeloFornecedor = entregaPeloFornecedor;
        this.cidade = cidade;
        this.anunciante = anunciante;
        this.produto = produto;
    }
}
