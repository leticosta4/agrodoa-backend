package com.labweb.agrodoa_backend.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.labweb.agrodoa_backend.model.Avaliacao;


public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long>{
    boolean existsByAvaliacaoId(Long avaliacaoId); //ver se precisa mesmo
    ArrayList <Avaliacao> findAll();
    ArrayList <Avaliacao> findAllByAvaliadoId(Long avaliadoId);
    Avaliacao findByIdAvaliacao(Long idAvaliacao); //para poder editar

    //precisa:?
    // Avaliacao findAllByAvaliacaoIdAndAvaliadoId(Long avaliacaoId, Long idAvaliado);
    // Avaliacao findAllByAvaliadorId(Long idAvaliador);
}
