package com.labweb.agrodoa_backend.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.labweb.agrodoa_backend.model.Avaliacao;
import com.labweb.agrodoa_backend.model.pessoas.Usuario;


public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long>{
    boolean existsByIdAvaliacao(Long idAvaliacao); //ver se precisa mesmo
    ArrayList <Avaliacao> findAll();
    ArrayList <Avaliacao> findAllByAvaliado(Usuario avaliado);
    Avaliacao findByIdAvaliacao(Long idAvaliacao); //para poder editar

    //precisa:?
    // Avaliacao findAllByIdAvaliacaoAndIdAvaliado(Long idAvaliacao, Long idAvaliado);
    // Avaliacao findAllByAvaliadorId(Long idAvaliador);
}
