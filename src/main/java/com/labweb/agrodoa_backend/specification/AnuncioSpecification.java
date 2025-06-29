package com.labweb.agrodoa_backend.specification;

import java.math.BigDecimal;

import org.springframework.data.jpa.domain.Specification;

import com.labweb.agrodoa_backend.model.Anuncio;

public class AnuncioSpecification {
    
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
            return cb.like(cb.lower(root.get("fornecedor").get("cidade").get("nome")), "%" + cidade.toLowerCase() + "%");
        };
    }

    public static Specification<Anuncio> filtrarPorPrecoMin(BigDecimal precoMin) {
        return (root, query, cb) -> {
            if (precoMin == null) {
                return cb.conjunction();
            }
            return cb.greaterThanOrEqualTo(root.get("produto").get("precoUnitario"), precoMin);
        };
    }

    public static Specification<Anuncio> filtrarPorTipo(String tipo) {
        return (root, query, cb) -> {
            if (tipo == null || tipo.isEmpty()) {
                return cb.conjunction();
            }
            return cb.equal(cb.lower(root.get("tipoAnuncio")), tipo.toLowerCase());
        };
    }
}
