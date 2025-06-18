package com.labweb.agrodoa_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.labweb.agrodoa_backend.model.pessoas.Administrador;

import java.util.ArrayList;

public interface AdmRepository extends JpaRepository<Administrador, Long>{
    // boolean existsByContaId(Long administradorId);
    // ArrayList <Administrador> findAll();  //para o sobre
    // void removeByContaId(Long administradorId);

    //ver como remodelar pq não temos o atributo contaId nem administradorId na classe Adm >> deve acontecer o mesmo para os tipos de usuario
}
