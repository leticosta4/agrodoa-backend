package com.labweb.agrodoa_backend.dto.causa;

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

    private LocalDate prazo; //mudar a forma de exibição para DD/MM/AAAA

    private String nomeArquivoFoto;

    public Causa transformaParaObjeto(){
        return new Causa(nome, descricao, prazo, nomeArquivoFoto);
    }
}
