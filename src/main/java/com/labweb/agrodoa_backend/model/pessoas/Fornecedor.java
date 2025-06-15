package com.labweb.agrodoa_backend.model.pessoas;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;

@Entity
public class Fornecedor extends Usuario{
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Anuncio> listaAnunciosPostados = new ArrayList<>();

    public Fornecedor(){}

    //talvez fazer um construtor sem a lista de anuncios para ficar mais facil na busca e envio p o front

    public Fornecedor(String nome, String email, String senha, String cpfOuCnpj, String nomeArquivoFoto, String telefone, int ehVoluntario, Tipo tipoUsuario, List<Anuncio> listaAnunciosPostados){
        super(nome, email, senha, cpfOuCnpj, nomeArquivoFoto, telefone, ehVoluntario, tipoUsuario);
        this.listaAnunciosPostados = new ArrayList<>();                                                                                                                                             
    }

}
