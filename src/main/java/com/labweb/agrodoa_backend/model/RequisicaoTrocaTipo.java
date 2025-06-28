package com.labweb.agrodoa_backend.model;

import com.labweb.agrodoa_backend.model.pessoas.Usuario;

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
@Table(name = "requisicao")
@Getter
@Setter
@NoArgsConstructor
public class RequisicaoTrocaTipo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String idRequisicaoTrocaTipo; 

    @ManyToOne
    @JoinColumn(name = "usuario_conta_idconta")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "tipo_anterior")
    private Tipo tipoAnterior; //para o banco guardar só esse
    //private Tipo novoTipoDesejado; //pensar se precisa mesmo

    public RequisicaoTrocaTipo(Usuario usuario){
        this.usuario = usuario;
        this.tipoAnterior = this.usuario.getTipoUsuario();
    }
}