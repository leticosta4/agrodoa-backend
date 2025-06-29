package com.labweb.agrodoa_backend.specification.anuncio;

import java.math.BigDecimal;

import org.springframework.data.jpa.domain.Specification;

import com.labweb.agrodoa_backend.model.Anuncio;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class PrecoMinimoSpecification implements Specification<Anuncio>{
    private final BigDecimal precoMin;


    
    public PrecoMinimoSpecification(BigDecimal precoMin) {
        this.precoMin = precoMin;
    }



    @Override
    public Predicate toPredicate(Root<Anuncio> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        if(precoMin == null){
            return cb.conjunction();
        }    

        return cb.greaterThanOrEqualTo(root.get("preco"), precoMin);
    }



}
