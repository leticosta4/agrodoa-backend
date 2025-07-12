package com.labweb.agrodoa_backend.dto;

import com.labweb.agrodoa_backend.model.Avaliacao;
import com.labweb.agrodoa_backend.model.contas.Usuario;

public class AvaliacaoDTO {
    private String idAvaliacao;
    private int nota;
    private String comentario;
    private Usuario avaliado;

    public AvaliacaoDTO(Avaliacao av){
        this.idAvaliacao = av.getIdAvaliacao();
        this.nota = av.getNota();
        this.comentario = av.getComentario();
        this.avaliado = av.getAvaliado();
    }
}
