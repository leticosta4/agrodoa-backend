package com.labweb.agrodoa_backend.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.labweb.agrodoa_backend.model.RequisicaoTrocaTipo;
import com.labweb.agrodoa_backend.model.pessoas.Usuario;

public interface RequisicaoTrocaTipoRepository extends JpaRepository<RequisicaoTrocaTipo, Long>{
    boolean existsByIdRequisicaoTrocaTipo(Long idRequisicaoTrocaTipoId);
    ArrayList <RequisicaoTrocaTipo> findAll();
    ArrayList <RequisicaoTrocaTipo> findAllByUsuario(Usuario usuario);  
    RequisicaoTrocaTipo findByIdRequisicaoTrocaTipoAndUsuario(Long idRequisicaoTrocaTipoId, Usuario usuario);  //para o adm decidir algo sobre ela
    void removeByIdRequisicaoTrocaTipo(Long idRequisicaoTrocaTipoId);  //talvez pelo id do user tb
}