package com.labweb.agrodoa_backend.dto.causa;

import java.time.LocalDate;

import com.labweb.agrodoa_backend.model.Causa;

import lombok.Getter;

@Getter
public class CausaRespostaDTO {
    private String idCausa;
    private String nome;
    private String descricao;
    private double meta;
    private double valorArrecadado;
    private LocalDate prazo; //mudar a forma de exibição para DD/MM/AAAA
    private String nomeArquivoFoto;


    public CausaRespostaDTO(Causa c){
        this.idCausa = c.getIdCausa();
        this.nome = c.getNome();
        this.descricao = c.getDescricao();
        this.meta = c.getMeta();
        this.valorArrecadado = c.getValorArrecadado();
        this.prazo = c.getPrazo();
        this.nomeArquivoFoto = c.getNomeArquivoFoto();
    }
}
