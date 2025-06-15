package com.labweb.agrodoa_backend.model.pessoas;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;

import com.labweb.agrodoa_backend.model.*;
import com.labweb.agrodoa_backend.model.local.Cidade;

@Entity
@DiscriminatorValue("HIBRIDO")
@PrimaryKeyJoinColumn(name = "conta_idconta")
public class Hibrido extends Usuario{
    @OneToMany(mappedBy = "anunciante", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Anuncio> listaAnunciosPostados = new ArrayList<>();

    @ManyToMany
    @JoinTable(
        name = "relacao_beneficiario",
        joinColumns = @JoinColumn(name = "beneficiario_id"),
        inverseJoinColumns = @JoinColumn(name = "anuncio_id")
    )
    private List<Anuncio> anunciosSalvos = new ArrayList<>();

    public Hibrido() {}

    public Hibrido(String nome, String email, String senha, String cpfOuCnpj, String nomeArquivoFoto, String telefone, int ehVoluntario, Tipo tipoUsuario, Cidade cidade){
        super(nome, email, senha, cpfOuCnpj, nomeArquivoFoto, telefone, ehVoluntario, tipoUsuario, cidade);
    }
}
