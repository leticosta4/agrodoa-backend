package com.labweb.agrodoa_backend.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.labweb.agrodoa_backend.model.local.Estado;

public interface EstadoRepository extends JpaRepository<Estado, Long>{
    boolean existsByEstadoId(Long estadoId); //ver se precisa mesmo
    ArrayList <Estado> findAll();
    void removeByEstadoId(Long estadoId);
}
