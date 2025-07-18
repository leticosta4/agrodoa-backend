package com.labweb.agrodoa_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.labweb.agrodoa_backend.model.Anuncio;
import com.labweb.agrodoa_backend.model.Negociacao;
import com.labweb.agrodoa_backend.model.enums.StatusNegociacao;
import com.labweb.agrodoa_backend.model.enums.TipoRelacaoBenef;

@Repository
public interface NegociacaoRepository extends JpaRepository<Negociacao, String> { 
   Negociacao findByIdNegociacao(String idNegociacao);
   List<Negociacao> findAllByRelacaoAnuncioAndStatus(Anuncio anuncio, StatusNegociacao status);

    @Query("SELECT n FROM Negociacao n WHERE n.relacao.anuncio.idAnuncio = :idAnuncio " + "AND n.relacao.tipoRelacao = :tipoRelacao " + "AND n.status IN :statusAtivos")
    List<Negociacao> findNegociacoesAtivasPorAnuncio(
        @Param("idAnuncio") String idAnuncio,
        @Param("tipoRelacao") TipoRelacaoBenef tipoRelacao,
        @Param("statusAtivos") List<StatusNegociacao> statusAtivos
    );

}