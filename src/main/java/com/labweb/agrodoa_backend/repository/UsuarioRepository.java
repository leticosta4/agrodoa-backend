package com.labweb.agrodoa_backend.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.labweb.agrodoa_backend.model.Tipo;
import com.labweb.agrodoa_backend.model.pessoas.Usuario;
import com.labweb.agrodoa_backend.model.projection.UsuarioProjection;


public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
  boolean existsByIdAndTipoUsuario(Long id, Tipo tipoUsuario);
  ArrayList <UsuarioProjection> findAllBy();  //para consulta pelo adm quando tiver -que gerenciar os usuarios
  UsuarioProjection findUsuarioById(Long usuarioId);  //dependendo do tipo que for ele puxa listas específicas la dentro do service
  void removeById(Long usuarioId);
}
