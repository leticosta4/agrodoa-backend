package com.labweb.agrodoa_backend.specification;

import org.springframework.data.jpa.domain.Specification;

import com.labweb.agrodoa_backend.model.Causa;
import com.labweb.agrodoa_backend.model.enums.StatusCausa;

public class CausaSpecification {

    public static Specification<Causa> filtrarPorNome(String nome) {
        return (root, query, cb) -> {
            if (nome == null || nome.isEmpty()) {
                return cb.conjunction();
            }
            
            return cb.like(cb.lower(root.get("nome")), "%" + nome.toLowerCase() + "%");
        };
    }

    public static Specification<Causa> filtrarPorStatus(StatusCausa statusCausa) { //esse é o geral
        return (root, query, cb) -> {
            if (statusCausa == null || statusCausa.name().isEmpty()) {
                return cb.conjunction();
            }
            
            return cb.like(cb.lower(root.get("status")), "%" + statusCausa.name().toLowerCase() + "%");
        };
    }
}   
