package com.labweb.agrodoa_backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labweb.agrodoa_backend.dto.anuncio.AnuncioRespostaDTO;
import com.labweb.agrodoa_backend.model.Anuncio;
import com.labweb.agrodoa_backend.model.enums.TipoRelacaoBenef;
import com.labweb.agrodoa_backend.repository.relacoes.RelacaoBeneficiarioRepository;

@Service
public class RelacaoBeneficiarioService {
    @Autowired private RelacaoBeneficiarioRepository rbRepo;
    
    public List<AnuncioRespostaDTO> exibirGrupoAnuncios(String idBeneficiario, TipoRelacaoBenef tipoRelacao){
        List<Anuncio> anuncios = rbRepo.findAnunciosByIdBeneficiarioAndTipo(idBeneficiario, tipoRelacao);

        return anuncios.stream()
                .map(AnuncioRespostaDTO::new)
                .collect(Collectors.toList());
    }
}
