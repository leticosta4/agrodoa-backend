package com.labweb.agrodoa_backend.service;

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

        if(tipoRelacao == TipoRelacaoBenef.NEGOCIANDO){
            //chamar funcao auxiliar para ver quantidade de negociantes
        }

        Usuario beneficiario = userRepo.findUsuarioByIdConta(idBeneficiario)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado com ID: " + idBeneficiario + "!\n"));

        RelacaoBeneficiario relacaoSalva = rbRepo.save(new RelacaoBeneficiario(anuncio, beneficiario, tipoRelacao));

        return new RelacaoBeneficiarioDTO(relacaoSalva);
    }
}
