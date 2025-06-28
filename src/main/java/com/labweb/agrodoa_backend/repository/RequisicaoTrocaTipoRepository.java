package com.labweb.agrodoa_backend.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.labweb.agrodoa_backend.model.RequisicaoTrocaTipo;
import com.labweb.agrodoa_backend.model.pessoas.Usuario;

public interface RequisicaoTrocaTipoRepository extends JpaRepository<RequisicaoTrocaTipo, String>{
    boolean existsByIdRequisicaoTrocaTipo(String idRequisicaoTrocaTipoId);
    ArrayList <RequisicaoTrocaTipo> findAll();
    ArrayList <RequisicaoTrocaTipo> findAllByUsuario(Usuario usuario);  
    RequisicaoTrocaTipo findByIdRequisicaoTrocaTipoAndUsuario(String idRequisicaoTrocaTipoId, Usuario usuario);  //para o adm decidir algo sobre ela
    void removeByIdRequisicaoTrocaTipo(String idRequisicaoTrocaTipoId);  //talvez pelo id do user tb
}