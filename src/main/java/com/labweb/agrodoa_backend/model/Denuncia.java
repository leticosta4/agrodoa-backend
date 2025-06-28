package com.labweb.agrodoa_backend.model;

import com.labweb.agrodoa_backend.model.enums.StatusDenuncia;
import com.labweb.agrodoa_backend.model.pessoas.Usuario;

import jakarta.persistence.Column;
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
@Table(name = "denuncia")
@Getter
@Setter
@NoArgsConstructor
public class Denuncia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String idDenuncia;
    //adicionar o enum do status da denuncia

    @ManyToOne
    @JoinColumn(name = "motivo_idmotivo")
    private Motivo motivo; 

    @ManyToOne
    @JoinColumn(name = "id_denunciante", referencedColumnName = "conta_idconta")
    private Usuario denunciante;

    @ManyToOne
    @JoinColumn(name = "id_denunciado", referencedColumnName = "conta_idconta")
    private Usuario denunciado;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_denuncia")
    private StatusDenuncia status;

    public Denuncia(Motivo motivo, Usuario denunciante, Usuario denunciado){
        this.motivo = motivo;
        this.denunciante = denunciante;
        this.denunciado = denunciado;
        this.status = StatusDenuncia.AGUARDANDO; //valor padrao
    }
}
