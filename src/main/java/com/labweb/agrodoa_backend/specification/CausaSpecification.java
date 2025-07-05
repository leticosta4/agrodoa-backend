package com.labweb.agrodoa_backend.specification;

import org.springframework.data.jpa.domain.Specification;

import com.labweb.agrodoa_backend.dto.CausaDTO;
import com.labweb.agrodoa_backend.model.Causa;

public class CausaSpecification {

    public static Specification<Causa> filtrarPorNome(String nome) {
        return (root, query, cb) -> {
            if (nome == null || nome.isEmpty()) {
                return cb.conjunction();
            }
            
            return cb.like(cb.lower(root.get("nome")), "%" + nome.toLowerCase() + "%");
        };
    }

    public static Specification<Causa> filtrarPorMetaMin(Double metaMin) {
        return (root, query, cb) -> {
            if (metaMin == null) {
                return cb.conjunction(); 
            }

            return cb.greaterThanOrEqualTo(root.get("meta"), metaMin);
        };
    }

    public static Specification<Causa> filtrarPorMetaMax(Double metaMax) {
        return (root, query, cb) -> {
            if (metaMax == null) {
                return cb.conjunction();
            }

            return cb.lessThanOrEqualTo(root.get("meta"), metaMax);
        };
    }

}   
