package com.labweb.agrodoa_backend.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.labweb.agrodoa_backend.service.AnuncioService;
import com.labweb.agrodoa_backend.service.NegociacaoService;
import com.labweb.agrodoa_backend.service.ProdutoService;
import com.labweb.agrodoa_backend.service.RelacaoBeneficiarioService;
import com.labweb.agrodoa_backend.service.contas.ContaDetailsService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/anuncios")
public class NegociacaoController {
    @Autowired private AnuncioService anuncioService;
    @Autowired private ContaDetailsService contaService;
    @Autowired private ProdutoService produtoService;
    @Autowired private RelacaoBeneficiarioService relBenefService;
    @Autowired private NegociacaoService negociacaoService;

    // @GetMapping("/{idAnuncio}/listar_negociacoes") // retornar idAnuncio, quantidade, quem pediu, idNegociacaoexport interface Negociacao{
    // idNegociacao:string,
    // idAnuncio:string,
    // pedinte:string,
    // quantidade:number,
    // anuncioNome:string,
    // fotoBeneficiario:string
    // public ResponseEntity<List<RelacaoBeneficiarioDTO>> listarNegociacoes(@PathVariable String idAnuncio, @AuthenticationPrincipal UserDetails userDetails) {
        
    //     List<RelacaoBeneficiarioDTO> negociacoes = relBenefService.listarNegociacoesAtivasPorAnuncio(idAnuncio);

    //     if (negociacoes.isEmpty()) {return ResponseEntity.noContent().build();}
        
    //     return ResponseEntity.ok(negociacoes);
    // }

    @GetMapping("/{idAnuncio}/listar_negociacoes")
    public ResponseEntity<List<Map<String, Object>>> listarNegociacoes(@PathVariable String idAnuncio) {
        
        List<Map<String, Object>> negociacoes = relBenefService.listarNegociacoesAtivasPorAnuncio(idAnuncio);

        if (negociacoes.isEmpty()) {return ResponseEntity.noContent().build();}
        
        return ResponseEntity.ok(negociacoes);
    }

    @PostMapping("/{idAnuncio}/iniciar_negociacao") 
    public ResponseEntity<RelacaoBeneficiarioDTO> iniciarNegociacao(@PathVariable String idAnuncio, @Valid @RequestBody NegociacaoRespostaDTO negociacaoDTO, @AuthenticationPrincipal UserDetails userDetails) {
        String idBeneficiario = contaService.findIdByEmail(userDetails.getUsername());

        RelacaoBeneficiarioDTO negociacaoIniciada = relBenefService.criarRelacao(idAnuncio, idBeneficiario, TipoRelacaoBenef.NEGOCIANDO, negociacaoDTO.getQuantidade());
        // Negociacao respostaDTO = negociacaoService.iniciarNegociacao(idAnuncio, idBeneficiario, TipoRelacaoBenef.);
        
        return ResponseEntity.ok(negociacaoIniciada);
    }

    @PatchMapping("/{idNegociacao}/aceitar_negociacao")
    public ResponseEntity<Map<String, Object>> aceitarNegociacao(@PathVariable String idNegociacao) {
        Map<String, Object> resposta = negociacaoService.aceitarNeg(idNegociacao);
        return ResponseEntity.ok(resposta);
    }

    @PatchMapping("/{idNegociacao}/cancelar_negociacao")
    public ResponseEntity<Map<String, Object>> cancelarNegociacao(@PathVariable String idNegociacao) {
        Map<String, Object> resposta = negociacaoService.cancelarNeg(idNegociacao);
        return ResponseEntity.ok(resposta);
    }
}


    // Aceitar e recusar - com envio de emil, decrementar a quantidade aprovada com a quantidade total do anuncio, caso a quantidade do anuncio chegue q 0 cancela automaticamente
    // todas as relações em andamento com o anuncio, e muda o status do anuncio para FINALIZADO

