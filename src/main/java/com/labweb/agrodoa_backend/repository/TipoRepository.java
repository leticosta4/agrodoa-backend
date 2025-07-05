package com.labweb.agrodoa_backend.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.labweb.agrodoa_backend.model.Tipo;

public interface TipoRepository extends JpaRepository<Tipo, String>{
    boolean existsByIdTipo(String idTipo); //ver se precisa mesmo
    ArrayList <Tipo> findAll();
    Tipo findByNome(String nome);
}