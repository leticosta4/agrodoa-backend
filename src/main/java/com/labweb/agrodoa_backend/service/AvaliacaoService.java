package com.labweb.agrodoa_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labweb.agrodoa_backend.dto.avaliacao.AvaliacaoRequestDTO;
import com.labweb.agrodoa_backend.model.Avaliacao;
import com.labweb.agrodoa_backend.model.contas.Usuario;
import com.labweb.agrodoa_backend.repository.AvaliacaoRepository;
import com.labweb.agrodoa_backend.repository.contas.UsuarioRepository;
import com.labweb.agrodoa_backend.service.auxiliares.GeradorIdCustom;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class AvaliacaoService {
    @Autowired private AvaliacaoRepository avaliacaoRepository;
    @Autowired private UsuarioRepository usuarioRepository; // Supondo que você tenha um repositório para a entidade Usuario


    @Transactional
    public void criarAvaliacao(String idAvaliador, String idAvaliado, AvaliacaoRequestDTO avaliacaoDTO) { 

        Avaliacao novaAvaliacao = new Avaliacao();

        String novoId = GeradorIdCustom.gerarIdComPrefixo("AVA", avaliacaoRepository, "idAvaliacao");
        novaAvaliacao.setIdAvaliacao(novoId);

        novaAvaliacao.setNota(avaliacaoDTO.getNota());
        novaAvaliacao.setComentario(avaliacaoDTO.getComentario());

        Usuario avaliador = usuarioRepository.findUsuarioByIdConta(idAvaliador)
        .orElseThrow(() -> new EntityNotFoundException("Usuário avaliador não encontrado com o ID: " + idAvaliador));

        Usuario avaliado = usuarioRepository.findUsuarioByIdConta(idAvaliado)
        .orElseThrow(() -> new EntityNotFoundException("Usuário avaliado não encontrado com o ID: " + idAvaliado));

        if (avaliador.equals(avaliado)) {
                throw new IllegalArgumentException("O usuário não pode avaliar a si mesmo.");
        }

        Avaliacao avaliacaoExistente = avaliacaoRepository.findByAvaliadorAndAvaliado(avaliador, avaliado);
        if (avaliacaoExistente != null) {
            throw new IllegalArgumentException("Esta avaliacao já existe para este usuário, avaliacao e motivo.");
        }

        novaAvaliacao.setAvaliador(avaliador);
        novaAvaliacao.setAvaliado(avaliado);

        avaliacaoRepository.saveAndFlush(novaAvaliacao);
    }
}

