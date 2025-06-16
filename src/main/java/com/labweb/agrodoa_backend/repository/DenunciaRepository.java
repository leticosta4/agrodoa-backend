package com.labweb.agrodoa_backend.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.labweb.agrodoa_backend.model.Denuncia;

public interface DenunciaRepository extends JpaRepository<Denuncia, Long>{
    boolean existsByDenunciaId(Long denunciaId);
    ArrayList <Denuncia> findAll();
    ArrayList <Denuncia> findAllByDenunciadoId(Long denunciadoId);  
    Denuncia findByDenunciaIdAndDenunciadoId(Long denunciaId, Long denunciadoId);  //para o adm decidir algo sobre ela
}
