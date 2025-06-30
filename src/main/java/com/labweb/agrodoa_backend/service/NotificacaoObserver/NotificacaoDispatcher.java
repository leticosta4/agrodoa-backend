package com.labweb.agrodoa_backend.service.NotificacaoObserver;

import com.labweb.agrodoa_backend.model.Notificacao;
import com.labweb.agrodoa_backend.model.pessoas.Usuario;
import com.labweb.agrodoa_backend.repository.NotificacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificacaoDispatcher {

    private final NotificacaoRepository notificacaoRepository;

    public NotificacaoDispatcher(NotificacaoRepository notificacaoRepository) {
        this.notificacaoRepository = notificacaoRepository;
    }

    // Envia uma notificação para um único destinatário
    public void enviar(NotificacaoObserver notificacaoObserver, Usuario destinatario) {
        Notificacao notificacao = new Notificacao(
            notificacaoObserver.getMensagem(),
            notificacaoObserver.getTipo(),
            destinatario,
            notificacaoObserver.getFonte().getClass().getSimpleName(),
            extrairId(notificacaoObserver.getFonte())
        );
        notificacaoRepository.save(notificacao);
    }

    // Envia para uma lista de destinatários que implementam Observer (ex: Usuarios, Administradores etc)
    public void enviarParaObservers(NotificacaoObserver notificacaoObserver, List<? extends Observer> destinatarios) {
        for (Observer obs : destinatarios) {
            if (obs instanceof Usuario usuario) {
                enviar(notificacaoObserver, usuario);
            }
            // Se tiver outros tipos que precisam de notificação, trate aqui
        }
    }

    // Envia especificamente para uma lista de Usuarios
    public void enviarParaUsuarios(NotificacaoObserver notificacaoObserver, List<Usuario> destinatarios) {
        for (Usuario usuario : destinatarios) {
            enviar(notificacaoObserver, usuario);
        }
    }

    // Extrai ID como String da fonte da notificação
    private String extrairId(Object fonte) {
        try {
            var method = fonte.getClass().getMethod("getId" + fonte.getClass().getSimpleName());
            Object id = method.invoke(fonte);
            return id != null ? id.toString() : "sem_id";
        } catch (Exception e) {
            return "desconhecido";
        }
    }
}
