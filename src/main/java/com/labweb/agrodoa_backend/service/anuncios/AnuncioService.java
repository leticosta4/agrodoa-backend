package com.labweb.agrodoa_backend.service.anuncios;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.labweb.agrodoa_backend.model.Anuncio;
import com.labweb.agrodoa_backend.repository.AnuncioRepository;
import com.labweb.agrodoa_backend.specification.anuncio.CidadeSpecification;
import com.labweb.agrodoa_backend.specification.anuncio.NomeSpecification;
import com.labweb.agrodoa_backend.specification.anuncio.PrecoMinimoSpecification;
import com.labweb.agrodoa_backend.specification.anuncio.TipoSpecification;


@Service
public class AnuncioService {

    @Autowired
    private AnuncioRepository anuncioRepo;

    public List<Anuncio> buscarAnunciosFiltro(String nome, String cidade, BigDecimal precoMin, String tipo){
        Specification<Anuncio> spec = Specification
                                                .where(new NomeSpecification(nome))
                                                .and(new CidadeSpecification(cidade))
                                                .and(new PrecoMinimoSpecification(precoMin))
                                                .and(new TipoSpecification(tipo));

                                                
        return anuncioRepo.findAll(spec);
    }    

}
