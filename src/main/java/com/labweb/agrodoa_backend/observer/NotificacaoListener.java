package com.labweb.agrodoa_backend.observer;

import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.labweb.agrodoa_backend.model.Causa;
import com.labweb.agrodoa_backend.model.contas.Administrador;
import com.labweb.agrodoa_backend.repository.contas.AdministradorRepository;
import com.labweb.agrodoa_backend.service.EmailService;

//OBSERVER
@Component
public class NotificacaoListener { //depois remodelar provavelmente ja q vão ter outros tipos de notificacao
    @Autowired private EmailService emailService;
    @Autowired private AdministradorRepository admRepo;

    private static final DateTimeFormatter FORMATADOR_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Async //o metodo pode rodar em outra thread
    @EventListener
    public void aoCriarCausa(CausaCriadaEvent evento){
        Causa causaCriada = evento.getCausa();
        List<Administrador> adms = admRepo.findAll();

        if (adms.isEmpty()) {
            System.out.println("Nenhum administrador encontrado para notificar.");
            return;
        }

        //preparando o conteúdo para o email
        String assunto = "Nova Causa Criada: " + causaCriada.getNome();
        String corpo = String.format(
            "Olá, Administrador!\n\nUma nova causa foi cadastrada na plataforma e pode precisar de sua atenção.\n\n" +
            "ID da Causa: %s\n" +
            "Nome: %s\n" +
            "Meta de Arrecadação: R$ %.2f\n" +
            "Prazo Final: %s\n\n" + //formatar ainda
            "Por favor, revise-a no painel de causas.\n\n" +
            "Atenciosamente,\nEquipe Agrodoa.",
            causaCriada.getIdCausa(),
            causaCriada.getNome(),
            causaCriada.getMeta(),
            causaCriada.getPrazo().format(FORMATADOR_DATA)
        );

        //enviando a todos 
        for(Administrador adm: adms){
            emailService.enviarEmail(adm.getEmail(), assunto, corpo);
            System.out.println("\n\nE-mail de notificação enviado para: " + adm.getEmail() + "\n\n");
        }
    }
}
