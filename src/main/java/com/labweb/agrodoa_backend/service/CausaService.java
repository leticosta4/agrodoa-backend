package com.labweb.agrodoa_backend.service;

import com.labweb.agrodoa_backend.dto.causa.*;
import com.labweb.agrodoa_backend.dto.relacoes.DoacaoCausaDTO;
import com.labweb.agrodoa_backend.model.Causa;
import com.labweb.agrodoa_backend.model.contas.Usuario;
import com.labweb.agrodoa_backend.model.enums.StatusCausa;
import com.labweb.agrodoa_backend.model.relacoes.DoacaoCausa;
import com.labweb.agrodoa_backend.observer.CausaCriadaEvent;
import com.labweb.agrodoa_backend.repository.CausaRepository;
import com.labweb.agrodoa_backend.repository.contas.UsuarioRepository;
import com.labweb.agrodoa_backend.repository.relacoes.DoacaoCausaRepository;
import com.labweb.agrodoa_backend.specification.CausaSpecification;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CausaService {

    @Autowired CausaRepository causaRepo;
    @Autowired UsuarioRepository userRepo;
    @Autowired DoacaoCausaRepository doacaoCausaRepo;
    @Autowired ApplicationEventPublisher eventPublisher; //publicador de eventos proprio do spring - SUBJECT

    @Transactional(readOnly = true)
    public List<CausaRespostaDTO> buscarComFiltros(String nome, Double metaMin, Double metaMax) {
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
    public Causa criarCausa(CausaDTO causaDTO) {
        Causa novaCausa = causaDTO.transformaParaObjeto();
        novaCausa.setIdCausa(GeradorIdCustom.gerarIdComPrefixo("CAU", causaRepo, "idCausa"));
        Causa causaSalva = causaRepo.saveAndFlush(novaCausa);
        
        eventPublisher.publishEvent(new CausaCriadaEvent(causaSalva));
        return causaSalva; 
    }

    public DoacaoCausaDTO doarParaCausa(String idUser, String idCausa, Double valorDoado){
        Causa causa = causaRepo.findByIdCausa(idCausa)
                 .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Causa não encontrada com ID: " + idCausa + "!\n"));

        if(causa.getStatus() == StatusCausa.CONCLUIDA || causa.getPrazo().isBefore(LocalDate.now())){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Não é possível doar para esta causa, pois ela não está aberta ou já atingiu o prazo.");
        }

        Usuario user = userRepo.findUsuarioByIdConta(idUser)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado com ID: " + idUser + "!\n"));

        //algo para validar o pagamento

        DoacaoCausa doacaoSalva = doacaoCausaRepo.save(new DoacaoCausa(user, causa, valorDoado));
        return new DoacaoCausaDTO(doacaoSalva);
    }
}
