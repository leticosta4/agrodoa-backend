package com.labweb.agrodoa_backend.model.pessoas;

import java.util.ArrayList;
import java.util.List;

import com.labweb.agrodoa_backend.model.Tipo;
import com.labweb.agrodoa_backend.model.local.Cidade;
import com.labweb.agrodoa_backend.model.relacoes.Negociacao;
import com.labweb.agrodoa_backend.model.relacoes.RelacaoBeneficiario;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;

@Entity
@DiscriminatorValue("BENEFICIARIO")
@PrimaryKeyJoinColumn(name = "conta_idconta")
public class Beneficiario extends Usuario{
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Negociacao> anunciosEmNegociacao;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RelacaoBeneficiario> relacoesAnuncio = new ArrayList<>();


    /*na horade filtrar fa\er algo tipo:

    public List<Anuncio> getAnunciosSalvos() {
        return relacoes.stream()
            .filter(r -> "I".equals(r.getTipoRelacao()))
            .map(RelacaoBeneficiario::getAnuncio)
            .toList();
    }
    */


    public Beneficiario(){}

    //talvez fazer um construtor sem a lista de anuncios para ficar mais facil na busca e envio p o front

    public Beneficiario(String nome, String email, String senha, String cpfOuCnpj, String nomeArquivoFoto, String telefone, int ehVoluntario, Tipo tipoUsuario, Cidade cidade){
        super(nome, email, senha, cpfOuCnpj, nomeArquivoFoto, telefone, ehVoluntario, tipoUsuario, cidade);
    }
}
