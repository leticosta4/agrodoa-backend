package com.labweb.agrodoa_backend.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.labweb.agrodoa_backend.model.Tipo;
import com.labweb.agrodoa_backend.model.pessoas.Usuario;
import com.labweb.agrodoa_backend.dto.UsuarioDTO;


public interface UsuarioRepository extends JpaRepository<Usuario, String>{
  boolean existsByIdAndTipoUsuario(String id, Tipo tipoUsuario);
  ArrayList <UsuarioDTO> findAllBy();  //para consulta pelo adm quando tiver -que gerenciar os usuarios
  UsuarioDTO findUsuarioById(String usuarioId);  //dependendo do tipo que for ele puxa listas específicas la dentro do service
  void removeById(String usuarioId);
}
