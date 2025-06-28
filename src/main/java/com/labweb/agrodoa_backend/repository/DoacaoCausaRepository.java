package com.labweb.agrodoa_backend.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.labweb.agrodoa_backend.model.relacoes.DoacaoCausa;
import com.labweb.agrodoa_backend.model.relacoes.IdDoacaoCausa;


public interface DoacaoCausaRepository extends JpaRepository<DoacaoCausa, IdDoacaoCausa>{
    boolean existsByIdDoacao(IdDoacaoCausa idDoacao);

    @Query(value = "SELECT causa_idcausa FROM usuario_has_causa WHERE usuario_conta_idconta = :idConta", nativeQuery = true) //depois adicionar um join com causa provavelmente para colocar AND causa.status_causa = 'A';
    ArrayList<String> findAllByUsuario(@Param("idConta") String idConta);  //causas em que um usuario ta investindo

    @Query(value = "SELECT usuario_conta_idconta FROM usuario_has_causa WHERE causa_idcausa = :idCausa", nativeQuery = true) //depois adicionar um join com causa provavelmente para colocar AND causa.status_causa = 'A';
    ArrayList<String> findAllByCausa(@Param("idCausa") String idCausa);  //usuarios contribuindo para uma causa
} 