package com.labweb.agrodoa_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labweb.agrodoa_backend.dto.avaliacao.AvaliacaoRequestDTO;
import com.labweb.agrodoa_backend.model.Avaliacao;
import com.labweb.agrodoa_backend.model.contas.Usuario;
import com.labweb.agrodoa_backend.repository.AvaliacaoRepository;
import com.labweb.agrodoa_backend.repository.contas.UsuarioRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class AvaliacaoService {

    // Injeção dos repositórios necessários para acessar o banco de dados.
    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository; // Supondo que você tenha um repositório para a entidade Usuario


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

        novaAvaliacao.setAvaliador(avaliador);
        novaAvaliacao.setAvaliado(avaliado);

        avaliacaoRepository.saveAndFlush(novaAvaliacao);
    }
}

