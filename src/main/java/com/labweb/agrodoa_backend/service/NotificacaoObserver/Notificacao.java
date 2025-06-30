package com.labweb.agrodoa_backend.service.NotificacaoObserver;

import com.labweb.agrodoa_backend.model.pessoas.Usuario;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Notificacao {
    private String mensagem;
    private TipoNotificacao tipo;
    private Object fonte;
    private Usuario destinatario;

    public Notificacao(String mensagem, TipoNotificacao tipo, Object fonte) {
        this.mensagem = mensagem;
        this.tipo = tipo;
        this.fonte = fonte;
    }

    public enum TipoNotificacao {
        ANUNCIO_VALIDADE_PROXIMA,
        ANUNCIO_LIMITE_NEGOCIANTES,
        USUARIO_RECEBEU_DENUNCIA,
        USUARIO_ATINGIU_LIMITE_DENUNCIAS,
        CAUSA_PRAZO_FINAL,
        CAUSA_META_ATINGIDA,
        AVISO_INTERNO_SISTEMA
    }

}
