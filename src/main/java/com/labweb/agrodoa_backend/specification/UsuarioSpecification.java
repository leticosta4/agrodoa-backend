package com.labweb.agrodoa_backend.specification;

import org.springframework.data.jpa.domain.Specification;

import com.labweb.agrodoa_backend.model.contas.Usuario;
import com.labweb.agrodoa_backend.model.enums.SituacaoUsuario;

public class UsuarioSpecification {    //falta diminuir os repositories com specifications que a gente já tem
    public static Specification<Usuario> filtrarPorTipo(String tipo) {
        return (root, query, cb) -> {
            if (tipo == null || tipo.isBlank()) {
                return cb.conjunction();
            }
            return cb.equal(cb.lower(root.get("tipoUsuario").get("nome")), tipo.toLowerCase());
        };
    }

    public static Specification<Usuario> filtrarPorSituacao(String situacao) {
        return (root, query, cb) -> {
            if (situacao == null || situacao.isBlank()) {
                return cb.conjunction();
            }

            SituacaoUsuario situacaoEnum;
            try {
                situacaoEnum = SituacaoUsuario.valueOf(situacao.toUpperCase());
            } catch (IllegalArgumentException e) {
                return cb.disjunction(); //nesse caso n vai retornar nada
            }
            
            return cb.equal(root.get("situacaoUser"), situacaoEnum);
        };
    }
}
