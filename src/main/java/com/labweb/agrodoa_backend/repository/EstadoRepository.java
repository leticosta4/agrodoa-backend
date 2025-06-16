package com.labweb.agrodoa_backend.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.labweb.agrodoa_backend.model.projection.EstadoProjection;

public interface EstadoRepository extends JpaRepository<EstadoProjection, Long>{
    boolean existsByEstadoId(Long estadoId); //ver se precisa mesmo
    ArrayList <EstadoProjection> findAll();
    void removeByEstadoId(Long estadoId);
}
