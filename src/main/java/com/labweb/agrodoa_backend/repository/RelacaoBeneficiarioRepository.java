package com.labweb.agrodoa_backend.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.labweb.agrodoa_backend.model.enums.TipoRelacaoBenef;
import com.labweb.agrodoa_backend.model.relacoes.IdRelacaoBeneficiario;
import com.labweb.agrodoa_backend.model.relacoes.RelacaoBeneficiario;

public interface RelacaoBeneficiarioRepository extends JpaRepository<RelacaoBeneficiario, IdRelacaoBeneficiario> {
    boolean existsByIdRelacao(IdRelacaoBeneficiario idRelacao);

    @Query(value = "SELECT anuncio_idanuncio FROM relacao_beneficiario WHERE usuario_conta_idconta = :beneficiarioId AND tipo_relacao_interessado = :tipoRelacao;",
       nativeQuery = true)
    ArrayList <String> findAllByIdBeneficiarioAndTipoRelacao(String beneficiarioId, TipoRelacaoBenef tipoRelacao);  //lista salvos por user
    
    @Query(value = "SELECT usuario_conta_idconta FROM relacao_beneficiario WHERE anuncio_idanuncio = :anuncioId AND tipo_relacao_interessado = :tipoRelacao;",
       nativeQuery = true)
    ArrayList <String> findAllByIdAnuncioAndTipoRelacao(String anuncioId, TipoRelacaoBenef tipoRelacao);  //lista de negociantes por anuncio
    
    //ver aqui como vai associar o item do enum a um caracter 'S' ou 'N'
    //ainda precisa testar
}
