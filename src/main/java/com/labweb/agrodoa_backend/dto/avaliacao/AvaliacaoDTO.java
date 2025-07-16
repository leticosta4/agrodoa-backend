package com.labweb.agrodoa_backend.dto.avaliacao;

import com.labweb.agrodoa_backend.model.Avaliacao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AvaliacaoDTO {
    private String idAvaliacao;
    private int nota;
    private String comentario;
    private String idAvaliador;
    private String nomeAvaliador;
    private String nomeArquivoFoto;

    public AvaliacaoDTO(Avaliacao av){
        this.idAvaliacao = av.getIdAvaliacao();
        this.nota = av.getNota();
        this.comentario = av.getComentario();
        this.idAvaliador = av.getAvaliador().getIdConta();
        this.nomeAvaliador = av.getAvaliador().getNome();
        this.nomeArquivoFoto = av.getAvaliador().getNomeArquivoFoto();
    }
}
