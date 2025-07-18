package com.labweb.agrodoa_backend.service.auxiliares;

import java.time.format.DateTimeFormatter;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.labweb.agrodoa_backend.events.CausaAbertaEvent;
import com.labweb.agrodoa_backend.events.DenunciaAprovadaEvent;
import com.labweb.agrodoa_backend.model.Causa;
import com.labweb.agrodoa_backend.model.Denuncia;
import com.labweb.agrodoa_backend.model.contas.Usuario;
import com.labweb.agrodoa_backend.model.enums.StatusCausa;
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
    public void aoAbrirCausa(CausaAbertaEvent evento){  //em lote p n sobrecarregar
        Causa causa = evento.getCausa();
        int tamLote = 20;
        Pageable paginacao = PageRequest.of(0, tamLote);
        Page<Usuario> paginaUsers;

        if(causa.getStatus() != StatusCausa.ABERTA){
            throw new IllegalArgumentException("Essa causa ainda não foi aberta.");
        }

        String assunto = "Uma nova causa precisa da sua ajuda: " + causa.getNome();
        String corpoTemplate = "Olá, %s!\n\n" +
                "Uma nova causa foi cadastrada em nossa plataforma e pode ser do seu interesse.\n\n" +
                "Nome da Causa: %s\n" +
                "Descrição: %s\n" +
                "Prazo final para se participar e contribuir: %s\n" +
                "Meta de voluntários: %d\n\n" +
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
                        causa.getNome(),
                        causa.getDescricao(),
                        causa.getPrazo().format(FORMATADOR_DATA),
                        causa.getMetaVoluntarios()
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
}
