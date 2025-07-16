package com.labweb.agrodoa_backend.service.auxiliares;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    String emissor;

    public void enviarEmail(String destinatario, String assunto, String corpo){
        try{
            SimpleMailMessage msg = new SimpleMailMessage();

            msg.setFrom(emissor);
            msg.setTo(destinatario);
            msg.setSubject(assunto);
            msg.setText(corpo);
            mailSender.send(msg);

        } catch (Exception e){
            System.err.println("Erro ao enviar e-mail: " + e.getMessage());
        }
    }

}
