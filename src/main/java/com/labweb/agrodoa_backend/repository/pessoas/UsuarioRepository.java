package com.labweb.agrodoa_backend.repository.pessoas;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.labweb.agrodoa_backend.model.Tipo;
import com.labweb.agrodoa_backend.model.pessoas.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, String>{
  boolean existsByIdContaAndTipoUsuario(String idUsuario, Tipo tipoUsuario);
  ArrayList <Usuario> findAll();  //para consulta pelo adm quando tiver -que gerenciar os usuarios
  Optional findUsuarioByIdConta(String idUsuario);  //dependendo do tipo que for ele puxa listas específicas la dentro do service
  void removeByIdConta(String idUsuario);
}
