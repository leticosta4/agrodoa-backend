package com.labweb.agrodoa_backend.repository.relacoes;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.labweb.agrodoa_backend.model.Anuncio;
import com.labweb.agrodoa_backend.model.enums.TipoRelacaoBenef;
import com.labweb.agrodoa_backend.model.relacoes.IdRelacaoBeneficiario;
import com.labweb.agrodoa_backend.model.relacoes.RelacaoBeneficiario;

public interface RelacaoBeneficiarioRepository extends JpaRepository<RelacaoBeneficiario, IdRelacaoBeneficiario> {
    boolean existsByIdRelacao(IdRelacaoBeneficiario idRelacao);

    @Query("SELECT r.anuncio FROM RelacaoBeneficiario r WHERE r.beneficiario.idConta = :beneficiarioId AND r.tipoRelacao = :tipoRelacao")
    List<Anuncio> findAnunciosByIdBeneficiarioAndTipo(String beneficiarioId, TipoRelacaoBenef tipoRelacao);
    
    @Query("SELECT COUNT(r) FROM RelacaoBeneficiario r WHERE r.anuncio.idAnuncio = :anuncioId AND r.tipoRelacao = :tipo")
    long countByAnuncioIdAndTipoRelacao(@Param("anuncioId") String anuncioId, @Param("tipo") TipoRelacaoBenef tipo);
}
