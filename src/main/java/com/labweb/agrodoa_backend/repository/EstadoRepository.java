package com.labweb.agrodoa_backend.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.labweb.agrodoa_backend.model.local.Estado;

public interface EstadoRepository extends JpaRepository<Estado, Long>{
    boolean existsByIdEstado(Long idEstado); //ver se precisa mesmo
    ArrayList <Estado> findAll();
    void removeByIdEstado(Long idEstado);
}
