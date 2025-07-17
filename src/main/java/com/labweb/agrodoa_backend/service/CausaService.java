package com.labweb.agrodoa_backend.service;

import com.labweb.agrodoa_backend.dto.causa.*;
import com.labweb.agrodoa_backend.model.Causa;
import com.labweb.agrodoa_backend.observer.CausaCriadaEvent;
import com.labweb.agrodoa_backend.repository.CausaRepository;
import com.labweb.agrodoa_backend.service.auxiliares.CloudinaryService;
import com.labweb.agrodoa_backend.service.auxiliares.GeradorIdCustom;
import com.labweb.agrodoa_backend.specification.CausaSpecification;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CausaService {
    @Autowired CausaRepository causaRepo;
    @Autowired ApplicationEventPublisher eventPublisher; //publicador de eventos proprio do spring - SUBJECT
    @Autowired CloudinaryService cloudinary;

    @Transactional(readOnly = true)
    public List<CausaRespostaDTO> buscarComFiltros(String nome) {
        Specification<Causa> spec = Specification.where(null);

        if (nome != null && !nome.isEmpty()) {
            spec = spec.and(CausaSpecification.filtrarPorNome(nome));
        }

        List<Causa> causas = causaRepo.findAll(spec);

        return causas.stream().map(CausaRespostaDTO::new).collect(Collectors.toList()); 
    }

    @Transactional(readOnly = true)
    public CausaRespostaDTO buscarPorId(String idCausa) {
        return causaRepo.findById(idCausa)
                .map(CausaRespostaDTO::new) 
                .orElseThrow(() -> new EntityNotFoundException("Causa não encontrada com o ID: " + idCausa));
    }


    
    @Transactional
    public Causa criarCausa(CausaDTO causaDTO, MultipartFile imagem) {
        String urlImagem = cloudinary.uploadImagem(imagem);
        causaDTO.setNomeArquivoFoto(urlImagem);
        Causa novaCausa = causaDTO.transformaParaObjeto();
        novaCausa.setIdCausa(GeradorIdCustom.gerarIdComPrefixo("CAU", causaRepo, "idCausa"));
        Causa causaSalva = causaRepo.saveAndFlush(novaCausa);
        eventPublisher.publishEvent(new CausaCriadaEvent(causaSalva));
        return causaSalva; 
    }
}
