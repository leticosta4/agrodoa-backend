package com.labweb.agrodoa_backend.specification.anuncio;

import org.springframework.data.jpa.domain.Specification;

import com.labweb.agrodoa_backend.model.Anuncio;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class NomeSpecification implements Specification<Anuncio> {
    private final String nome;

    
    
    public NomeSpecification(String nome) {
        this.nome = nome;
    }



    @Override
    public Predicate toPredicate(Root<Anuncio> root, CriteriaQuery<?> queyr, CriteriaBuilder cb) {
        
      if (nome == null || nome.isEmpty()) {
            return cb.conjunction(); 
        }

      return cb.like(cb.lower(root.get("nome")), "%" + nome.toLowerCase() + "%");
    }

}
