package com.labweb.agrodoa_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.labweb.agrodoa_backend.model.Avaliacao;
import com.labweb.agrodoa_backend.model.contas.Usuario;


public interface AvaliacaoRepository extends JpaRepository<Avaliacao, String>{
    boolean existsByIdAvaliacao(String idAvaliacao); //ver se precisa mesmo
    List <Avaliacao> findAll();
    List <Avaliacao> findAllByAvaliado(Usuario avaliado);
    Avaliacao findByIdAvaliacao(String idAvaliacao); //para poder editar

    Avaliacao findByAvaliadorAndAvaliado(Usuario avaliador, Usuario avaliado);

    //precisa:?
    // Avaliacao findAllByIdAvaliacaoAndIdAvaliado(String idAvaliacao, String idAvaliado);
    // Avaliacao findAllByAvaliadorId(String idAvaliador);
}
