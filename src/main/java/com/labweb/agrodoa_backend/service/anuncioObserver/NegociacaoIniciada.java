package com.labweb.agrodoa_backend.service.anuncioObserver;

import org.springframework.context.ApplicationEvent;

import com.labweb.agrodoa_backend.model.Anuncio;
import com.labweb.agrodoa_backend.model.pessoas.Usuario;

public class NegociacaoIniciada extends ApplicationEvent {

    private final Anuncio anuncio;
    private final Usuario interessado;

    public NegociacaoIniciada(Object source, Anuncio anuncio, Usuario interessado) {
        super(source);
        this.anuncio = anuncio;
        this.interessado = interessado;
    }

    // Getters para anuncio e interessado
    public Anuncio getAnuncio() {
        return anuncio;
    }

    public Usuario getInteressado() {
        return interessado;
    }
}
