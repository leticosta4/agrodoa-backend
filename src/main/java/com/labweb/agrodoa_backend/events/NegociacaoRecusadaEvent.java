package com.labweb.agrodoa_backend.events;

import com.labweb.agrodoa_backend.model.Negociacao;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NegociacaoRecusadaEvent {
    private final Negociacao negociacao;
}