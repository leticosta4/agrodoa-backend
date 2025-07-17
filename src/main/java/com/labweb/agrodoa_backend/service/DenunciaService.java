package com.labweb.agrodoa_backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.labweb.agrodoa_backend.dto.denuncia.DenunciaRespostaDTO;
import com.labweb.agrodoa_backend.events.DenunciaAprovadaEvent;
import com.labweb.agrodoa_backend.model.Denuncia;
import com.labweb.agrodoa_backend.model.Motivo;
import com.labweb.agrodoa_backend.model.contas.Usuario;
import com.labweb.agrodoa_backend.model.enums.StatusDenuncia;
import com.labweb.agrodoa_backend.repository.DenunciaRepository;
import com.labweb.agrodoa_backend.repository.MotivoRepository;
import com.labweb.agrodoa_backend.repository.contas.UsuarioRepository;
import com.labweb.agrodoa_backend.service.auxiliares.GeradorIdCustom;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class DenunciaService {
    @Autowired private DenunciaRepository denunciaRepository;
    @Autowired private UsuarioRepository usuarioRepository;
    @Autowired private MotivoRepository motivoRepository;
    @Autowired private ApplicationEventPublisher eventPublisher;

    public List<DenunciaRespostaDTO> listarTodas() {

        List<Denuncia> denuncias = denunciaRepository.findAll();

        return denuncias.stream().map(DenunciaRespostaDTO::new).collect(Collectors.toList());
    }
    
    @Transactional
    public void criarDenuncia(String idDenunciante, String idDenunciado, String nomeMotivo) {
        Usuario denunciante = usuarioRepository.findById(idDenunciante)
                .orElseThrow(() -> new EntityNotFoundException("Usuário denunciante não encontrado com o ID: " + idDenunciante));

        Usuario denunciado = usuarioRepository.findById(idDenunciado)
                .orElseThrow(() -> new EntityNotFoundException("Usuário a ser denunciado não encontrado com o ID: " + idDenunciado));

        Motivo motivo = motivoRepository.findByNomeIgnoreCase(nomeMotivo)
                .orElseThrow(() -> new EntityNotFoundException("Motivo da denúncia não encontrado com o nome: '" + nomeMotivo + "'"));

        if (denunciante.equals(denunciado)) {
                throw new IllegalArgumentException("O usuário não pode denunciar a si mesmo.");
        }

        Denuncia denunciaExistente = denunciaRepository.findByDenuncianteAndDenunciado(denunciante, denunciado);
        if (denunciaExistente != null) {
            throw new IllegalArgumentException("Esta denúncia já existe para este usuário, denunciado e motivo.");
        }

        String novoIdDenuncia = GeradorIdCustom.gerarIdComPrefixo("DEN", denunciaRepository, "idDenuncia");
        
        Denuncia novaDenuncia = new Denuncia(motivo, denunciante, denunciado);
        novaDenuncia.setIdDenuncia(novoIdDenuncia);

        denunciaRepository.saveAndFlush(novaDenuncia);
    }

    @Transactional
    public void avaliarDenuncia(String idDenuncia, String avaliacao) {
        Denuncia denuncia = denunciaRepository.findById(idDenuncia)
                .orElseThrow(() -> new EntityNotFoundException("Denúncia não encontrada com o ID: " + idDenuncia));

        if(denuncia.getStatus() != StatusDenuncia.AGUARDANDO){
            throw new IllegalArgumentException("Essa denúncia já foi avaliada anteriormente.");
        }

        StatusDenuncia novoStatus = StatusDenuncia.valueOf(avaliacao.toUpperCase());
        denuncia.setStatus(novoStatus);

        denunciaRepository.saveAndFlush(denuncia);

        if (novoStatus == StatusDenuncia.APROVADA) {
            eventPublisher.publishEvent(new DenunciaAprovadaEvent(denuncia));
        }
    }
}
