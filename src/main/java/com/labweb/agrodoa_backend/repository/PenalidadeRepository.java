package com.labweb.agrodoa_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.labweb.agrodoa_backend.model.Penalidade;
import com.labweb.agrodoa_backend.model.pessoas.Usuario;

public interface PenalidadeRepository extends JpaRepository<Penalidade, Long> {
    long countByUsuario(Usuario usuario);
}
