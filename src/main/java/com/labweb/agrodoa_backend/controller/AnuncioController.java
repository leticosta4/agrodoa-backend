package com.labweb.agrodoa_backend.controller;

import java.net.URI;
import java.util.List;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.labweb.agrodoa_backend.dto.anuncio.AnuncioDTO;
import com.labweb.agrodoa_backend.dto.anuncio.AnuncioFiltroDTO;
import com.labweb.agrodoa_backend.dto.anuncio.AnuncioFiltroUsuarioDTO;
import com.labweb.agrodoa_backend.dto.anuncio.AnuncioRespostaDTO;
import com.labweb.agrodoa_backend.dto.produto.ProdutoDTO;
import com.labweb.agrodoa_backend.dto.produto.ProdutoRespostaDTO;
import com.labweb.agrodoa_backend.model.Anuncio;
import com.labweb.agrodoa_backend.model.Produto;
import com.labweb.agrodoa_backend.service.AnuncioService;
import com.labweb.agrodoa_backend.service.ProdutoService;
import com.labweb.agrodoa_backend.service.contas.ContaDetailsService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/anuncios")
public class AnuncioController {

    @Autowired
    private AnuncioService anuncioService;

    @Autowired
    private ContaDetailsService contaService;

    @Autowired
    private ProdutoService produtoService;

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

    // Metodos CRUD

    @PostMapping("/criar_anuncio")
    public ResponseEntity<AnuncioRespostaDTO> criarAnuncio(@Valid @RequestBody AnuncioDTO anuncioDTO, @AuthenticationPrincipal UserDetails userDetails) {
        
        String idAnunciante = contaService.findIdByEmail(userDetails.getUsername());
        Anuncio anuncioSalvo = anuncioService.criarAnuncio(anuncioDTO, idAnunciante);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{idAnuncio}")
                .buildAndExpand(anuncioSalvo.getIdAnuncio()).toUri();

        AnuncioRespostaDTO respostaDTO = new AnuncioRespostaDTO(anuncioSalvo);

        return ResponseEntity.created(location).body(respostaDTO);
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
    

    @PutMapping("/{idAnuncio}/editar")
    public ResponseEntity<AnuncioRespostaDTO> editarAnuncio(@PathVariable String idAnuncio, @Valid @RequestBody AnuncioDTO anuncioDTO) {
        AnuncioRespostaDTO anuncioAtualizado = anuncioService.editarAnuncio(idAnuncio, anuncioDTO);
        return ResponseEntity.ok(anuncioAtualizado);
    }
    
    @PatchMapping("/{idAnuncio}/cancelar")
    public ResponseEntity<Void> cancelarAnuncio(@PathVariable String idAnuncio) {
        anuncioService.cancelarAnuncio(idAnuncio);
        return ResponseEntity.noContent().build();
    }
}
