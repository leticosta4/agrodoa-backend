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

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@DiscriminatorValue("BENEFICIARIO")
@PrimaryKeyJoinColumn(name = "conta_idconta")
public class Beneficiario extends Usuario{
    @OneToMany(mappedBy = "beneficiado", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Negociacao> anunciosEmNegociacao;

    @OneToMany(mappedBy = "beneficiario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RelacaoBeneficiario> relacoesAnuncio = new ArrayList<>();


    /*na hora de filtrar fa\er algo tipo:

    public List<Anuncio> getAnunciosSalvos() {
        return relacoes.stream()
            .filter(r -> "I".equals(r.getTipoRelacao()))
            .map(RelacaoBeneficiario::getAnuncio)
            .toList();
    }
    */

    public Beneficiario(String nome, String email, String senha, String cpfOuCnpj, String nomeArquivoFoto, String telefone, int ehVoluntario, Tipo tipoUsuario, Cidade cidade){
        super(nome, email, senha, cpfOuCnpj, nomeArquivoFoto, telefone, ehVoluntario, tipoUsuario, cidade);
    }
}
