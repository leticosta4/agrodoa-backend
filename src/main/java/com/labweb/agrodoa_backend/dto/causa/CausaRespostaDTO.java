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
    private LocalDate dataCriacao;
    private LocalDate prazo; //mudar a forma de exibição para DD/MM/AAAA
    private String nomeArquivoFoto;
    private int metaVoluntarios;
    private int voluntariosAtivos;
    private ContaCriaCausaDTO contaCriadora;


    public CausaRespostaDTO(Causa c){
        this.idCausa = c.getIdCausa();
        this.nome = c.getNome();
        this.descricao = c.getDescricao();
        this.dataCriacao = c.getDataCriacao();
        this.prazo = c.getPrazo();
        this.nomeArquivoFoto = c.getNomeArquivoFoto();
        this.metaVoluntarios = c.getMetaVoluntarios();
        this.voluntariosAtivos = c.getQuantVoluntarios();
        this.contaCriadora = new ContaCriaCausaDTO(c.getCriador());
    }
}
