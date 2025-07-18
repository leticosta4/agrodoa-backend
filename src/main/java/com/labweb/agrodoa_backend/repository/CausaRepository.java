package com.labweb.agrodoa_backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.labweb.agrodoa_backend.model.Causa;

public interface CausaRepository extends JpaRepository<Causa, String> , JpaSpecificationExecutor<Causa>{
    boolean existsByIdCausa(String idCausa);
    Optional<Causa> findByIdCausa(String idCausa); //p ver causa específica e finalizar a causa
    List <Causa> findAll();
    List <Causa> findAllByIdCausa(String idCausa);
    List<Causa> findAllByCriador_IdConta(String idCriador);

    @Query(value = "SELECT * FROM causa WHERE LOWER(nome) LIKE CONCAT('%', :pesquisa, '%') AND status_causa = 'A';", nativeQuery = true)
    List<Causa> findByTituloContaining(String pesquisa); //barra de pesquisa mas precisa refinar talvez

    @Query(value = "SELECT * FROM causa WHERE status_causa = :status;", nativeQuery = true)
    List<Causa> findAllByStatus(String status); //causas concluidas (caso sirva)
}
