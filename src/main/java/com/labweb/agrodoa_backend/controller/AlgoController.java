package com.labweb.agrodoa_backend.controller;

import com.labweb.agrodoa_backend.*;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/algos")
public class AlgoController {

    private final AlgoRepository algoRepository;

    public AlgoController(AlgoRepository algoRepository) {
        this.algoRepository = algoRepository;
    }

    @GetMapping
    public List<Algo> getAllAlgos() {
        return algoRepository.findAll();
    }
}
