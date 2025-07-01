package com.labweb.agrodoa_backend.service.anuncios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.labweb.agrodoa_backend.dto.anuncio.AnuncioFiltroDTO;
import com.labweb.agrodoa_backend.dto.anuncio.AnuncioRespostaDTO;
import com.labweb.agrodoa_backend.model.Anuncio;
import com.labweb.agrodoa_backend.repository.AnuncioRepository;
import com.labweb.agrodoa_backend.specification.AnuncioSpecification;

@Service
public class AnuncioService {
    @Autowired
    private AnuncioRepository anuncioRepo;

    public List<AnuncioRespostaDTO> buscarAnunciosFiltro(AnuncioFiltroDTO dto){
        Specification<Anuncio> spec = Specification
                                                .where(AnuncioSpecification.filtrarPorNome(dto.getNome()))
                                                .and(AnuncioSpecification.filtrarPorCidade(dto.getCidade()))
                                                .and(AnuncioSpecification.filtrarPorPrecoMin(dto.getPrecoMin()))
                                                .and(AnuncioSpecification.filtrarPorPrecoMax(dto.getPrecoMax()))
                                                .and(AnuncioSpecification.filtrarPorTipo(dto.getTipo()))
                                                .and(AnuncioSpecification.filtrarPorDataExpiracao(dto.getDataExpiracao()))
                                                .and(AnuncioSpecification.filtrarPorStatus(dto.getStatus()));

                                                
        return anuncioRepo.findAll(spec)
                          .stream()
                          .map(AnuncioRespostaDTO::new)
                          .toList();
    }    
}
