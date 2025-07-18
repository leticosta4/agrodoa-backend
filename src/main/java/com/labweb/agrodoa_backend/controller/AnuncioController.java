package com.labweb.agrodoa_backend.controller;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.labweb.agrodoa_backend.dto.RelacaoBeneficiarioDTO;
import com.labweb.agrodoa_backend.dto.anuncio.AnuncioDTO;
import com.labweb.agrodoa_backend.dto.anuncio.AnuncioEditarDTO;
import com.labweb.agrodoa_backend.dto.anuncio.AnuncioFiltroDTO;
import com.labweb.agrodoa_backend.dto.anuncio.AnuncioRespostaDTO;
import com.labweb.agrodoa_backend.dto.produto.ProdutoDTO;
import com.labweb.agrodoa_backend.dto.produto.ProdutoRespostaDTO;
import com.labweb.agrodoa_backend.model.Anuncio;
import com.labweb.agrodoa_backend.model.Produto;
import com.labweb.agrodoa_backend.model.enums.TipoRelacaoBenef;
import com.labweb.agrodoa_backend.service.AnuncioService;
import com.labweb.agrodoa_backend.service.ProdutoService;
import com.labweb.agrodoa_backend.service.RelacaoBeneficiarioService;
import com.labweb.agrodoa_backend.service.contas.ContaDetailsService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/anuncios")
public class AnuncioController {
    @Autowired private AnuncioService anuncioService;
    @Autowired private ContaDetailsService contaService;
    @Autowired private ProdutoService produtoService;
    @Autowired private RelacaoBeneficiarioService relBenefService;

    @GetMapping
    public ResponseEntity<List<AnuncioRespostaDTO>> listarAnunciosFiltro(@ParameterObject @ModelAttribute AnuncioFiltroDTO filtro){
        List<AnuncioRespostaDTO> anuncios = anuncioService.buscarAnunciosFiltro(filtro);

        System.out.println("FILTROS PEGOS" + filtro);
        if(anuncios.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(anuncios);
       
    }

    @GetMapping("/{idAnuncio}")
    public ResponseEntity<AnuncioRespostaDTO> buscarId(@PathVariable String idAnuncio) {
       return anuncioService.buscarAnuncioPorId(idAnuncio)
                         .map(ResponseEntity::ok)
                         .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{idAnuncio}/salvar")
    public ResponseEntity<RelacaoBeneficiarioDTO> salvarAnuncio(@PathVariable String idAnuncio, @AuthenticationPrincipal UserDetails userDetails) {
        String idBeneficiario = contaService.findIdByEmail(userDetails.getUsername());
        RelacaoBeneficiarioDTO salvou = relBenefService.criarRelacao(idAnuncio, idBeneficiario, TipoRelacaoBenef.SALVOU, 0);

       return ResponseEntity.ok(salvou);
    }

    //Movido para NegociacaoController
    
    // @PostMapping("/{idAnuncio}/iniciar_negociacao") //vai receber uma quantidade
    // public ResponseEntity<RelacaoBeneficiarioDTO> iniciarNegociacao(@PathVariable String idAnuncio, @Valid @RequestBody NegociacaoRespostaDTO negociacaoDTO, @AuthenticationPrincipal UserDetails userDetails) {
    //     String idBeneficiario = contaService.findIdByEmail(userDetails.getUsername());

    //     RelacaoBeneficiarioDTO negociacaoIniciada = relBenefService.criarRelacao(idAnuncio, idBeneficiario, TipoRelacaoBenef.NEGOCIANDO, negociacaoDTO.getQuantidade());

    //     // Negociacao respostaDTO = negociacaoService.iniciarNegociacao(idAnuncio, idBeneficiario, TipoRelacaoBenef.);
        
    //     return ResponseEntity.ok(negociacaoIniciada);
    // }

    // Metodos CRUD
    @PostMapping(value = "/criar_anuncio", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AnuncioRespostaDTO> criarAnuncio(
        @RequestParam String titulo,
        @RequestParam String descricao,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataExpiracao,
        @RequestParam int entregaPeloFornecedor,
        @RequestParam String cidadeId,
        @RequestParam String produtoId,
        @RequestParam(value = "imagem", required = false) MultipartFile imagem,
        @AuthenticationPrincipal UserDetails userDetails
    ) {
        String idAnunciante = contaService.findIdByEmail(userDetails.getUsername());

        AnuncioDTO dto = new AnuncioDTO();
        dto.setTitulo(titulo);
        dto.setDescricao(descricao);
        dto.setDataExpiracao(dataExpiracao);
        dto.setEntregaPeloFornecedor(entregaPeloFornecedor);
        dto.setCidadeId(cidadeId);
        dto.setProdutoId(produtoId);
     
        Anuncio anuncioSalvo = anuncioService.criarAnuncio(dto, idAnunciante, imagem);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{idAnuncio}")
            .buildAndExpand(anuncioSalvo.getIdAnuncio())
            .toUri();

        return ResponseEntity.created(location).body(new AnuncioRespostaDTO(anuncioSalvo));
    }

    @PostMapping("/criar_anuncio/criar_produto")
    public ResponseEntity<ProdutoRespostaDTO> criarProduto(@Valid @RequestBody ProdutoDTO produtoDTO) {

        Produto resposta = produtoService.criarProduto(produtoDTO);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{idProduto}")
                    .buildAndExpand(resposta.getIdProduto()).toUri();

        ProdutoRespostaDTO respostaDTO = new ProdutoRespostaDTO(resposta);

        return ResponseEntity.created(location).body(respostaDTO); //talvez retornar o id do produto para podermos usar no /criar_anuncio
    }
    

    //isso aqui tem que ser restrito ao user que tá logado 
    @PutMapping("/{idAnuncio}/editar")
    public ResponseEntity<AnuncioRespostaDTO> editarAnuncio(@PathVariable String idAnuncio, @Valid @RequestBody AnuncioEditarDTO anuncioDTO) {
        AnuncioRespostaDTO anuncioAtualizado = anuncioService.editarAnuncio(idAnuncio, anuncioDTO);
        return ResponseEntity.ok(anuncioAtualizado);
    }
    
    @PatchMapping("/{idAnuncio}/cancelar")
    public ResponseEntity<Void> cancelarAnuncio(@PathVariable String idAnuncio) {
        anuncioService.cancelarAnuncio(idAnuncio);
        return ResponseEntity.noContent().build();
    }
}
