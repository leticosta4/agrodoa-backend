package com.labweb.agrodoa_backend.model;

import com.labweb.agrodoa_backend.model.contas.Usuario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "avaliacao")
@Getter
@Setter
@NoArgsConstructor
public class Avaliacao {
    @Id
    @Column(name = "idavaliacao")
    private String idAvaliacao;

    @Column(name = "nota")
    private int nota;

    @Column(name = "comentario")
    private String comentario;
    
    @ManyToOne
    @JoinColumn(name = "id_avaliador", referencedColumnName = "conta_idconta")
    private Usuario avaliador;

    @ManyToOne
    @JoinColumn(name = "id_avaliado", referencedColumnName = "conta_idconta")
    private Usuario avaliado;

    public Avaliacao(int nota, String comentario, Usuario avaliador, Usuario avaliado){
        this.nota = nota;
        this.comentario = comentario;
        this.avaliador = avaliador;
        this.avaliado = avaliado;
    }
}
   