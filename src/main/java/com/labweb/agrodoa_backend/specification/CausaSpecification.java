package com.labweb.agrodoa_backend.specification;

import org.springframework.data.jpa.domain.Specification;

import com.labweb.agrodoa_backend.dto.CausaDTO;

public class CausaSpecification {

    public static Specification<CausaDTO> filtrarPorNome(String nome){
        return (root, query, cb) -> {
            if(nome == null || nome.isEmpty()){
                return cb.conjunction();
            }

            return cb.like(cb.lower(root.get("titulo")), "%" + nome.toLowerCase() + "%");
        };
    }

}
