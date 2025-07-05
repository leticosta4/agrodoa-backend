package com.labweb.agrodoa_backend.model.relacoes;

import com.labweb.agrodoa_backend.model.Anuncio;
import com.labweb.agrodoa_backend.model.contas.Usuario;
import com.labweb.agrodoa_backend.model.enums.TipoRelacaoBenef;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "relacao_beneficiario")
public class RelacaoBeneficiario {
    @EmbeddedId
    private IdRelacaoBeneficiario idRelacao; //dois valores por causa da classe  IdRelacaoBeneficiario (revisar)

    @ManyToOne
    @MapsId("anuncioId") // MapsId para "ligar" a FK da classe principal com a @EmbeddedId
    @JoinColumn(name = "anuncio_idanuncio")
    private Anuncio anuncio;

    @ManyToOne
    @MapsId("beneficiarioId")
    @JoinColumn(name = "usuario_conta_idconta")
    private Usuario beneficiario;  //pode ser beneficiario ou hibrido >> a verificação do comportamento deve ser feita no service com a interface RecebeAnuncios

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_relacao_interessado")
    private TipoRelacaoBenef tipoRelacao;

    public RelacaoBeneficiario(Anuncio anuncio, Usuario beneficiario, TipoRelacaoBenef tipoRelacao) {
        this.anuncio = anuncio;
        this.beneficiario = beneficiario;
        this.tipoRelacao = tipoRelacao;
        this.idRelacao = new IdRelacaoBeneficiario(anuncio.getIdAnuncio(), beneficiario.getIdConta());
    }
}
