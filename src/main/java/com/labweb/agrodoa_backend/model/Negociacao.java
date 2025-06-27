package com.labweb.agrodoa_backend.model;

import com.labweb.agrodoa_backend.model.enums.StatusNegociacao;
import com.labweb.agrodoa_backend.model.relacoes.RelacaoBeneficiario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "negociacao")
@Getter
@Setter
@NoArgsConstructor
public class Negociacao { //nossa negociacao confirmada fechada já com algum beneficiario => venda ou doação, por isso esse nome em vez de pagamento
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String idPagamento;
    private double valorPago;
    private int quantidade;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_negociacao")
    private StatusNegociacao status;

    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "id_anuncio", referencedColumnName = "anuncio_idanuncio"),
        @JoinColumn(name = "id_beneficiario", referencedColumnName = "usuario_conta_idconta")
    })
    private RelacaoBeneficiario relacao;

    public Negociacao(double valorPago, int quantidade, RelacaoBeneficiario relacao){
        this.relacao = relacao;
        //valores padrão
        this.valorPago = this.relacao.getAnuncio().getProduto().getPrecoUnidade();
        this.quantidade = 1;
        this.status = StatusNegociacao.AGUARDANDO;
    }
}
    