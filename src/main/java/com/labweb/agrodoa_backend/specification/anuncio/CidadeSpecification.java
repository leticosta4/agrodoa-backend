package com.labweb.agrodoa_backend.specification.anuncio;

import org.springframework.data.jpa.domain.Specification;

import com.labweb.agrodoa_backend.model.Anuncio;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;


public class CidadeSpecification implements Specification<Anuncio> {
    private final String cidade;

    public CidadeSpecification(String cidade){
        this.cidade = cidade;
    }



    @Override
    public Predicate toPredicate(Root<Anuncio> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

         if (cidade == null || cidade.isEmpty()) {
            return cb.conjunction(); 
        }
        return cb.equal(root.get("cidade"), cidade);
    }

}
