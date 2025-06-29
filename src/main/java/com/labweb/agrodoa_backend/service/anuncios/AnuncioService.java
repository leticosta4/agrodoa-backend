package com.labweb.agrodoa_backend.service.anuncios;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.labweb.agrodoa_backend.dto.anuncio.AnuncioFiltroDTO;
import com.labweb.agrodoa_backend.dto.anuncio.AnuncioRespostaDTO;
import com.labweb.agrodoa_backend.model.Anuncio;
import com.labweb.agrodoa_backend.repository.AnuncioRepository;
import com.labweb.agrodoa_backend.specification.TipoSpecification;
import com.labweb.agrodoa_backend.specification.anuncio.CidadeSpecification;
import com.labweb.agrodoa_backend.specification.anuncio.NomeSpecification;
import com.labweb.agrodoa_backend.specification.anuncio.PrecoMinimoSpecification;


@Service
public class AnuncioService {

    @Autowired
    private AnuncioRepository anuncioRepo;

    public List<AnuncioRespostaDTO> buscarAnunciosFiltro(AnuncioFiltroDTO dto){
        Specification<Anuncio> spec = Specification
                                                .where(new NomeSpecification(dto.getNome()))
                                                .and(new CidadeSpecification(dto.getCidade()))
                                                .and(new PrecoMinimoSpecification(dto.getPrecoMin()));
                                                //.and(new TipoSpecification<Anuncio>(dto.getTipo()));

                                                
        return anuncioRepo.findAll(spec)
                          .stream()
                          .map(AnuncioRespostaDTO::new)
                          .toList();
    }    

}
