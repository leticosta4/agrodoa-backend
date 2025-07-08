package com.labweb.agrodoa_backend.dto;

import java.time.LocalDate;

import com.labweb.agrodoa_backend.model.Causa;
import com.labweb.agrodoa_backend.model.enums.StatusCausa;

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

    private LocalDate prazo;

    private String nomeArquivoFoto;

    private double valorArrecadado;

    private StatusCausa status;

    //Construtor
    public CausaDTO(Causa causa) {
        this.nome = causa.getNome();
        this.descricao = causa.getDescricao();
        this.meta = causa.getMeta();
        this.prazo = causa.getPrazo();
        this.nomeArquivoFoto = causa.getNomeArquivoFoto();
        this.valorArrecadado = causa.getValorArrecadado();
        this.status = causa.getStatus();
    }

    public Causa transformaParaObjeto(){
        return new Causa(nome, descricao, meta, prazo, nomeArquivoFoto, valorArrecadado);
    }
}
