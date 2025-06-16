package com.labweb.agrodoa_backend.controller;

import org.springframework.web.bind.annotation.*;

//import jakarta.transaction.Transactional;

@RestController
@CrossOrigin(origins = "*")
public class Hello {
    @GetMapping({"/hello"})  
    public int hello() {
        return  200;
    }
}