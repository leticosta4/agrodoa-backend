package com.labweb.agrodoa_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.labweb.agrodoa_backend.dto.denuncia.DenunciaRespostaDTO;
import com.labweb.agrodoa_backend.service.DenunciaService;

@RestController
@RequestMapping("/denuncias")
public class DenunciaController {
    @Autowired private DenunciaService denunciaService;

    @GetMapping
    public ResponseEntity<List<DenunciaRespostaDTO>> listarDenuncias() {
        List<DenunciaRespostaDTO> listaDenuncias = denunciaService.listarTodas();
        return ResponseEntity.ok(listaDenuncias);
    }

    @PatchMapping("/{idDenuncia}/aprovar")
    public ResponseEntity<Void> aprovarDenuncia(@PathVariable String idDenuncia){
        denunciaService.avaliarDenuncia(idDenuncia, "APROVADA");
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{idDenuncia}/reprovar")
    public ResponseEntity<Void> reprovarDenuncia(@PathVariable String idDenuncia) {
        denunciaService.avaliarDenuncia(idDenuncia, "REPROVADA");
        return ResponseEntity.ok().build(); 
    }
}
