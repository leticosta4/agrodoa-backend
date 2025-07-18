package com.labweb.agrodoa_backend.events.causa;

import lombok.AllArgsConstructor;
import lombok.Getter;

import com.labweb.agrodoa_backend.model.Causa;

@Getter
@AllArgsConstructor
public class CausaAprovadaEvent {
    private final Causa causa;
}
