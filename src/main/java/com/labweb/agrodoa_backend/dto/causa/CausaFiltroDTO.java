package com.labweb.agrodoa_backend.dto.causa;

import com.labweb.agrodoa_backend.model.enums.StatusCausa;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class CausaFiltroDTO {
    private String nome;
    private String status;

    public StatusCausa getStatusEnum(){
        try {
            return status == null ? StatusCausa.ABERTA : StatusCausa.valueOf(status.toUpperCase());
        } catch(IllegalArgumentException e){
            return StatusCausa.ABERTA;
        }
    }
}
