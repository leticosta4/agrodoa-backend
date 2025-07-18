package com.labweb.agrodoa_backend.service.auxiliares;

import java.time.format.DateTimeFormatter;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.labweb.agrodoa_backend.events.CausaCriadaEvent;
import com.labweb.agrodoa_backend.events.DenunciaAprovadaEvent;
import com.labweb.agrodoa_backend.events.NegociacaoAceitaEvent;
import com.labweb.agrodoa_backend.events.NegociacaoIniciadaEvent;
import com.labweb.agrodoa_backend.events.NegociacaoRecusadaEvent;
import com.labweb.agrodoa_backend.model.Anuncio;
import com.labweb.agrodoa_backend.model.Causa;
import com.labweb.agrodoa_backend.model.Denuncia;
import com.labweb.agrodoa_backend.model.Negociacao;
import com.labweb.agrodoa_backend.model.contas.Usuario;
import com.labweb.agrodoa_backend.repository.contas.ContaRepository;
import com.labweb.agrodoa_backend.repository.contas.UsuarioRepository;

//OBSERVER
@Component
public class NotificacaoListener { //depois remodelar provavelmente ja q vão ter outros tipos de notificacao
    @Autowired private EmailService emailService;
    @Autowired private UsuarioRepository userRepo;
    @Autowired private ContaRepository contaRepo;

    private static final DateTimeFormatter FORMATADOR_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Async //o metodo pode rodar em outra thread
    @EventListener
    public void aoCriarCausa(CausaCriadaEvent evento){  //em lote p n sobrecarregar
        Causa causaCriada = evento.getCausa();
        int tamLote = 20;
        Pageable paginacao = PageRequest.of(0, tamLote);
        Page<Usuario> paginaUsers;

        String assunto = "Uma nova causa precisa da sua ajuda: " + causaCriada.getNome();
        String corpoTemplate = "Olá, %s!\n\n" +
                "Uma nova causa foi cadastrada em nossa plataforma e pode ser do seu interesse.\n\n" +
                "Nome da Causa: %s\n" +
                "Descrição: %s\n" +
                "Prazo final para se participar e contribuir: %s\n" +
                "Meta de assinaturas: %d\n\n" +
                "Sua ajuda pode fazer a diferença! Acesse nosso site para saber mais.\n\n" +
                "Atenciosamente,\nEquipe Agrodoa.";

        do {
            paginaUsers = userRepo.findAll(paginacao);

            if(!paginaUsers.hasContent()){ break; }

            informes(paginaUsers, paginacao);            

            //enviando a todos 
            for(Usuario user: paginaUsers.getContent()){
                String corpoPersonalizado = String.format(
                        corpoTemplate, 
                        user.getNome(),
                        causaCriada.getNome(),
                        causaCriada.getDescricao(),
                        causaCriada.getPrazo().format(FORMATADOR_DATA),
                        causaCriada.getMetaAssinatura()
                );


                emailService.enviarEmail(user.getEmail(), assunto, corpoPersonalizado);
                System.out.println("\n\nE-mail de notificação enviado para: " + user.getEmail() + "\n\n");
            }
            paginacao = paginacao.next();
        }while(paginaUsers.hasNext());

        System.out.println("Envio de notificações para usuários concluído.");
    }

    @Async
    @EventListener
    public void aoAprovarDenuncia(DenunciaAprovadaEvent evento) {
        System.out.println("Iniciando processo de notificação para denúncia aprovada...");

        Denuncia denunciaAprovada = evento.getDenuncia();
        Usuario usuarioDenunciado = denunciaAprovada.getDenunciado();

        String assunto = "Aviso: Uma denúncia contra você foi aprovada";
        String corpo = String.format(
            "Olá, %s.\n\n" +
            "Informamos que uma denúncia registrada contra você em nossa plataforma foi analisada e aprovada por nossa equipe de moderação.\n\n" +
            "Motivo da Denúncia: %s\n\n" +
            "Ações cabíveis poderão ser tomadas de acordo com nossos termos de serviço.\n\n" +
            "Atenção: Caso sua conta atinja o limite de 3 denúncias aprovadas, ela será automaticamente suspensa.\n\n" +
            "Atenciosamente,\nEquipe Agrodoa.",
            usuarioDenunciado.getNome(),
            denunciaAprovada.getMotivo().getNome()
        );

        String emailDenunciado = contaRepo.findEmailByIdConta(usuarioDenunciado.getIdConta());

        emailService.enviarEmail(emailDenunciado, assunto, corpo);

        System.out.printf("\n\nE-mail de notificação de denúncia aprovada enviado para: %s\n\n", emailDenunciado);
    }

    private void informes(Page<Usuario> intermedPage, Pageable paginacao){
        System.out.printf("Processando lote de %d usuários (Página %d de %d)%n",
                              intermedPage.getNumberOfElements(),
                              paginacao.getPageNumber() + 1,
                              intermedPage.getTotalPages());
    }

    @Async 
    @EventListener
    public void aoIniciadaNegociacao(NegociacaoIniciadaEvent evento) {
        try {
            Negociacao negociacao = evento.getNegociacao();

            Anuncio anuncio = negociacao.getRelacao().getAnuncio();
            Usuario interessado = negociacao.getRelacao().getBeneficiario(); 
            Usuario anunciante = anuncio.getAnunciante(); 

            
            String destinatario = anunciante.getEmail();
            
            String assunto = "Você recebeu uma nova proposta para o anúncio: " + anuncio.getTitulo();

            String corpoTemplate = """
                    Olá, %s!

                    Um usuário da plataforma Agrodoa demonstrou interesse no seu anúncio e enviou uma proposta de negociação.

                    --- Detalhes da Proposta ---
                    Anúncio: %s
                    Quantidade: %d

                    --- Informações de Contato do Interessado ---
                    Nome: %s
                    E-mail: %s
                    Telefone: %s

                    Sugerimos que você entre em contato para dar continuidade à negociação.

                    Atenciosamente,
                    Equipe Agrodoa.
                    """;

            String corpoPersonalizado = String.format(
                    corpoTemplate,
                    anunciante.getNome(),
                    anuncio.getTitulo(),
                    negociacao.getQuantidade(),
                    interessado.getNome(),
                    interessado.getEmail(),
                    interessado.getTelefone()
            );

            emailService.enviarEmail(destinatario, assunto, corpoPersonalizado);

            System.out.println("\n\nE-mail de notificação de negociação enviado para: " + destinatario + "\n\n");

        } catch (Exception e) {
            System.err.println("Falha ao enviar e-mail de notificação de negociação: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Async
    @EventListener
    public void aoAceitarNegociacao(NegociacaoAceitaEvent evento) {
        try {
            Negociacao negociacao = evento.getNegociacao();
            Usuario interessado = negociacao.getRelacao().getBeneficiario(); // O destinatário é o beneficiário
            Anuncio anuncio = negociacao.getRelacao().getAnuncio();

            String destinatario = interessado.getEmail();
            String assunto = "Boas notícias! Sua proposta foi aceita!";
            String corpo = String.format(
                """
                Olá, %s!

                Sua proposta para o anúncio "%s" foi ACEITA pelo anunciante.

                Parabéns! Agora você pode entrar em contato com o anunciante para combinar os detalhes da entrega/retirada.

                Atenciosamente,
                Equipe Agrodoa.
                """,
                interessado.getNome(),
                anuncio.getTitulo()
            );
            emailService.enviarEmail(destinatario, assunto, corpo);
            System.out.println("\n\nE-mail de ACEITAÇÃO de negociação enviado para: " + destinatario + "\n\n");
        } catch (Exception e) {
            System.err.println("Falha ao enviar e-mail de aceitação de negociação: " + e.getMessage());
        }
    }

    /**
     * NOVO LISTENER: Notifica o BENEFICIÁRIO que sua proposta foi RECUSADA.
     */
    @Async
    @EventListener
    public void aoRecusarNegociacao(NegociacaoRecusadaEvent evento) {
        try {
            Negociacao negociacao = evento.getNegociacao();
            Usuario interessado = negociacao.getRelacao().getBeneficiario();
            Anuncio anuncio = negociacao.getRelacao().getAnuncio();

            String destinatario = interessado.getEmail();
            String assunto = "Atualização sobre sua proposta de negociação";
            String corpo = String.format(
                """
                Olá, %s.

                Gostaríamos de informar que sua proposta para o anúncio "%s" foi RECUSADA pelo anunciante neste momento.

                Não desanime! Existem muitos outros anúncios em nossa plataforma que podem ser do seu interesse.

                Atenciosamente,
                Equipe Agrodoa.
                """,
                interessado.getNome(),
                anuncio.getTitulo()
            );
            emailService.enviarEmail(destinatario, assunto, corpo);
            System.out.println("\n\nE-mail de RECUSA de negociação enviado para: " + destinatario + "\n\n");
        } catch (Exception e) {
            System.err.println("Falha ao enviar e-mail de recusa de negociação: " + e.getMessage());
        }
    }
}
