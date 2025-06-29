package com.labweb.agrodoa_backend.specification;

import org.springframework.data.jpa.domain.Specification;

import com.labweb.agrodoa_backend.model.Anuncio;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class TipoSpecification<T> implements Specification<T>{
    private final String tipo;

    public TipoSpecification(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
         if (tipo == null || tipo.isEmpty()) {
            return cb.conjunction();
        }
        return cb.equal(root.get("tipo"), tipo.toLowerCase());
    }

}
