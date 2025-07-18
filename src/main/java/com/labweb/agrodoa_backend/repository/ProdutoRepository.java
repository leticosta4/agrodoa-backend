package com.labweb.agrodoa_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.labweb.agrodoa_backend.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, String>{
    boolean existsByIdProduto(String idProduto);
    Produto findByIdProduto(String idProduto);
    List <Produto> findAll(); //talvez enm precise
}
