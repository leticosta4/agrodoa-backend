package com.labweb.agrodoa_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.labweb.agrodoa_backend.model.relacoes.IdRelacaoBeneficiario;
import com.labweb.agrodoa_backend.model.relacoes.RelacaoBeneficiario;

public interface RelacaoBeneficiarioRepository extends JpaRepository<RelacaoBeneficiario, IdRelacaoBeneficiario> {
    //as relacoes de anuncio que vao depender de filtros pelo tipo da relacao
    //S para salvos 
    //N para em negociacao
}
