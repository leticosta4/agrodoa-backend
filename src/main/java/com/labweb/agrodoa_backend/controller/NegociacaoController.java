package com.labweb.agrodoa_backend.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.labweb.agrodoa_backend.dto.RelacaoBeneficiarioDTO;
import com.labweb.agrodoa_backend.dto.negociacao.NegociacaoRespostaDTO;
import com.labweb.agrodoa_backend.model.enums.TipoRelacaoBenef;
import com.labweb.agrodoa_backend.service.NegociacaoService;
import com.labweb.agrodoa_backend.service.RelacaoBeneficiarioService;
import com.labweb.agrodoa_backend.service.contas.ContaDetailsService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/anuncios/{idAnuncio}")
public class NegociacaoController {
    @Autowired private ContaDetailsService contaService;
    @Autowired private RelacaoBeneficiarioService relBenefService;
    @Autowired private NegociacaoService negociacaoService;

    @GetMapping("/negociacoes/listar")
    public ResponseEntity<List<Map<String, Object>>> listarNegociacoes(@PathVariable String idAnuncio) {
        
        List<Map<String, Object>> negociacoes = relBenefService.listarNegociacoesAtivasPorAnuncio(idAnuncio);

        if (negociacoes.isEmpty()) {return ResponseEntity.noContent().build();}
        
        return ResponseEntity.ok(negociacoes);
    }

    @PostMapping("/iniciar_negociacao") //p o beneficiario
    public ResponseEntity<RelacaoBeneficiarioDTO> iniciarNegociacao(@PathVariable String idAnuncio, @Valid @RequestBody NegociacaoRespostaDTO negociacaoDTO, @AuthenticationPrincipal UserDetails userDetails) {
        String idBeneficiario = contaService.findIdByEmail(userDetails.getUsername());

        RelacaoBeneficiarioDTO negociacaoIniciada = relBenefService.criarRelacao(idAnuncio, idBeneficiario, TipoRelacaoBenef.NEGOCIANDO, negociacaoDTO.getQuantidade());
        // Negociacao respostaDTO = negociacaoService.iniciarNegociacao(idAnuncio, idBeneficiario, TipoRelacaoBenef.);
        
        return ResponseEntity.ok(negociacaoIniciada);
    }

    @PatchMapping("/negociacoes/{idNegociacao}/aceitar") //p o anunciante fornecedor
    public ResponseEntity<Map<String, Object>> aceitarNegociacao(@PathVariable String idNegociacao) {
        Map<String, Object> resposta = negociacaoService.aceitarNeg(idNegociacao);
        return ResponseEntity.ok(resposta);
    }

    @PatchMapping("/negociacoes/{idNegociacao}/cancelar") //p o anunciante fornecedor
    public ResponseEntity<Map<String, Object>> cancelarNegociacao(@PathVariable String idNegociacao) {
        Map<String, Object> resposta = negociacaoService.cancelarNeg(idNegociacao);
        return ResponseEntity.ok(resposta);
    }
}

