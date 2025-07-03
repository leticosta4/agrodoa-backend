package com.labweb.agrodoa_backend.repository.pessoas;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.labweb.agrodoa_backend.model.Tipo;
import com.labweb.agrodoa_backend.model.pessoas.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, String>, JpaSpecificationExecutor<Usuario>{
  boolean existsByIdContaAndTipoUsuario(String idUsuario, Tipo tipoUsuario);
  ArrayList <Usuario> findAll();  //para consulta pelo adm quando tiver -que gerenciar os usuarios
  ArrayList <Usuario> findAllByTipoUsuario_NomeIgnoreCase(String nomeTipo); //para consulta fitrada pelo adm 
  Optional<Usuario> findUsuarioByIdConta(String idUsuario);  //dependendo do tipo que for ele puxa listas específicas la dentro do service
  Usuario findByEmail(String email);
  void removeByIdConta(String idUsuario);
}
