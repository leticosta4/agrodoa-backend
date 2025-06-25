package com.labweb.agrodoa_backend.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.labweb.agrodoa_backend.model.local.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Long>{
    boolean existsByIdCidade(Long idCidade); //ver se precisa mesmo
    ArrayList <Cidade> findAll();
    void removeByIdCidade(Long idCidade);
}
