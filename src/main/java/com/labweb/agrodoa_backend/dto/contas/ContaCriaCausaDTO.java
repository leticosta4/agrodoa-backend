package com.labweb.agrodoa_backend.dto.contas;

import com.labweb.agrodoa_backend.model.contas.Conta;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ContaCriaCausaDTO {
    private String idAnunciante;
    private String nome;
    private String email;

    public ContaCriaCausaDTO(Conta c){
        this.idAnunciante = c.getIdConta();
        this.nome = c.getNome();
        this.email = c.getEmail();
    }
}
