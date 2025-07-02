package com.labweb.agrodoa_backend.specification;

import org.springframework.data.jpa.domain.Specification;

import com.labweb.agrodoa_backend.model.pessoas.Usuario;

public class UsuarioSpecification {    //falta diminuir os repositories com specifications que a gente já tem
    public static Specification<Usuario> filtrarPorTipo(String tipo) {
        return (root, query, cb) -> {
            if (tipo == null || tipo.isEmpty()) {
                return cb.conjunction();
            }
            return cb.equal(cb.lower(root.get("tipoUsuario").get("nome")), tipo.toLowerCase());
        };
    }
}
