package com.labweb.agrodoa_backend.model.pessoas;

import com.labweb.agrodoa_backend.service.NotificacaoObserver.NotificacaoObserver;
import com.labweb.agrodoa_backend.service.NotificacaoObserver.NotificacaoObserver.TipoNotificacao;
import com.labweb.agrodoa_backend.service.NotificacaoObserver.Observer;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter; 
import lombok.NoArgsConstructor;

@Entity
@Table(name = "administrador")
@PrimaryKeyJoinColumn(name = "conta_idconta") // mapeia PK+FK >> exato nome da coluna no banco
@Getter
@Setter
@NoArgsConstructor
public class Administrador extends Conta implements Observer {
    private String github;
    private String linkedin;

    public Administrador(String nome, String email, String senha, String github, String linkedin) {
        super(nome, email, senha);
        this.github = github;
        this.linkedin = linkedin;
    }

    @Override
    public void atualizar(NotificacaoObserver notificacao) {
        System.out.println("NOTIFICAÇÃO PARA ADMIN: " + notificacao.getMensagem());

        if (notificacao.getTipo() == TipoNotificacao.USUARIO_ATINGIU_LIMITE_DENUNCIAS) {

            Usuario usuarioProblematico = (Usuario) notificacao.getFonte();
            System.out.println("AÇÃO REQUERIDA: Desativar conta de " + usuarioProblematico.getNome());

        }
    }
}
