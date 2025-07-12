package com.labweb.agrodoa_backend.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.labweb.agrodoa_backend.dto.RelacaoBeneficiarioDTO;
import com.labweb.agrodoa_backend.dto.anuncio.AnuncioRespostaDTO;
import com.labweb.agrodoa_backend.model.Anuncio;
import com.labweb.agrodoa_backend.model.contas.Usuario;
import com.labweb.agrodoa_backend.model.enums.StatusAnuncio;
import com.labweb.agrodoa_backend.model.enums.TipoRelacaoBenef;
import com.labweb.agrodoa_backend.model.relacoes.RelacaoBeneficiario;
import com.labweb.agrodoa_backend.repository.AnuncioRepository;
import com.labweb.agrodoa_backend.repository.contas.UsuarioRepository;
import com.labweb.agrodoa_backend.repository.relacoes.RelacaoBeneficiarioRepository;

@Service
public class RelacaoBeneficiarioService {
    @Autowired private RelacaoBeneficiarioRepository rbRepo;
    @Autowired private UsuarioRepository userRepo;
    @Autowired private AnuncioRepository anuncioRepo;
    
    public List<AnuncioRespostaDTO> exibirGrupoAnuncios(String idBeneficiario, TipoRelacaoBenef tipoRelacao){
        List<Anuncio> anuncios = rbRepo.findAnunciosByIdBeneficiarioAndTipo(idBeneficiario, tipoRelacao);

        return anuncios.stream()
                .map(AnuncioRespostaDTO::new)
                .collect(Collectors.toList());
    }

    public RelacaoBeneficiarioDTO criarRelacao(String idAnuncio, String idBeneficiario, TipoRelacaoBenef tipoRelacao) {
        Anuncio anuncio = anuncioRepo.findByIdAnuncio(idAnuncio)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Anúncio não encontrado com ID: " + idAnuncio + "!\n"));

        if(anuncio.getStatus() != StatusAnuncio.ATIVO || anuncio.getDataExpiracao().isBefore(LocalDate.now())){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Não é possível negociar com este anúncio, pois ele não está ativo ou já expirou.");
        }

        if (tipoRelacao == TipoRelacaoBenef.NEGOCIANDO) {
            if(rbRepo.countByAnuncioIdAndTipoRelacao(idAnuncio, TipoRelacaoBenef.NEGOCIANDO) == 5){
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Este anúncio já atingiu o limite de 5 negociantes.");
            }
            
        }

        Usuario beneficiario = userRepo.findUsuarioByIdConta(idBeneficiario)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado com ID: " + idBeneficiario + "!\n"));

        RelacaoBeneficiario relacaoSalva = rbRepo.save(new RelacaoBeneficiario(anuncio, beneficiario, tipoRelacao));

        return new RelacaoBeneficiarioDTO(relacaoSalva);
    }
}
