package com.labweb.agrodoa_backend.model.pessoas;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.labweb.agrodoa_backend.model.Anuncio;
import com.labweb.agrodoa_backend.model.Tipo;
import com.labweb.agrodoa_backend.model.local.Cidade;
import com.labweb.agrodoa_backend.model.pessoas.comportamento.*;
import com.labweb.agrodoa_backend.model.relacoes.RelacaoBeneficiario;

@NoArgsConstructor
@Getter
@Setter
@Entity
@DiscriminatorValue("HIBRIDO")
@PrimaryKeyJoinColumn(name = "conta_idconta")
public class Hibrido extends Usuario implements PublicaAnuncios, RecebeAnuncios{  //melhorar isso ainda
    @OneToMany(mappedBy = "anunciante", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Anuncio> listaAnunciosPostados = new ArrayList<>(); 

    @OneToMany(mappedBy = "beneficiario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RelacaoBeneficiario> relacoesAnuncio = new ArrayList<>();

    public Hibrido(String nome, String email, String senha, String cpfOuCnpj, String nomeArquivoFoto, String telefone, Tipo tipoUsuario, Cidade cidade){
        super(nome, email, senha, cpfOuCnpj, nomeArquivoFoto, telefone, cidade, tipoUsuario);
    }

    @Override
    public List<RelacaoBeneficiario> getRelacoesAnuncios() {
        return this.relacoesAnuncio;    
    }

    @Override
    public List<Anuncio> getAnunciosPostados() {
        return this.listaAnunciosPostados;    
    }
}
