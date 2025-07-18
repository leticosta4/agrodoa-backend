package com.labweb.agrodoa_backend.service;

import com.labweb.agrodoa_backend.dto.causa.*;
import com.labweb.agrodoa_backend.events.CausaAbertaEvent;
import com.labweb.agrodoa_backend.model.Causa;
import com.labweb.agrodoa_backend.model.contas.Conta;
import com.labweb.agrodoa_backend.model.contas.Usuario;
import com.labweb.agrodoa_backend.model.enums.StatusCausa;
import com.labweb.agrodoa_backend.model.relacoes.Voluntariado;
import com.labweb.agrodoa_backend.repository.CausaRepository;
import com.labweb.agrodoa_backend.repository.contas.ContaRepository;
import com.labweb.agrodoa_backend.repository.contas.UsuarioRepository;
import com.labweb.agrodoa_backend.repository.relacoes.VoluntariadoRepository;
import com.labweb.agrodoa_backend.service.auxiliares.CloudinaryService;
import com.labweb.agrodoa_backend.service.auxiliares.GeradorIdCustom;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CausaService {
    @Autowired CausaRepository causaRepo;
    @Autowired ContaRepository contaRepo;
    @Autowired UsuarioRepository userRepo;
    @Autowired VoluntariadoRepository voluntariadoRepo;
    @Autowired ApplicationEventPublisher eventPublisher; //publicador de eventos proprio do spring - SUBJECT
    @Autowired CloudinaryService cloudinary;

    private Specification<Causa> criarSpecification(CausaFiltroDTO dto) {
        return Specification
            .where(CausaSpecification.filtrarPorNome(dto.getNome()))
            .and(CausaSpecification.filtrarPorStatus(dto.getStatusEnum()));
    }

    @Transactional(readOnly = true)
    public List<CausaRespostaDTO> buscarComFiltros(CausaFiltroDTO dto) {
        Specification<Causa> spec = criarSpecification(dto);

        return causaRepo.findAll(spec)
                        .stream()
                        .map(CausaRespostaDTO::new)
                        .toList();
    }

    @Transactional(readOnly = true)
    public CausaRespostaDTO buscarPorId(String idCausa) {
        return causaRepo.findById(idCausa)
                .map(CausaRespostaDTO::new) 
                .orElseThrow(() -> new EntityNotFoundException("Causa não encontrada com o ID: " + idCausa));
    }

   
    @Transactional
    public Causa criarCausa(CausaDTO causaDTO, MultipartFile imagem, String idCriador) {
        Conta criador = contaRepo.findById(idCriador)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Conta não encontrado com o ID: " + idCriador));

        String urlImagem = cloudinary.uploadImagem(imagem);
        causaDTO.setNomeArquivoFoto(urlImagem);

        Causa novaCausa = causaDTO.transformaParaObjeto(criador);
        novaCausa.setIdCausa(GeradorIdCustom.gerarIdComPrefixo("CAU", causaRepo, "idCausa"));

        Causa causaSalva = causaRepo.saveAndFlush(novaCausa);

        return causaSalva; 
    }

    @Transactional
    public void aprovarCriarCausa(String idCausa){
        Causa possivelCausa = causaRepo.findByIdCausa(idCausa)
                .orElseThrow(() -> new EntityNotFoundException("Causa não encontrada com o ID: " + idCausa));

        if(!possivelCausa.getPrazo().isAfter(LocalDate.now())){
            throw new IllegalArgumentException("O prazo deve ser uma data futura.");
        } 

        possivelCausa.setStatus(StatusCausa.ABERTA);
        causaRepo.save(possivelCausa);
        eventPublisher.publishEvent(new CausaAbertaEvent(possivelCausa));
    }

    @Transactional
    public void rejeitarCausa(String idCausa){
        Causa possivelCausa = causaRepo.findByIdCausa(idCausa)
                .orElseThrow(() -> new EntityNotFoundException("Causa não encontrada com o ID: " + idCausa));

        possivelCausa.setStatus(StatusCausa.REJEITADA);
        causaRepo.save(possivelCausa);
    }

    @Transactional
    public void virarVoluntario(String idUsuario, String idCausa){
        Causa causaInteresse = causaRepo.findByIdCausa(idCausa)
                    .orElseThrow(() -> new EntityNotFoundException("Causa não encontrada com o ID: " + idCausa));

        Usuario voluntario = verificaPossivelVoluntario(idUsuario);

        if(voluntario.getIdConta().equals(causaInteresse.getCriador().getIdConta())){
            throw new IllegalArgumentException("Você não pode se inscrever nas causas que criou.");
        }

        Voluntariado novoVoluntariado = new Voluntariado(voluntario, causaInteresse);
        voluntariadoRepo.save(novoVoluntariado);
        verificarPrazosEVoluntariosCausas();
        causaRepo.save(causaInteresse);
    }

    @Transactional
    public void concluirCausa(String idUsuario, String idCausa){
        Causa causaInteresse = causaRepo.findByIdCausa(idCausa)
                    .orElseThrow(() -> new EntityNotFoundException("Causa não encontrada com o ID: " + idCausa));

        Usuario voluntario = verificaPossivelVoluntario(idUsuario);

        if(!(voluntario.getIdConta().equals(causaInteresse.getCriador().getIdConta()))){
            throw new IllegalArgumentException("Você não pode concluir uma causa que não criou.");
        }

        Voluntariado novoVoluntariado = new Voluntariado(voluntario, causaInteresse);
        voluntariadoRepo.save(novoVoluntariado);

        causaRepo.save(causaInteresse);
    }

    public List<CausaRespostaDTO> causasVoluntariando(String idUsuario) {
        Usuario user = verificaPossivelVoluntario(idUsuario);

        return voluntariadoRepo.findAllByUsuario(idUsuario).stream()
            .map(idCausa -> causaRepo.findByIdCausa(idCausa)
                .map(causa -> new CausaRespostaDTO(causa)) // conversão aqui
                .orElseThrow(() -> new IllegalArgumentException("Causa não encontrada: " + idCausa)))
            .collect(Collectors.toList());
    }

    public List<CausaRespostaDTO> minhasCausasCriadas(String idUsuario) {
    Usuario user = verificaPossivelVoluntario(idUsuario);

    return causaRepo.findAllByCriador_IdConta(idUsuario).stream()
        .map(causa -> new CausaRespostaDTO(causa)) // converte direto
        .collect(Collectors.toList());
}
    

    private Usuario verificaPossivelVoluntario(String idUsuario){
        return userRepo.findUsuarioByIdConta(idUsuario)
        .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com o ID: " + idUsuario));
    }

    public void verificarPrazosEVoluntariosCausas(){
        for(Causa c: causaRepo.findAllByStatus(StatusCausa.ABERTA.name())){
            if(c.getPrazo().isBefore(LocalDate.now())){
                c.setStatus(StatusCausa.CONCLUIDA);
                causaRepo.save(c);
            }

            c.setQuantVoluntarios(voluntariadoRepo.findAllByCausa(c.getIdCausa()).size());
            causaRepo.save(c);
        }
    }
}
