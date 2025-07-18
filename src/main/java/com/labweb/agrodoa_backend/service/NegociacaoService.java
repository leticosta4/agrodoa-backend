package com.labweb.agrodoa_backend.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import com.labweb.agrodoa_backend.events.negociacao.NegociacaoAceitaEvent;
import com.labweb.agrodoa_backend.events.negociacao.NegociacaoRecusadaEvent;
import com.labweb.agrodoa_backend.model.Anuncio;
import com.labweb.agrodoa_backend.model.Negociacao;
import com.labweb.agrodoa_backend.model.enums.StatusAnuncio;
import com.labweb.agrodoa_backend.model.enums.StatusNegociacao;
import com.labweb.agrodoa_backend.repository.AnuncioRepository;
import com.labweb.agrodoa_backend.repository.NegociacaoRepository;

import jakarta.transaction.Transactional;

@Service
public class NegociacaoService {
    @Autowired private AnuncioRepository anuncioRepo;
    @Autowired private NegociacaoRepository negociacaoRepo;
    @Autowired private ApplicationEventPublisher eventPublisher;

    @Transactional 
    public Map<String, Object> aceitarNeg(String idNegociacao) {
        
        Negociacao negociacao = negociacaoRepo.findById(idNegociacao)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Negociação não encontrada."));

        if (negociacao.getStatus() != StatusNegociacao.AGUARDANDO) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Esta negociação não está mais aguardando uma ação.");
        }

        Anuncio anuncio = negociacao.getRelacao().getAnuncio();
        
        if(anuncio.getStatus() != StatusAnuncio.ATIVO) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Este anúncio não está mais ativo.");
        }

        if (anuncio.getProduto().getQuantidade() < negociacao.getQuantidade()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "O anúncio não possui a quantidade solicitada disponível.");
        }

        // Decrementa a quantidade do anúncio
        int novaQuantidade = anuncio.getProduto().getQuantidade() - negociacao.getQuantidade();
        anuncio.getProduto().setQuantidade(novaQuantidade);

        negociacao.setStatus(StatusNegociacao.FINALIZADA);
        
        List<Negociacao> negociacoesParaSalvar = new ArrayList<>();
        negociacoesParaSalvar.add(negociacao);
        
        // Lógica Condicional: Anúncio Esgotado 
        if (novaQuantidade <= 0) {
            anuncio.setStatus(StatusAnuncio.FINALIZADO);

            List<Negociacao> outrasNegociacoesPendentes = negociacaoRepo.findAllByRelacaoAnuncioAndStatus(anuncio, StatusNegociacao.AGUARDANDO);

            for (Negociacao outra : outrasNegociacoesPendentes) {
                outra.setStatus(StatusNegociacao.CANCELADA);
                negociacoesParaSalvar.add(outra);
                eventPublisher.publishEvent(new NegociacaoRecusadaEvent(outra));
            }
        }

        anuncioRepo.save(anuncio);
        negociacaoRepo.saveAll(negociacoesParaSalvar);

        eventPublisher.publishEvent(new NegociacaoAceitaEvent(negociacao));

        Map<String, Object> resposta = new HashMap<>();
        resposta.put("mensagem", "Negociação aceita com sucesso!");
        resposta.put("idNegociacao", negociacao.getIdNegociacao());
        resposta.put("statusNegociacao", negociacao.getStatus());
        resposta.put("statusAnuncio", anuncio.getStatus());

        return resposta;
    }

    public Map<String, Object> cancelarNeg(String idNegociacao) {
        
        Negociacao negociacaoCancelada = negociacaoRepo.findByIdNegociacao(idNegociacao);
        negociacaoCancelada.setStatus(StatusNegociacao.CANCELADA);
        negociacaoRepo.save(negociacaoCancelada);

        eventPublisher.publishEvent(new NegociacaoRecusadaEvent(negociacaoCancelada));

        Map<String, Object> resposta = new HashMap<>();
        resposta.put("idNegociacao", negociacaoCancelada.getIdNegociacao());
        resposta.put("status", negociacaoCancelada.getStatus()); 
        resposta.put("mensagem", "Negociação cancelada com sucesso.");

        return resposta;
    }
}
