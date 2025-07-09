package com.labweb.agrodoa_backend.controller;

import java.net.URI;
import java.util.List;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.labweb.agrodoa_backend.model.Anuncio;
import com.labweb.agrodoa_backend.service.AnuncioService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/anuncios")
public class AnuncioController {

    @Autowired
    private AnuncioService anuncioService;


    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<AnuncioRespostaDTO>> buscarAnuncioPorUsuario(
        @PathVariable String idUsuario,
        @ParameterObject @ModelAttribute AnuncioFiltroDTO filtro){


        AnuncioFiltroUsuarioDTO filtroComUsuario = new AnuncioFiltroUsuarioDTO();
        BeanUtils.copyProperties(filtro, filtroComUsuario);
        filtroComUsuario.setIdAnunciante(idUsuario);
        
        List<AnuncioRespostaDTO> anuncios = anuncioService.buscarAnunciosFiltroUsuario(filtroComUsuario);
        if(anuncios.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(anuncios);

    }
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

    @PostMapping
    public ResponseEntity<AnuncioRespostaDTO> criarAnuncio(@Valid @RequestBody AnuncioDTO anuncioDTO, @AuthenticationPrincipal UserDetails userDetails) {
        
        String idAnunciante = userDetails.getUsername(); 

        Anuncio anuncioSalvo = anuncioService.criarAnuncio(anuncioDTO, idAnunciante);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{idAnuncio}")
                .buildAndExpand(anuncioSalvo.getIdAnuncio())
                .toUri();

        AnuncioRespostaDTO respostaDTO = new AnuncioRespostaDTO(anuncioSalvo);

        return ResponseEntity.created(location).body(respostaDTO);
    }

     @PutMapping("/{idAnuncio}")
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
