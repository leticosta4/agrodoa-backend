package com.labweb.agrodoa_backend.model.contas;

import java.util.ArrayList;
import java.util.List;

import com.labweb.agrodoa_backend.model.Tipo;
import com.labweb.agrodoa_backend.model.local.Cidade;
import com.labweb.agrodoa_backend.model.relacoes.DoacaoCausa;
import com.labweb.agrodoa_backend.model.relacoes.RelacaoBeneficiario;
import com.labweb.agrodoa_backend.model.Denuncia;
import com.labweb.agrodoa_backend.model.Anuncio;
import com.labweb.agrodoa_backend.model.Avaliacao;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
@DiscriminatorValue("USUARIO")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@PrimaryKeyJoinColumn(name = "conta_idconta") // mapeia PK+FK >> exato nome da coluna no banco
@Getter
@Setter
@NoArgsConstructor
public class Usuario extends Conta{
    @Column(name = "cpf_ou_cnpj")
    String cpfOuCnpj;

    @Column(name = "nome_arquivo_foto")
    String nomeArquivoFoto;

    @Column(name = "telefone")
    String telefone;

    @ManyToOne
    @JoinColumn(name = "cidade_idcidade", referencedColumnName = "idcidade")
    private Cidade cidade;

    @ManyToOne(fetch = FetchType.EAGER) //retorna o tipo do usuario imediatamente logo com ele
    @JoinColumn(name = "tipo_idtipo", referencedColumnName = "idtipo") //nomes exatamente como no banco
    private Tipo tipoUsuario;
    
    //ver o momento da inicialização dessas 3 listas abaixo
    @OneToMany(mappedBy = "denunciado", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Denuncia> denuncias;

    @OneToMany(mappedBy = "avaliado", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Avaliacao> avaliacoes; 

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DoacaoCausa> doacoesCausas;

    @OneToMany(mappedBy = "anunciante", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Anuncio> listaAnunciosPostados = new ArrayList<>(); //caso seja fornecedor ou hibrido

    @OneToMany(mappedBy = "beneficiario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RelacaoBeneficiario> relacoesAnuncio = new ArrayList<>(); //caso seja beneficiario ou hibrido

    public Usuario(String nome, String email, String senha, String cpfOuCnpj, String nomeArquivoFoto, String telefone, Cidade cidade, Tipo tipoUsuario) {
        super(nome, email, senha);
        this.cpfOuCnpj = cpfOuCnpj;
        this.nomeArquivoFoto = nomeArquivoFoto;
        this.telefone = telefone;
        this.cidade = cidade;
        this.tipoUsuario = tipoUsuario;
    }

    @Override
    public List<String> getRoles() {
        if (this.tipoUsuario == null || this.tipoUsuario.getNome() == null) {
            return List.of("ROLE_USER");
        }

        switch (this.tipoUsuario.getNome()) {
            case "fornecedor":
                return List.of("ROLE_FORNECEDOR");
            case "beneficiario":
                return List.of("ROLE_BENEFICIARIO");
            case "hibrido":
                return List.of("ROLE_FORNECEDOR", "ROLE_BENEFICIARIO");
            default:
                return List.of("ROLE_USUARIO");
        }
    }
}

//tem os overrides ainda