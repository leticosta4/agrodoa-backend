package com.labweb.agrodoa_backend.model;

import java.beans.Transient;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.labweb.agrodoa_backend.model.enums.StatusAnuncio;
import com.labweb.agrodoa_backend.model.enums.TipoAnuncio;
import com.labweb.agrodoa_backend.model.local.Cidade;
import com.labweb.agrodoa_backend.model.pessoas.Fornecedor;
import com.labweb.agrodoa_backend.model.relacoes.RelacaoBeneficiario;

import com.labweb.agrodoa_backend.service.NotificacaoObserver.Notificacao;
import com.labweb.agrodoa_backend.service.NotificacaoObserver.Notificacao.TipoNotificacao;
import com.labweb.agrodoa_backend.service.NotificacaoObserver.Observer;
import com.labweb.agrodoa_backend.service.NotificacaoObserver.Subject;

import java.beans.Transient;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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
public class Anuncio implements Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String idAnuncio;
    
    @Column(name = "titulo")
    private String titulo;

    @Column(name = "nome_arquivo_foto")
    private String nomeArquivoFoto;

    @Column(name = "data_expiracao")
    private LocalDate dataExpiracao;

    @Column(name = "entrega_pelo_fornecedor")
    private int entregaPeloFornecedor; //talvez mudar para boolean

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusAnuncio status; //para criar/atribuir StatusAnuncio.ATIVO ; 
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
    private Fornecedor anunciante; //restrição do tipo >> comando SQL - talvez tenha que mudar depois pq tem o hibrido tb

    @OneToOne
    @JoinColumn(name = "produto_idproduto", referencedColumnName = "idproduto")
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

    public Anuncio(String titulo, String nomeArquivoFoto, LocalDate dataExpiracao, int entregaPeloFornecedor, TipoAnuncio tipo, Cidade cidade, Fornecedor anunciante, Produto produto) {
        this.titulo = titulo;
        this.nomeArquivoFoto = nomeArquivoFoto;
        this.entregaPeloFornecedor = entregaPeloFornecedor;
        this.tipo = tipo;
        this.cidade = cidade;
        this.anunciante = anunciante;
        this.produto = produto;
        //valores padrao
        this.status = StatusAnuncio.ATIVO; //ver como fazer associação com letra para armazenar no banco
        this.dataExpiracao = this.produto.getDataValidade();
    }

    // Logica para a classe ser observada
    // @Transient
    // private List<Observer> observadores = new ArrayList<>();

    private transient List<Observer> observadores = new ArrayList<>();

    public void checarValidade() {
        if (LocalDate.now().plusDays(3).isAfter(this.dataExpiracao)) {
            Notificacao notificacao = new Notificacao(
                "O anúncio '" + this.titulo + "' está próximo de expirar.",
                TipoNotificacao.ANUNCIO_VALIDADE_PROXIMA,
                this
            );
            notificarObservadores(notificacao);
        }
    }

    public void checarLimiteNegociantes() {
        final int LIMITE = 10; // Exemplo de limite
        if (this.relacoes != null && this.relacoes.size() >= LIMITE) {
            Notificacao notificacao = new Notificacao(
                "O anúncio '" + this.titulo + "' atingiu seu limite de negociantes.",
                TipoNotificacao.ANUNCIO_LIMITE_NEGOCIANTES,
                this
            );
            notificarObservadores(notificacao);
        }
    }

    //Metodos da interface
    @Override
    public void adicionarObservador(Observer observer) {
        if (!observadores.contains(observer)) observadores.add(observer);
    }

    @Override
    public void removerObservador(Observer observer) {
        observadores.remove(observer);
    }

    @Override
    public void notificarObservadores(Notificacao notificacao) {
        for (Observer obs : observadores) {
            obs.atualizar(notificacao);
        }
    }
}
