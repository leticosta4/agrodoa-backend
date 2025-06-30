package com.labweb.agrodoa_backend.service.NotificacaoObserver;

public interface Subject {
    void adicionarObservador(Observer observador);
    void removerObservador(Observer observador);
    void notificarObservadores(NotificacaoObserver notificacao);
}
