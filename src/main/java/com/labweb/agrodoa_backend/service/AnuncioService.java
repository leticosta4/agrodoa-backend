package com.labweb.agrodoa_backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.labweb.agrodoa_backend.dto.anuncio.AnuncioFiltroDTO;
import com.labweb.agrodoa_backend.dto.anuncio.AnuncioFiltroUsuarioDTO;
import com.labweb.agrodoa_backend.dto.anuncio.AnuncioRespostaDTO;
import com.labweb.agrodoa_backend.model.Anuncio;
import com.labweb.agrodoa_backend.repository.AnuncioRepository;
import com.labweb.agrodoa_backend.repository.ProdutoRepository;
import com.labweb.agrodoa_backend.repository.contas.UsuarioRepository;
import com.labweb.agrodoa_backend.repository.local.CidadeRepository;
import com.labweb.agrodoa_backend.specification.AnuncioSpecification;

@Service
public class AnuncioService {
    @Autowired private AnuncioRepository anuncioRepo;
    // @Autowired private CidadeRepository cidadeRepo;
    // @Autowired private ProdutoRepository produtoRepo;
    // @Autowired private UsuarioRepository userRepo; // Para buscar o anunciante

    private Specification<Anuncio> criarSpecification(AnuncioFiltroDTO dto) {
        return Specification
            .where(AnuncioSpecification.filtrarPorNome(dto.getNome()))
            .and(AnuncioSpecification.filtrarPorCidade(dto.getCidade()))
            .and(AnuncioSpecification.filtrarPorPrecoMin(dto.getPrecoMin()))
            .and(AnuncioSpecification.filtrarPorPrecoMax(dto.getPrecoMax()))
            .and(AnuncioSpecification.filtrarPorTipo(dto.getTipoEnum()))
            .and(AnuncioSpecification.filtrarPorDataExpiracao(dto.getDataExpiracao()))
            .and(AnuncioSpecification.filtrarPorStatus(dto.getStatusEnum()));
    }
    

    public List<AnuncioRespostaDTO> buscarAnunciosFiltro(AnuncioFiltroDTO dto){
        Specification<Anuncio> spec = criarSpecification(dto);
                                                
        return anuncioRepo.findAll(spec)
                          .stream()
                          .map(AnuncioRespostaDTO::new)
                          .toList();
    }   
    
    
    public Optional<AnuncioRespostaDTO> buscarAnuncioPorId(String anuncioId){
        return anuncioRepo.findById(anuncioId).map(AnuncioRespostaDTO::new);
    }

    public List<AnuncioRespostaDTO> buscarAnunciosFiltroUsuario(AnuncioFiltroUsuarioDTO dto){
        Specification<Anuncio> spec = 
                                criarSpecification(dto)
                                .and(AnuncioSpecification.filtrarPorAnuncianteId(dto.getIdAnunciante()));

        return anuncioRepo.findAll(spec)
                          .stream()
                          .map(AnuncioRespostaDTO::new)
                          .toList();
    }

    //fazer a de criar anuncio
}
