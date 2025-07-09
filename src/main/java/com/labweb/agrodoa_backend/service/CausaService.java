package com.labweb.agrodoa_backend.service;

import com.labweb.agrodoa_backend.dto.CausaDTO;
import com.labweb.agrodoa_backend.model.Causa;
import com.labweb.agrodoa_backend.observer.CausaCriadaEvent;
import com.labweb.agrodoa_backend.repository.CausaRepository;
import com.labweb.agrodoa_backend.specification.CausaSpecification;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CausaService {

    @Autowired CausaRepository causaRepo;

    @Autowired ApplicationEventPublisher eventPublisher; //publicador de eventos proprio do spring - SUBJECT

    @Transactional(readOnly = true)
    public List<CausaDTO> buscarComFiltros(String nome, Double metaMin, Double metaMax) { 
        
        Specification<Causa> spec = Specification.where(null);

        if (nome != null && !nome.isEmpty()) {
            spec = spec.and(CausaSpecification.filtrarPorNome(nome));
        }
        if (metaMin != null) {
            spec = spec.and(CausaSpecification.filtrarPorMetaMin(metaMin));
        }
        if (metaMax != null) {
            spec = spec.and(CausaSpecification.filtrarPorMetaMax(metaMax));
        }

        List<Causa> listaDeCausas = causaRepo.findAll(spec); 

        return listaDeCausas.stream()
                            .map(CausaDTO::new)
                            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CausaDTO buscarPorId(String idCausa) {
        return causaRepo.findById(idCausa)
                .map(CausaDTO::new) 
                .orElseThrow(() -> new EntityNotFoundException("Causa não encontrada com o ID: " + idCausa));
    }

    @Transactional
    public Causa criarCausa(CausaDTO causaDTO) {
        Causa novaCausa = causaDTO.transformaParaObjeto();
        novaCausa.setIdCausa(GeradorIdCustom.gerarIdComPrefixo("CAU", causaRepo, "idCausa"));
        Causa causaSalva = causaRepo.saveAndFlush(novaCausa);
        
        eventPublisher.publishEvent(new CausaCriadaEvent(causaSalva));
        return causaSalva; 
    }
}
