package com.labweb.agrodoa_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labweb.agrodoa_backend.model.Denuncia;
import com.labweb.agrodoa_backend.model.Motivo;
import com.labweb.agrodoa_backend.model.contas.Usuario;
import com.labweb.agrodoa_backend.repository.DenunciaRepository;
import com.labweb.agrodoa_backend.repository.MotivoRepository;
import com.labweb.agrodoa_backend.repository.contas.UsuarioRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class DenunciaService {

    @Autowired
    private DenunciaRepository denunciaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private MotivoRepository motivoRepository;
    
    @Transactional
    public void criarDenuncia(String idDenunciante, String idDenunciado, String nomeMotivo) {

        Usuario denunciante = usuarioRepository.findById(idDenunciante)
                .orElseThrow(() -> new EntityNotFoundException("Usuário denunciante não encontrado com o ID: " + idDenunciante));

        Usuario denunciado = usuarioRepository.findById(idDenunciado)
                .orElseThrow(() -> new EntityNotFoundException("Usuário a ser denunciado não encontrado com o ID: " + idDenunciado));

        Motivo motivo = motivoRepository.findByNomeIgnoreCase(nomeMotivo)
                .orElseThrow(() -> new EntityNotFoundException("Motivo da denúncia não encontrado com o nome: '" + nomeMotivo + "'"));

        String novoIdDenuncia = GeradorIdCustom.gerarIdComPrefixo("DEN", denunciaRepository, "idDenuncia");
        
        Denuncia novaDenuncia = new Denuncia(motivo, denunciante, denunciado);
        novaDenuncia.setIdDenuncia(novoIdDenuncia);

        denunciaRepository.saveAndFlush(novaDenuncia);
    }
}
