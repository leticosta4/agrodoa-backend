package com.labweb.agrodoa_backend.repository.pessoas;

import com.labweb.agrodoa_backend.model.pessoas.Conta;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaRepository extends JpaRepository<Conta, String>{
  boolean existsByIdConta(String idConta);
  Optional<Conta> findByEmail(String email);
    
}
