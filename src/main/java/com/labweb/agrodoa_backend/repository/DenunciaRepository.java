package com.labweb.agrodoa_backend.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.labweb.agrodoa_backend.model.Denuncia;
import com.labweb.agrodoa_backend.model.pessoas.Usuario;

public interface DenunciaRepository extends JpaRepository<Denuncia, Long>{
    boolean existsByIdDenuncia(Long idDenuncia);
    ArrayList <Denuncia> findAll();
    ArrayList <Denuncia> findAllByDenunciado(Usuario denunciado);  
    Denuncia findByIdDenunciaAndDenunciado(Long idDenuncia, Usuario denunciado);  //para o adm decidir algo sobre ela
}
