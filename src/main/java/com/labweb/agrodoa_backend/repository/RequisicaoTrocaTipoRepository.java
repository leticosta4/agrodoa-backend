package com.labweb.agrodoa_backend.repository;

import java.util.ArrayList;

import com.labweb.agrodoa_backend.model.RequisicaoTrocaTipo;

public interface RequisicaoTrocaTipoRepository {
    boolean existsByRequisicaoTrocaTipoId(Long requisicaoTrocaTipoId);
    ArrayList <RequisicaoTrocaTipo> findAll();
    ArrayList <RequisicaoTrocaTipo> findAllByUsuarioId(Long usuarioId);  
    RequisicaoTrocaTipo findByRequisicaoTrocaTipoIdAndUsuarioId(Long requisicaotrocatipoId, Long usuarioId);  //para o adm decidir algo sobre ela
    void removeByRequisicaoTrocaTipoId(Long requisicaoTrocaTipoId);  //talvez pelo id do user tb
}