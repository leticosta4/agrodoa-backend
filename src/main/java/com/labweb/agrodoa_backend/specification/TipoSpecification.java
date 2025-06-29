package com.labweb.agrodoa_backend.specification;

import org.springframework.data.jpa.domain.Specification;

import com.labweb.agrodoa_backend.model.Tipo;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class TipoSpecification<T> implements Specification<T>{
    private final Tipo tipo;

    public TipoSpecification(Tipo tipo) {
        this.tipo = tipo;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
         if (tipo == null || tipo.getNome().isEmpty()) {
            return cb.conjunction();
        }
        return cb.equal(root.get("tipo"), tipo.getNome().toLowerCase());
    }

}
