package com.labweb.agrodoa_backend.model;

import com.labweb.agrodoa_backend.model.enums.StatusNegociacao;
import com.labweb.agrodoa_backend.model.pessoas.Beneficiario;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
    private Long idPagamento;
    private double valorPago;
    private int quantidade;

    @Enumerated(EnumType.STRING)
    private StatusNegociacao status;

    @ManyToOne
    @JoinColumn(name = "anuncio_idanuncio")
    private Anuncio anuncio;

    @ManyToOne
    @JoinColumn(name = "usuario_conta_idconta")
    private Beneficiario beneficiado;

    public Negociacao(double valorPago, int quantidade, Anuncio anuncio, Beneficiario beneficiado){
        this.anuncio = anuncio;
        this.beneficiado = beneficiado;
        //valores padrão
        this.valorPago = this.anuncio.getProduto().getPrecoUnidade();
        this.quantidade = 1;
        this.status = StatusNegociacao.AGUARDANDO;
    }
}
    