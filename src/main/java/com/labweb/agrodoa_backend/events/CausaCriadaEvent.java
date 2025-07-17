package com.labweb.agrodoa_backend.events;

import lombok.AllArgsConstructor;
import lombok.Getter;

import com.labweb.agrodoa_backend.model.Causa;

@Getter
@AllArgsConstructor
public class CausaCriadaEvent { //para o observer - evento do subject a ser observado
    private final Causa causa;
}
