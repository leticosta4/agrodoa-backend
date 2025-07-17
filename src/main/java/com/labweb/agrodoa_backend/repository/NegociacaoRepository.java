package com.labweb.agrodoa_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.labweb.agrodoa_backend.model.Negociacao;

@Repository
public interface NegociacaoRepository extends JpaRepository<Negociacao, String> { 
   
}