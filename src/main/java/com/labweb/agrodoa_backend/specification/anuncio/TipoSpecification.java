package com.labweb.agrodoa_backend.specification.anuncio;

import org.springframework.data.jpa.domain.Specification;

import com.labweb.agrodoa_backend.model.Anuncio;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class TipoSpecification implements Specification<Anuncio>{
    private final String tipo;

    public TipoSpecification(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public Predicate toPredicate(Root<Anuncio> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
         if (tipo == null || tipo.isEmpty()) {
            return cb.conjunction();
        }
        return cb.equal(root.get("tipo"), tipo.toLowerCase());
    }

}
