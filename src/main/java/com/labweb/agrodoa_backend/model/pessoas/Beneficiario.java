package com.labweb.agrodoa_backend.model.pessoas;

import java.util.ArrayList;

import com.labweb.agrodoa_backend.model.Tipo;
import com.labweb.agrodoa_backend.model.local.Cidade;
import com.labweb.agrodoa_backend.model.pessoas.comportamento.RecebeAnuncios;
import com.labweb.agrodoa_backend.model.relacoes.RelacaoBeneficiario;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@DiscriminatorValue("BENEFICIARIO")
@PrimaryKeyJoinColumn(name = "conta_idconta")
public class Beneficiario extends Usuario implements RecebeAnuncios{
    @OneToMany(mappedBy = "beneficiario", cascade = CascadeType.ALL, orphanRemoval = true)
    private ArrayList<RelacaoBeneficiario> relacoesAnuncio = new ArrayList<>();

    /*na hora de filtrar fa\er algo tipo:

    public List<Anuncio> getAnunciosSalvos() {
        return relacoes.stream()
            .filter(r -> "I".equals(r.getTipoRelacao()))
            .map(RelacaoBeneficiario::getAnuncio)
            .toList();
    }
        
    public List<Anuncio> getAnunciosEmNegociacao() {
        return relacoes.stream()
            .filter(r -> "N".equals(r.getTipoRelacao()))
            .map(RelacaoBeneficiario::getAnuncio)
            .toList();
    }
    */

    public Beneficiario(String nome, String email, String senha, String cpfOuCnpj, String nomeArquivoFoto, String telefone, Tipo tipoUsuario, Cidade cidade){
        super(nome, email, senha, cpfOuCnpj, nomeArquivoFoto, telefone, cidade, tipoUsuario);
    }

    @Override
    public ArrayList<RelacaoBeneficiario> getRelacoesAnuncios() {
        return this.relacoesAnuncio;    
    }
}
