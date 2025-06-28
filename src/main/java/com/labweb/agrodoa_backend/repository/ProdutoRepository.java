package com.labweb.agrodoa_backend.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.labweb.agrodoa_backend.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, String>{
    boolean existsByIdProduto(String idProduto);
    Produto findByIdProduto(String idProduto);
    ArrayList <Produto> findAll(); //talvez enm precise
}
