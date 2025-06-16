package com.labweb.agrodoa_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.labweb.agrodoa_backend.model.pessoas.Administrador;

import java.util.ArrayList;

public interface AdmRepository extends JpaRepository<Administrador, Long>{
    boolean existsByAdministradorId(Long administradorId);
    ArrayList <Administrador> findAll();  //para o sobre
    void removeByAdministradorId(Long administradorId);
}
