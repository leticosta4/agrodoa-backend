package com.labweb.agrodoa_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.labweb.agrodoa_backend.dto.negociacao.NegociacaoRespostaDTO;
import com.labweb.agrodoa_backend.model.Negociacao;
import com.labweb.agrodoa_backend.model.contas.Usuario;
import com.labweb.agrodoa_backend.model.enums.TipoRelacaoBenef;
import com.labweb.agrodoa_backend.observer.NegociacaoIniciadaEvent;
import com.labweb.agrodoa_backend.repository.AnuncioRepository;
import com.labweb.agrodoa_backend.repository.NegociacaoRepository;
import com.labweb.agrodoa_backend.repository.contas.UsuarioRepository;

@Service
public class NegociacaoService {
    @Autowired private UsuarioRepository userRepo;
    @Autowired private AnuncioRepository anuncioRepo;
    @Autowired private NegociacaoRepository negociacaoRepo;
    @Autowired private ApplicationEventPublisher eventPublisher;

    // public Negociacao iniciarNegociacao(String idAnuncio, String idBeneficiario, TipoRelacaoBenef tipoRelacao, NegociacaoRespostaDTO negociacaoDTO) {
    //     Usuario beneficiario = userRepo.findUsuarioByIdConta(idBeneficiario)
    //             .orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + idBeneficiario));

    //     // Verifica se o anúncio existe e está ativo
    //     var anuncio = anuncioRepo.findByIdAnuncio(idAnuncio)
    //             .orElseThrow(() -> new RuntimeException("Anúncio não encontrado com ID: " + idAnuncio));

    //     // Lógica para iniciar a negociação
        
    //     Negociacao negociacaoSalva = negociacaoRepo.save(new Negociacao(negociacaoDTO.getQuantidade(), tipoRelacao ));
    //     eventPublisher.publishEvent(new NegociacaoIniciadaEvent(negociacaoSalva));

    // }
}
