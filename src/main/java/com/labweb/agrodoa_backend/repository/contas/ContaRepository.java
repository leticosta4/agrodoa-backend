package com.labweb.agrodoa_backend.repository.contas;

import com.labweb.agrodoa_backend.model.contas.Conta;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ContaRepository extends JpaRepository<Conta, String>{
  boolean existsByIdConta(String idConta);
  Optional<Conta> findByEmail(String email);
  String findEmailByIdConta(String idConta);

  @Query("SELECT c.idConta FROM Conta c WHERE c.email = :email")
  String findIdContaByEmail(@Param("email") String email);
}
