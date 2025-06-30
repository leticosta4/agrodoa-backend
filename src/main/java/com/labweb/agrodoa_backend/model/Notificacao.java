package com.labweb.agrodoa_backend.model;

import java.time.LocalDateTime;

import com.labweb.agrodoa_backend.model.pessoas.Usuario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.labweb.agrodoa_backend.service.NotificacaoObserver.NotificacaoObserver;

@Entity
@Table(name = "notificacao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Notificacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idnotificacao")
    private String idNotificacao;

    private String mensagem;

    @Enumerated(EnumType.STRING)
    private NotificacaoObserver.TipoNotificacao tipo;

    private LocalDateTime dataHoraCriacao;

    @ManyToOne
    @JoinColumn(name = "destinatario_id")
    private Usuario destinatario;

    private String origemClasse;
    private String origemId;

    public Notificacao(String mensagem, NotificacaoObserver.TipoNotificacao tipo, Usuario destinatario, String origemClasse, String origemId) {
        this.mensagem = mensagem;
        this.tipo = tipo;
        this.dataHoraCriacao = LocalDateTime.now();
        this.destinatario = destinatario;
        this.origemClasse = origemClasse;
        this.origemId = origemId;
    }
}
