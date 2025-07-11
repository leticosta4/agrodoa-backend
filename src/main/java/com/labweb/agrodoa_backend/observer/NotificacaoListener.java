package com.labweb.agrodoa_backend.observer;

import java.time.format.DateTimeFormatter;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.labweb.agrodoa_backend.model.Causa;
import com.labweb.agrodoa_backend.model.contas.Usuario;
import com.labweb.agrodoa_backend.repository.contas.UsuarioRepository;
import com.labweb.agrodoa_backend.service.EmailService;

//OBSERVER
@Component
public class NotificacaoListener { //depois remodelar provavelmente ja q vão ter outros tipos de notificacao
    @Autowired private EmailService emailService;
    @Autowired private UsuarioRepository userRepo;

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
                "Meta: R$ %.2f\n" +
                "Prazo Final para Doações: %s\n\n" +
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
                        causaCriada.getMeta(),
                        causaCriada.getPrazo().format(FORMATADOR_DATA)
                );


                emailService.enviarEmail(user.getEmail(), assunto, corpoPersonalizado);
                System.out.println("\n\nE-mail de notificação enviado para: " + user.getEmail() + "\n\n");
            }
            paginacao = paginacao.next();
        }while(paginaUsers.hasNext());

        System.out.println("Envio de notificações para usuários concluído.");
    }

    private void informes(Page<Usuario> intermedPage, Pageable paginacao){
        System.out.printf("Processando lote de %d usuários (Página %d de %d)%n",
                              intermedPage.getNumberOfElements(),
                              paginacao.getPageNumber() + 1,
                              intermedPage.getTotalPages());
    }
}
