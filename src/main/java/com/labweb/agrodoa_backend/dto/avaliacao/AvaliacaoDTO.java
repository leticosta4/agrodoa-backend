package com.labweb.agrodoa_backend.dto.avaliacao;

import com.labweb.agrodoa_backend.model.Avaliacao;
import com.labweb.agrodoa_backend.model.contas.Usuario;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
