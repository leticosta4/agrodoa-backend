package com.labweb.agrodoa_backend.dto;

import java.time.LocalDate;

import com.labweb.agrodoa_backend.model.Causa;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CausaDTO {
    private String nome;

    private String descricao;

    private double meta;

    private LocalDate prazo; //mudar a forma de exibição para DD/MM/AAAA

    private String nomeArquivoFoto;

    //Construtor
    public CausaDTO(Causa causa) {
        this.nome = causa.getNome();
        this.descricao = causa.getDescricao();
        this.meta = causa.getMeta();
        this.prazo = causa.getPrazo();
        this.nomeArquivoFoto = causa.getNomeArquivoFoto();
        //o status e o valor arrecadado vai ser preechido por valor padrão no construtor de causa
    }

    public Causa transformaParaObjeto(){
        return new Causa(nome, descricao, meta, prazo, nomeArquivoFoto);
    }
}
