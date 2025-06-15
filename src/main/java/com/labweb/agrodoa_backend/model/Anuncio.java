package com.labweb.agrodoa_backend.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.labweb.agrodoa_backend.model.enums.StatusAnuncio;
import com.labweb.agrodoa_backend.model.enums.TipoAnuncio;
import com.labweb.agrodoa_backend.model.local.Cidade;
import com.labweb.agrodoa_backend.model.pessoas.Fornecedor;
import com.labweb.agrodoa_backend.model.relacoes.Pagamento;
import com.labweb.agrodoa_backend.model.relacoes.RelacaoBeneficiario;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

    @Enumerated(EnumType.STRING)
    private StatusAnuncio status; //para criar/atribuir StatusAnuncio.ATIVO ; 
    /*
     para criar/atribuir StatusAnuncio.ATIVO ; 

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
    private TipoAnuncio tipo;

    @ManyToOne
    @JoinColumn(name = "cidade_idcidade", referencedColumnName = "idcidade")
    private Cidade cidade;

    @ManyToOne
    @JoinColumn(name = "id_anunciante")
    private Fornecedor anunciante; //restrição do tipo >> comando SQL

    @OneToOne
    @JoinColumn(name = "produto_idproduto", referencedColumnName = "idcidade")
    private Produto produto; 

    //lista de beneficiarios?
    @OneToMany(mappedBy = "anuncio", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RelacaoBeneficiario> relacoes;   //ver o momento da inicializaçao

    /*na hora de filtrar fa\er algo tipo:

    public List<Usuario> getListaNegociantes() {  
        return relacoes.stream()
            .filter(r -> "N".equals(r.getTipoRelacao()))
            .map(RelacaoBeneficiario::getUsuario)
            .toList();
    }
    */

    public Anuncio(String titulo, String nomeArquivoFoto, LocalDateTime dataExpiracao, int entregaPeloFornecedor, TipoAnuncio tipo, Cidade cidade, Fornecedor anunciante, Produto produto) {
        this.titulo = titulo;
        this.nomeArquivoFoto = nomeArquivoFoto;
        this.dataExpiracao = dataExpiracao;
        this.entregaPeloFornecedor = entregaPeloFornecedor;
        this.tipo = tipo;
        this.cidade = cidade;
        this.anunciante = anunciante;
        this.produto = produto;
        this.status = StatusAnuncio.ATIVO; //valor padrao
    }
}
