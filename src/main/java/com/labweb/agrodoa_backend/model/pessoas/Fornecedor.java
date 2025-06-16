package com.labweb.agrodoa_backend.model.pessoas;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import lombok.NoArgsConstructor;
import com.labweb.agrodoa_backend.model.*;
import com.labweb.agrodoa_backend.model.local.Cidade;

@NoArgsConstructor
@Entity
@DiscriminatorValue("FORNECEDOR")
@PrimaryKeyJoinColumn(name = "conta_idconta")
public class Fornecedor extends Usuario{
    @OneToMany(mappedBy = "anunciante", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Anuncio> listaAnunciosPostados = new ArrayList<>();

    public Fornecedor(String nome, String email, String senha, String cpfOuCnpj, String nomeArquivoFoto, String telefone, int ehVoluntario, Tipo tipoUsuario, Cidade cidade){
        super(nome, email, senha, cpfOuCnpj, nomeArquivoFoto, telefone, ehVoluntario, tipoUsuario, cidade);
    }

}
