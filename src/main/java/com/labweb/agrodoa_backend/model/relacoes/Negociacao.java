package com.labweb.agrodoa_backend.model.relacoes;

import com.labweb.agrodoa_backend.model.Anuncio;
import com.labweb.agrodoa_backend.model.pessoas.Beneficiario;

import jakarta.persistence.Entity;
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
public class Negociacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idNegociacao;
    private float valorPago;
    private int quantidade;
    //adicionar o statusNegociacao como enum...  

    @ManyToOne
    @JoinColumn(name = "anuncio_idanuncio")
    private Anuncio anuncio;

    @ManyToOne
    @JoinColumn(name = "usuario_conta_idconta")
    private Beneficiario beneficiado;

    public Negociacao(float valorPago, int quantidade, Anuncio anuncio, Beneficiario beneficiado){
        this.valorPago = valorPago;
        this.quantidade = quantidade;
        this.anuncio = anuncio;
        this.beneficiado = beneficiado;
    }

}
    