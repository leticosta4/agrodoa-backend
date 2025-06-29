package com.labweb.agrodoa_backend.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.labweb.agrodoa_backend.dto.anuncio.AnuncioFiltroDTO;
import com.labweb.agrodoa_backend.dto.anuncio.AnuncioRespostaDTO;
import com.labweb.agrodoa_backend.model.Anuncio;
import com.labweb.agrodoa_backend.service.anuncios.AnuncioService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/anuncios")
public class AnuncioController {


    @Autowired
    private AnuncioService anuncioService;

    @GetMapping
    public ResponseEntity<List<AnuncioRespostaDTO>> listarAnunciosFiltro(@ModelAttribute AnuncioFiltroDTO filtro){
        List<AnuncioRespostaDTO> anuncios = anuncioService.buscarAnunciosFiltro(filtro);
        if(anuncios.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(anuncios);
       
    }
}
