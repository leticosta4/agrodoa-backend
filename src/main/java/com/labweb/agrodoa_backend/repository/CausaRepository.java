package com.labweb.agrodoa_backend.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.labweb.agrodoa_backend.model.Causa;

public interface CausaRepository extends JpaRepository<Causa, String>{
    boolean existsByIdCausa(String idCausa);
    Causa findByIdCausa(String idCausa); //p ver causa específica e finalizar a causa
    ArrayList <Causa> findAll();
    ArrayList <Causa> findAllByIdCausa(String idCausa);

    @Query(value = "SELECT * FROM causa WHERE LOWER(nome) LIKE CONCAT('%', :pesquisa, '%') AND status_causa = 'A';", nativeQuery = true)
    ArrayList<Causa> findByTituloContaining(String pesquisa); //barra de pesquisa mas precisa refinar talvez

    @Query(value = "SELECT * FROM causa WHERE meta < :meta AND status_causa = 'A';", nativeQuery = true)
    ArrayList<Causa> findByMetaMenorQue(Double meta); 

    @Query(value = "SELECT * FROM causa WHERE meta > :meta AND status_causa = 'A';", nativeQuery = true)
    ArrayList<Causa> findByMetaMaiorQue(Double meta);

    @Query(value = "SELECT * FROM causa WHERE status_causa = 'C';", nativeQuery = true)
    ArrayList<Causa> findAllBystatus(); //causas concluidas (caso sirva)
}
