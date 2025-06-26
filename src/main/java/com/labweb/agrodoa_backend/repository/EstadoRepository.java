package com.labweb.agrodoa_backend.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.labweb.agrodoa_backend.model.local.Estado;
import com.labweb.agrodoa_backend.model.projection.EstadoProjection;

public interface EstadoRepository extends JpaRepository<Estado, Long>{
    boolean existsByIdEstado(Long idEstado); //ver se precisa mesmo
    ArrayList <EstadoProjection> findAllBy();
    void removeByIdEstado(Long idEstado);
}
