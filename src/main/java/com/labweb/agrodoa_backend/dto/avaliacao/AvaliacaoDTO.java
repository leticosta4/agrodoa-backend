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

    public AvaliacaoDTO(Avaliacao av){
        this.idAvaliacao = av.getIdAvaliacao();
        this.nota = av.getNota();
        this.comentario = av.getComentario();
    }
}
