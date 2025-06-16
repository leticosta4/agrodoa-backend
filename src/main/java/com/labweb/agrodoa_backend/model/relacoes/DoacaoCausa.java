package com.labweb.agrodoa_backend.model.relacoes;

import com.labweb.agrodoa_backend.model.pessoas.Usuario;
import com.labweb.agrodoa_backend.model.Causa;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
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
@Table(name = "usuario_has_causa")
public class DoacaoCausa {
    @EmbeddedId
    private IdDoacaoCausa id = new IdDoacaoCausa();
    
    @ManyToOne
    @MapsId("usuarioId")
    @JoinColumn(name = "usuario_conta_idconta")
    private Usuario usuario;

    @ManyToOne
    @MapsId("causaId")
    @JoinColumn(name = "causa_idcausa")
    private Causa causa;

    @Column(name = "valor_doado")
    private float valorDoado;

    public DoacaoCausa(Usuario usuario, Causa causa, float valorDoado){
        this.usuario = usuario;
        this.causa = causa;
        this.valorDoado = valorDoado;
    }
}
