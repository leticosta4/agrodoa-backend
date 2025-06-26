package com.labweb.agrodoa_backend.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.labweb.agrodoa_backend.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
    boolean existsByIdProduto(Long idProduto);
    Produto findByIdProduto(Long idProduto);
    ArrayList <Produto> findAll(); //talvez enm precise
}
