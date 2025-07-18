package com.labweb.agrodoa_backend.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.labweb.agrodoa_backend.dto.RelacaoBeneficiarioDTO;
import com.labweb.agrodoa_backend.dto.anuncio.AnuncioRespostaDTO;
import com.labweb.agrodoa_backend.model.Anuncio;
import com.labweb.agrodoa_backend.model.Negociacao;
import com.labweb.agrodoa_backend.model.contas.Usuario;
import com.labweb.agrodoa_backend.model.enums.StatusAnuncio;
import com.labweb.agrodoa_backend.model.enums.StatusNegociacao;
import com.labweb.agrodoa_backend.model.enums.TipoRelacaoBenef;
import com.labweb.agrodoa_backend.model.relacoes.RelacaoBeneficiario;
import com.labweb.agrodoa_backend.events.NegociacaoIniciadaEvent;
import com.labweb.agrodoa_backend.repository.AnuncioRepository;
import com.labweb.agrodoa_backend.repository.NegociacaoRepository;
import com.labweb.agrodoa_backend.repository.contas.UsuarioRepository;
import com.labweb.agrodoa_backend.repository.relacoes.RelacaoBeneficiarioRepository;
import com.labweb.agrodoa_backend.service.auxiliares.GeradorIdCustom;

@Service
public class RelacaoBeneficiarioService {
    @Autowired private RelacaoBeneficiarioRepository rbRepo;
    @Autowired private UsuarioRepository userRepo;
    @Autowired private AnuncioRepository anuncioRepo;
    @Autowired private NegociacaoRepository negociacaoRepo;
    @Autowired private ApplicationEventPublisher eventPublisher;
    
    public List<AnuncioRespostaDTO> exibirGrupoAnuncios(String idBeneficiario, TipoRelacaoBenef tipoRelacao){
        List<Anuncio> anuncios = rbRepo.findAnunciosByIdBeneficiarioAndTipo(idBeneficiario, tipoRelacao);

        return anuncios.stream()
                .map(AnuncioRespostaDTO::new)
                .collect(Collectors.toList());
    }



    // public List<RelacaoBeneficiarioDTO> listarNegociacoesAtivasPorAnuncio(String idAnuncio) {

    //     List<StatusNegociacao> statusAtivos = List.of(StatusNegociacao.AGUARDANDO);
    //     List<RelacaoBeneficiario> relacoes = rbRepo.findNegociacoesAtivasPorAnuncio(idAnuncio, TipoRelacaoBenef.NEGOCIANDO,statusAtivos);

    //     return relacoes.stream().map(RelacaoBeneficiarioDTO::new).collect(Collectors.toList());
    // }

    public List<Map<String, Object>> listarNegociacoesAtivasPorAnuncio(String idAnuncio) {

        List<StatusNegociacao> statusAtivos = List.of(StatusNegociacao.AGUARDANDO);

        // 1. Busca as negociações do banco de dados
        List<Negociacao> negociacoes = negociacaoRepo.findNegociacoesAtivasPorAnuncio(
            idAnuncio,
            TipoRelacaoBenef.NEGOCIANDO,
            statusAtivos
        );

        // 2. Transforma a lista de entidades em uma lista de mapas
        List<Map<String, Object>> respostaFormatada = new ArrayList<>();
        for (Negociacao neg : negociacoes) {
            Map<String, Object> negociacaoMap = new HashMap<>();

            // Extrai os objetos relacionados para facilitar a leitura
            Anuncio anuncio = neg.getRelacao().getAnuncio();
            Usuario pedinte = neg.getRelacao().getBeneficiario();

            // 3. Preenche o mapa com os dados e nomes exatos que o frontend precisa
            negociacaoMap.put("idNegociacao", neg.getIdNegociacao());
            negociacaoMap.put("idAnuncio", anuncio.getIdAnuncio());
            negociacaoMap.put("pedinte", pedinte.getNome());
            negociacaoMap.put("quantidade", neg.getQuantidade());
            negociacaoMap.put("anuncioNome", anuncio.getTitulo());
            negociacaoMap.put("fotoBeneficiario", pedinte.getNomeArquivoFoto());
            
            respostaFormatada.add(negociacaoMap);
        }

        return respostaFormatada;
    }



    public RelacaoBeneficiarioDTO criarRelacao(String idAnuncio, String idBeneficiario, TipoRelacaoBenef tipoRelacao, Integer quantidade) {
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

        if(tipoRelacao == TipoRelacaoBenef.NEGOCIANDO){
            String novoId = GeradorIdCustom.gerarIdComPrefixo("NEG", negociacaoRepo, "idNegociacao");
            // Negociacao negociacaoSalva = negociacaoRepo.save(new Negociacao(quantidade, relacaoSalva));
            Negociacao novaNegociacao = new Negociacao(quantidade, relacaoSalva);
            novaNegociacao.setIdNegociacao(novoId);
            Negociacao negociacaoSalva = negociacaoRepo.save(novaNegociacao);

            eventPublisher.publishEvent(new NegociacaoIniciadaEvent(negociacaoSalva));
        }

        return new RelacaoBeneficiarioDTO(relacaoSalva);
    }
}
