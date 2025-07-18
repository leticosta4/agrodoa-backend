package com.labweb.agrodoa_backend.specification;

import java.time.LocalDate;

import org.springframework.data.jpa.domain.Specification;

import com.labweb.agrodoa_backend.model.Anuncio;
import com.labweb.agrodoa_backend.model.enums.StatusAnuncio;

public class AnuncioSpecification { //falta diminuir os repositories com specifications que a gente já tem

    public static Specification<Anuncio> filtrarPorNome(String nome) {
        return (root, query, cb) -> {
            if (nome == null || nome.isEmpty()) {
                return cb.conjunction();
            }
            return cb.like(cb.lower(root.get("titulo")), "%" + nome.toLowerCase() + "%");
        };
    }

    public static Specification<Anuncio> filtrarPorCidade(String cidade) {
        return (root, query, cb) -> {
            if (cidade == null || cidade.isEmpty()) {
                return cb.conjunction();
            }
            return cb.like(cb.lower(root.get("cidade").get("nome")), "%" + cidade.toLowerCase() + "%");
        };
    }

    public static Specification<Anuncio> filtrarPorStatus(StatusAnuncio status) {
        return (root, query, cb) -> {
            if (status == null ) {
                return cb.conjunction();
            }
            return cb.equal(root.get("status"), status.name());
        };
    }

    public static Specification<Anuncio> filtrarPorDataExpiracao(LocalDate data){
        return (root, query, cb) -> {
            if(data == null){
                return cb.conjunction();
            }
            return cb.greaterThanOrEqualTo(root.get("dataExpiracao"), data);
        };
    }

    public static Specification<Anuncio> filtrarPorAnuncianteId(String idAnunciante){
        return ( root, query, cb) -> {
            if(idAnunciante == null || idAnunciante.isEmpty()){
                return cb.conjunction();
            }

            return cb.equal(root.get("anunciante").get("idConta"), idAnunciante );
        };
    }

    
}
