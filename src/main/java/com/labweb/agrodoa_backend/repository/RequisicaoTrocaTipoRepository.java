package com.labweb.agrodoa_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.labweb.agrodoa_backend.model.RequisicaoTrocaTipo;
import com.labweb.agrodoa_backend.model.contas.Usuario;

public interface RequisicaoTrocaTipoRepository extends JpaRepository<RequisicaoTrocaTipo, String>{
    boolean existsByIdRequisicaoTrocaTipo(String idRequisicaoTrocaTipoId);
    List <RequisicaoTrocaTipo> findAll();
    List <RequisicaoTrocaTipo> findAllByUsuario(Usuario usuario);  
    RequisicaoTrocaTipo findByIdRequisicaoTrocaTipoAndUsuario(String idRequisicaoTrocaTipoId, Usuario usuario);  //para o adm decidir algo sobre ela
    void removeByIdRequisicaoTrocaTipo(String idRequisicaoTrocaTipoId);  //talvez pelo id do user tb
}