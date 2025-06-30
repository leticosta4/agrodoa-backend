package com.labweb.agrodoa_backend.model.pessoas;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;

import com.labweb.agrodoa_backend.model.Tipo;
import com.labweb.agrodoa_backend.model.local.Cidade;
import com.labweb.agrodoa_backend.model.relacoes.DoacaoCausa;
import com.labweb.agrodoa_backend.service.NotificacaoObserver.Notificacao;
import com.labweb.agrodoa_backend.service.NotificacaoObserver.Notificacao.TipoNotificacao;
import com.labweb.agrodoa_backend.model.Denuncia;
import com.labweb.agrodoa_backend.model.Avaliacao;

import com.labweb.agrodoa_backend.service.NotificacaoObserver.Notificacao;
import com.labweb.agrodoa_backend.service.NotificacaoObserver.Notificacao.TipoNotificacao;
import com.labweb.agrodoa_backend.service.NotificacaoObserver.Observer;
import com.labweb.agrodoa_backend.service.NotificacaoObserver.Subject;
import java.beans.Transient;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuario")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@PrimaryKeyJoinColumn(name = "conta_idconta") // mapeia PK+FK >> exato nome da coluna no banco
@Getter
@Setter
@NoArgsConstructor
public abstract class Usuario extends Conta implements Subject {
    @Column(name = "cpf_ou_cnpj")
    String cpfOuCnpj;

    @Column(name = "nome_arquivo_foto")
    String nomeArquivoFoto;

    @Column(name = "telefone")
    String telefone;

    @ManyToOne
    @JoinColumn(name = "cidade_idcidade", referencedColumnName = "idcidade")
    private Cidade cidade;

    @ManyToOne
    @JoinColumn(name = "tipo_idtipo", referencedColumnName = "idtipo") //nomes exatamente como no banco
    private Tipo tipoUsuario;

    @Column(name = "voluntario")
    int ehVoluntario; //talvez virar boolean?
    
    //ver o momento da inicialização dessas 3 listas abaixo
    @OneToMany(mappedBy = "denunciado", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Denuncia> denuncias;

    @OneToMany(mappedBy = "avaliado", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Avaliacao> avaliacoes; 

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DoacaoCausa> doacoesCausas;

    public Usuario(String nome, String email, String senha, String cpfOuCnpj, String nomeArquivoFoto, String telefone, Cidade cidade, Tipo tipoUsuario, int ehVoluntario) {
        super(nome, email, senha);
        this.cpfOuCnpj = cpfOuCnpj;
        this.nomeArquivoFoto = nomeArquivoFoto;
        this.telefone = telefone;
        this.cidade = cidade;
        this.tipoUsuario = tipoUsuario;
        this.ehVoluntario = ehVoluntario;
    }


    private transient List<Observer> observadores = new ArrayList<>();


    // Método para adicionar uma denúncia e disparar a notificação
    public void adicionarDenuncia(Denuncia denuncia) {
        if (this.denuncias == null) {
            this.denuncias = new ArrayList<>();
        }
        this.denuncias.add(denuncia);

        // Notifica que uma nova denúncia foi recebida
        Notificacao novaDenuncia = new Notificacao(
            "O usuário " + getNome() + " recebeu uma nova denúncia.",
            TipoNotificacao.USUARIO_RECEBEU_DENUNCIA,
            this
        );
        notificarObservadores(novaDenuncia);

        // Verifica o limite de denúncias e notifica para desativação
        if (this.denuncias.size() >= 3) {
            Notificacao limiteAtingido = new Notificacao(
                "Atenção: O usuário " + getNome() + " atingiu 3 denúncias e deve ser desativado.",
                TipoNotificacao.USUARIO_ATINGIU_LIMITE_DENUNCIAS,
                this
            );
            notificarObservadores(limiteAtingido);
        }
    }


    @Override
    public void adicionarObservador(Observer observer) {
        if (!observadores.contains(observer)) observadores.add(observer);
    }

    @Override
    public void removerObservador(Observer observer) {
        observadores.remove(observer);
    }

    @Override
    public void notificarObservadores(Notificacao notificacao) {
        for (Observer obs : observadores) {
            obs.atualizar(notificacao);
        }
    }
}
