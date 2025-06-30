package com.labweb.agrodoa_backend.service.NotificacaoObserver;

import com.labweb.agrodoa_backend.model.Anuncio;
import com.labweb.agrodoa_backend.model.Denuncia;
import com.labweb.agrodoa_backend.model.Motivo;
import com.labweb.agrodoa_backend.model.Negociacao;
import com.labweb.agrodoa_backend.model.enums.StatusAnuncio;
import com.labweb.agrodoa_backend.model.pessoas.Usuario;
import com.labweb.agrodoa_backend.model.relacoes.RelacaoBeneficiario;
import com.labweb.agrodoa_backend.repository.PenalidadeRepository;
import com.labweb.agrodoa_backend.service.NotificacaoObserver.NotificacaoObserver;
import com.labweb.agrodoa_backend.service.NotificacaoObserver.NotificacaoObserver.TipoNotificacao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificacaoService {

    private final NotificacaoDispatcher dispatcher;
    private final PenalidadeRepository penalidadeRepository;

    public void notificarFinalizacaoNegociacao(Negociacao negociacaoFinalizada) {
        Anuncio anuncio = negociacaoFinalizada.getRelacao().getAnuncio();
        Usuario finalizador = negociacaoFinalizada.getRelacao().getBeneficiario();

        List<Usuario> outrosUsuarios = anuncio.getRelacoes().stream()
            .map(RelacaoBeneficiario::getBeneficiario)

            .filter(u -> !u.equals(finalizador))
            .toList();

        NotificacaoObserver notificacao = new NotificacaoObserver(
            "Uma negociação do anúncio '" + anuncio.getTitulo() + "' foi finalizada. Verifique a disponibilidade.",
            TipoNotificacao.NEGOCIACAO_FINALIZADA,
            negociacaoFinalizada
        );

        dispatcher.enviarParaUsuarios(notificacao, outrosUsuarios);
    }

    public void notificarMudancaStatusAnuncio(Anuncio anuncio, StatusAnuncio novoStatus) {
        List<Usuario> beneficiarios = anuncio.getRelacoes().stream()
            .map(RelacaoBeneficiario::getBeneficiario)

            .toList();

        NotificacaoObserver notificacao = new NotificacaoObserver(
            "O anúncio '" + anuncio.getTitulo() + "' mudou de status para: " + novoStatus.name(),
            TipoNotificacao.ANUNCIO_STATUS_ALTERADO,
            anuncio
        );

        dispatcher.enviarParaUsuarios(notificacao, beneficiarios);
        dispatcher.enviar(notificacao, anuncio.getAnunciante());
    }

    public void notificarPenalidade(Denuncia denunciaAvaliada) {
        Usuario denunciado = denunciaAvaliada.getDenunciado();
        Motivo motivo = denunciaAvaliada.getMotivo();

        NotificacaoObserver penalidade = new NotificacaoObserver(
            "Você recebeu uma penalidade por: " + motivo.getNome(),
            TipoNotificacao.USUARIO_RECEBEU_DENUNCIA,
            denunciaAvaliada
        );

        dispatcher.enviar(penalidade, denunciado);

        long total = penalidadeRepository.countByUsuario(denunciado);

        if (total >= 3) {
            NotificacaoObserver suspensao = new NotificacaoObserver(
                "Sua conta foi suspensa por atingir 3 penalidades.",
                TipoNotificacao.USUARIO_ATINGIU_LIMITE_DENUNCIAS,
                denunciaAvaliada
            );
            dispatcher.enviar(suspensao, denunciado);
        }
    }
}
