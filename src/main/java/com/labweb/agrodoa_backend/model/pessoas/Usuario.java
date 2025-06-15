package com.labweb.agrodoa_backend.model.pessoas;

import java.util.List;

import com.labweb.agrodoa_backend.model.Tipo;
import com.labweb.agrodoa_backend.model.local.Cidade;
import com.labweb.agrodoa_backend.model.Denuncia;
import com.labweb.agrodoa_backend.model.Avaliacao;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuario")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@PrimaryKeyJoinColumn(name = "conta_idconta") // mapeia PK+FK >> exato nome da coluna no banco
@Getter
@Setter
@NoArgsConstructor
public abstract class Usuario extends Conta{
    String cpfOuCnpj;
    String nomeArquivoFoto;
    String telefone;
    int ehVoluntario; //talvez virar boolean?
    
    @ManyToOne
    @JoinColumn(name = "tipo_idtipo", referencedColumnName = "idtipo") //nomes exatamente como no banco
    private Tipo tipoUsuario;

    @ManyToOne
    @JoinColumn(name = "cidade_idcidade", referencedColumnName = "idcidade")
    private Cidade cidade;

    @OneToMany(mappedBy = "denunciado", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Denuncia> denuncias;   //ver o momento da inicialização

    @OneToMany(mappedBy = "avaliado", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Avaliacao> avaliacoes; //ver o momento da inicialização

    //adicionar ainda:
    //opcional: lista de causas

    public Usuario(String nome, String email, String senha, String cpfOuCnpj, String nomeArquivoFoto, String telefone, int ehVoluntario, Tipo tipoUsuario, Cidade cidade) {
        super(nome, email, senha);
        this.cpfOuCnpj = cpfOuCnpj;
        this.nomeArquivoFoto = nomeArquivoFoto;
        this.telefone = telefone;
        this.ehVoluntario = ehVoluntario;
        this.tipoUsuario = tipoUsuario;
        this.cidade = cidade;
    }
}
