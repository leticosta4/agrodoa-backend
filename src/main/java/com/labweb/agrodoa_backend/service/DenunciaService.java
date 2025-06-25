package com.labweb.agrodoa_backend.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labweb.agrodoa_backend.model.Denuncia;
import com.labweb.agrodoa_backend.repository.DenunciaRepository;
import com.labweb.agrodoa_backend.service.strategyDenuncia.AcaoDenunciaStrategy;

@Service
public class DenunciaService {
    
    @Autowired
    private Map<String, AcaoDenunciaStrategy> acaoStrategies;

    @Autowired
    private DenunciaRepository denunciaRepository;

    public void avaliarDenuncia(Long denunciaId, String novaAcao) { 
        
        Denuncia denuncia = denunciaRepository.findById(denunciaId).orElseThrow();
        AcaoDenunciaStrategy strategy = acaoStrategies.get(novaAcao);
        if (strategy != null) {
            strategy.processar(denuncia);
        } else {
            throw new IllegalArgumentException("Ação inválida para a denúncia.");
        }
    }
}
