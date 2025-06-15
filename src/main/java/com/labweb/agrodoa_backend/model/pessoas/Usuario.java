package com.labweb.agrodoa_backend.model.pessoas;

import com.labweb.agrodoa_backend.model.Tipo;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario")
@PrimaryKeyJoinColumn(name = "conta_idconta") // mapeia PK+FK >> exato nome da coluna no banco
public abstract class Usuario extends Conta{
    String cpfOuCnpj;
    String nomeArquivoFoto;
    String telefone;
    int ehVoluntario; //talvez virar boolean?
    
    @ManyToOne
    @JoinColumn(name = "tipo_idtipo", referencedColumnName = "idtipo") //nomes exatamente como no banco
    private Tipo tipoUsuario;
    
    //adicionar ainda:
    //chave que indica a cidade e o estado (local)
    //lista de anuncios em negociacao (independente do tipo de user)
    //lista de anuncios salvos (se for beneficiario)
    //opcional: listas de denuncias, de avaliações e de causas

    public Usuario(){}

    public Usuario(String nome, String email, String senha, String cpfOuCnpj, String nomeArquivoFoto, String telefone, int ehVoluntario, Tipo tipoUsuario) {
        super(nome, email, senha);
        this.cpfOuCnpj = cpfOuCnpj;
        this.nomeArquivoFoto = nomeArquivoFoto;
        this.telefone = telefone;
        this.ehVoluntario = ehVoluntario;
        this.tipoUsuario = tipoUsuario;
    }
}
