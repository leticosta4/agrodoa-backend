package com.labweb.agrodoa_backend.repository.local;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.labweb.agrodoa_backend.model.local.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, String>{
    boolean existsByIdCidade(String idCidade); //ver se precisa mesmo
    ArrayList <Cidade> findAll();
    ArrayList <Cidade> findAllByEstado_IdEstado(String idEstado);
    Cidade findByIdCidade(String idCidade);
    void removeByIdCidade(String idCidade);
}