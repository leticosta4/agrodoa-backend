package com.labweb.agrodoa_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.labweb.agrodoa_backend.model.pessoas.Administrador;

import java.util.ArrayList;

public interface AdmRepository extends JpaRepository<Administrador, String>{
    boolean existsById(String administradorId);
    ArrayList <Administrador> findAll();  //para o sobre
    void removeById(String administradorId);

    //colocando só o Id em vez de AdministradorId ou ContaId pq o spring reconhece Id como padrão e no nosso caso é algo herdado
}
