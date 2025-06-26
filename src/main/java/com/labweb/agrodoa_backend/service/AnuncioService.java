package com.labweb.agrodoa_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.labweb.agrodoa_backend.model.Anuncio;
import com.labweb.agrodoa_backend.model.Negociacao;
import com.labweb.agrodoa_backend.model.pessoas.Usuario;
import com.labweb.agrodoa_backend.repository.AnuncioRepository;
import com.labweb.agrodoa_backend.repository.UsuarioRepository;
import com.labweb.agrodoa_backend.service.anuncioObserver.NegociacaoIniciada;

import jakarta.transaction.Transactional;

@Service
public class AnuncioService {

    // @Autowired
    // private AnuncioRepository anuncioRepository;
    // @Autowired
    // private NegociacaoRepository negociacaoRepository; // Repositório para a nova tabela
    // @Autowired
    // private UsuarioRepository usuarioRepository;
    // @Autowired
    // private ApplicationEventPublisher eventPublisher; // Ferramenta do Spring para publicar eventos

    // // DTO para retornar os dados de contato
    // public static class ContatoFornecedorDTO {
    //     private String email;
    //     private String telefone;
    //     // getters e setters
    // }

    // @Transactional // IMPORTANTE: Garante que tudo aconteça ou nada aconteça (atomicidade)
    // public ContatoFornecedorDTO iniciarNegociacao(Long anuncioId, Long interessadoId) {
    //     Anuncio anuncio = anuncioRepository.findById(anuncioId)
    //             .orElseThrow(() -> new RuntimeException("Anúncio não encontrado!"));

    //     Usuario interessado = usuarioRepository.findById(interessadoId)
    //             .orElseThrow(() -> new RuntimeException("Usuário interessado não encontrado!"));

    //     // 1. REGRA DE NEGÓCIO: Verificar o limite de negociações
    //     if (anuncio.getNegociacoesAtivas() >= 5) {
    //         throw new IllegalStateException("Este anúncio já atingiu o limite de negociações ativas.");
    //     }

    //     // 2. LÓGICA PRINCIPAL: Registrar a nova negociação
    //     Negociacao novaNegociacao = new Negociacao();
    //     novaNegociacao.setAnuncio(anuncio);
    //     novaNegociacao.setInteressado(interessado);
    //     novaNegociacao.setStatus("ATIVA");
    //     negociacaoRepository.save(novaNegociacao);

    //     // 3. LÓGICA PRINCIPAL: Incrementar o contador
    //     anuncio.setNegociacoesAtivas(anuncio.getNegociacoesAtivas() + 1);
    //     anuncioRepository.save(anuncio);

    //     // 4. PUBLICAR O EVENTO (Notificar os Observers)
    //     // O trabalho principal do service termina aqui. O resto é com os listeners.
    //     NegociacaoIniciada = new NegociacaoIniciada(this, anuncio, interessado);
    //     eventPublisher.publishEvent(event);
    //     System.out.println("Evento NegociacaoIniciadaEvent publicado!");

    //     // 5. RETORNO SÍNCRONO: Devolver as informações de contato para o usuário
    //     Usuario fornecedor = anuncio.getUsuario(); // O dono do anúncio
    //     ContatoFornecedorDTO contato = new ContatoFornecedorDTO();
    //     contato.setEmail(fornecedor.getConta().getEmail());
    //     contato.setTelefone(fornecedor.getTelefone());

    //     return contato;
    //}
}