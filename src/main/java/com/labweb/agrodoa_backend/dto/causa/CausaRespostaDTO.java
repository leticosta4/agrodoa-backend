package com.labweb.agrodoa_backend.dto.causa;

import java.time.LocalDate;

import com.labweb.agrodoa_backend.dto.contas.ContaCriaCausaDTO;
import com.labweb.agrodoa_backend.model.Causa;

import lombok.Getter;

@Getter
public class CausaRespostaDTO {
    private String idCausa;
    private String nome;
    private String descricao;
    private LocalDate prazo; //mudar a forma de exibição para DD/MM/AAAA
    private String nomeArquivoFoto;
    private int metaAssinatura;
    private int assinaturasObtidas;
    private ContaCriaCausaDTO contaCriadora;


    public CausaRespostaDTO(Causa c){
        this.idCausa = c.getIdCausa();
        this.nome = c.getNome();
        this.descricao = c.getDescricao();
        this.prazo = c.getPrazo();
        this.nomeArquivoFoto = c.getNomeArquivoFoto();
        this.metaAssinatura = c.getMetaAssinatura();
        this.assinaturasObtidas = c.getAssinaturasFeitas();
        this.contaCriadora = new ContaCriaCausaDTO(c.getCriador());
    }
}
