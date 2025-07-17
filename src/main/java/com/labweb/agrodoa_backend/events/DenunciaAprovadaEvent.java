package com.labweb.agrodoa_backend.events;

import com.labweb.agrodoa_backend.model.Denuncia;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DenunciaAprovadaEvent {
    private final Denuncia denuncia;
}