package com.labweb.agrodoa_backend.controller;

import java.util.List;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.labweb.agrodoa_backend.dto.anuncio.AnuncioFiltroDTO;
import com.labweb.agrodoa_backend.dto.anuncio.AnuncioFiltroUsuarioDTO;
import com.labweb.agrodoa_backend.dto.anuncio.AnuncioRespostaDTO;
import com.labweb.agrodoa_backend.service.anuncios.AnuncioService;


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
    
}
