package com.labweb.agrodoa_backend.service;

import com.labweb.agrodoa_backend.dto.CausaDTO;
import com.labweb.agrodoa_backend.model.Causa;
import com.labweb.agrodoa_backend.repository.CausaRepository;
import com.labweb.agrodoa_backend.specification.CausaSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException; // ou outra exceção customizada

@Service
@RequiredArgsConstructor // Cria um construtor com os campos 'final' (injeção de dependência)
public class CausaService {

    private final CausaRepository causaRepository;

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

        Page<Causa> paginaDeCausas = causaRepository.findAll(spec, pageable);

        return paginaDeCausas.map(CausaDTO::new); 
    }

    @Transactional(readOnly = true)
    public CausaDTO buscarPorId(String idCausa) {
        return causaRepository.findById(idCausa)
                .map(CausaDTO::new) 
                .orElseThrow(() -> new EntityNotFoundException("Causa não encontrada com o ID: " + idCausa));
    }

    @Transactional
    public Causa criarCausa(CausaDTO causaDTO) {
        
        Causa novaCausa = causaDTO.transformaParaObjeto();
        Causa causaSalva = causaRepository.save(novaCausa);
        
        return causaSalva; 
    }
}
