package com.labweb.agrodoa_backend.model.relacoes;

import com.labweb.agrodoa_backend.model.Anuncio;
import com.labweb.agrodoa_backend.model.pessoas.Beneficiario;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "relacao_beneficiario")
public class RelacaoBeneficiario {
    @EmbeddedId
    private IdRelacaoBeneficiario idRelacao; //dois valores por causa da classe  IdRelacaoBeneficiario (revisar)

    @ManyToOne
    @MapsId("anuncioId")
    @JoinColumn(name = "anuncio_idanuncio")
    private Anuncio anuncio;

    @ManyToOne
    @MapsId("conta_idconta")
    @JoinColumn(name = "usuario_conta_idconta")
    private Beneficiario beneficiario;

    @Column(name = "tipo_relacao_interessado")
    private String tipoRelacao;

    public RelacaoBeneficiario() {}

    public RelacaoBeneficiario(Anuncio anuncio, Beneficiario beneficiario, String tipoRelacao) {
        this.anuncio = anuncio;
        this.beneficiario = beneficiario;
        this.tipoRelacao = tipoRelacao;
        this.idRelacao = new IdRelacaoBeneficiario(anuncio.getIdAnuncio(), beneficiario.getIdConta());
    }
}
