package com.labweb.agrodoa_backend.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.labweb.agrodoa_backend.model.contas.Usuario;
import com.labweb.agrodoa_backend.model.enums.StatusAnuncio;
import com.labweb.agrodoa_backend.model.enums.TipoAnuncio;
import com.labweb.agrodoa_backend.model.local.Cidade;
import com.labweb.agrodoa_backend.model.relacoes.RelacaoBeneficiario;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
    @Column(name = "idanuncio")
    private String idAnuncio;
    
    @Column(name = "titulo")
    private String titulo;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "nome_arquivo_foto")
    private String nomeArquivoFoto;

    @Column(name = "data_expiracao")
    private LocalDate dataExpiracao;

    @Column(name = "entrega_pelo_fornecedor")
    private int entregaPeloFornecedor; //talvez mudar para boolean

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusAnuncio status;
    /*
     Anuncio anuncio = anuncioRepository.findById(1L).orElseThrow();
    System.out.println(anuncio.getStatus()); // Saída: ATIVO

    //se receber como string vindo de form ou API:
    public static TipoAnuncio fromString(String value) {
        try {
            return TipoAnuncio.valueOf(value.toUpperCase());
        } catch (Exception e) {
            return null;
        }
    }
    */

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_anuncio")
    private TipoAnuncio tipo;

    @ManyToOne
    @JoinColumn(name = "cidade_idcidade", referencedColumnName = "idcidade")
    private Cidade cidade;

    @ManyToOne
    @JoinColumn(name = "id_anunciante")
    private Usuario anunciante; //pode ser fornecedor ou hibrido >> a verificação do comportamento ainda

    @OneToOne
    @JoinColumn(name = "produto_idproduto", referencedColumnName = "idproduto")
    private Produto produto; 

    //lista de beneficiarios?
    @OneToMany(mappedBy = "anuncio", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RelacaoBeneficiario> relacoes = new ArrayList<>();

    /*na hora de filtrar fazer algo tipo:

    public List<Usuario> getListaNegociantes() {  
        return relacoes.stream()
            .filter(r -> "N".equals(r.getTipoRelacao()))
            .map(RelacaoBeneficiario::getUsuario)
            .toList();
    }
    */

    public Anuncio(String titulo, String descricao, String nomeArquivoFoto, LocalDate dataExpiracao, int entregaPeloFornecedor, TipoAnuncio tipo, Cidade cidade, Usuario anunciante, Produto produto) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.nomeArquivoFoto = nomeArquivoFoto;
        this.entregaPeloFornecedor = entregaPeloFornecedor;
        this.tipo = tipo;
        this.cidade = cidade;
        this.anunciante = anunciante;
        this.produto = produto;
        //valores padrao
        this.status = StatusAnuncio.ATIVO;
        this.dataExpiracao = this.produto.getDataValidade();
    }
}
