package com.labweb.agrodoa_backend.specification;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.data.jpa.domain.Specification;

import com.labweb.agrodoa_backend.model.Anuncio;
import com.labweb.agrodoa_backend.model.enums.StatusAnuncio;
import com.labweb.agrodoa_backend.model.enums.TipoAnuncio;

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

    public static Specification<Anuncio> filtrarPorPrecoMin(BigDecimal precoMin) {
        return (root, query, cb) -> {
            if (precoMin == null) {
                return cb.conjunction();
            }
            return cb.greaterThanOrEqualTo(root.get("produto").get("precoUnidade"), precoMin);
        };
    }

    public static Specification<Anuncio> filtrarPorPrecoMax(BigDecimal precoMax){
        return (root,query,cb) -> {
            if(precoMax == null){
                return cb.conjunction();
            }
            return cb.lessThanOrEqualTo(root.get("produto").get("precoUnidade"), precoMax);
        };
    }

    public static Specification<Anuncio> filtrarPorTipo(TipoAnuncio tipo) {  //doação ou venda
        return (root, query, cb) -> {
            if (tipo == null) {
                return cb.conjunction();
            }
            return cb.equal(root.get("tipo"), tipo);
        };
    }

    public static Specification<Anuncio> filtrarPorStatus(StatusAnuncio status) {  //doação ou venda
        return (root, query, cb) -> {
            if (status == null ) {
                return cb.conjunction();
            }
            return cb.equal(root.get("status"), status);
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
