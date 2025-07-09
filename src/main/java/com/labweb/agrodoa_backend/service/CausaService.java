package com.labweb.agrodoa_backend.service;

import com.labweb.agrodoa_backend.dto.CausaDTO;
import com.labweb.agrodoa_backend.model.Causa;
import com.labweb.agrodoa_backend.repository.CausaRepository;
import com.labweb.agrodoa_backend.specification.CausaSpecification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException; // ou outra exceção customizada

@Service
public class CausaService {

    @Autowired CausaRepository causaRepo;

    @Transactional(readOnly = true)
    public Page<CausaDTO> buscarComFiltros(String nome, Double metaMin, Double metaMax, Pageable pageable) {
        
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

        Page<Causa> paginaDeCausas = causaRepo.findAll(spec, pageable);

        return paginaDeCausas.map(CausaDTO::new); 
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
        
        return causaSalva; 
    }
}
