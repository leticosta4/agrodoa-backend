package com.labweb.agrodoa_backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.labweb.agrodoa_backend.model.Motivo;

@Repository
public interface MotivoRepository extends JpaRepository<Motivo, String> {
    Optional<Motivo> findByNomeIgnoreCase(String nome);
}
