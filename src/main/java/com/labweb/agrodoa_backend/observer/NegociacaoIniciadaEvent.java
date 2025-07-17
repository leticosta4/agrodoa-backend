package com.labweb.agrodoa_backend.observer;

import com.labweb.agrodoa_backend.model.Negociacao;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NegociacaoIniciadaEvent {
    private final Negociacao negociacao;
}
