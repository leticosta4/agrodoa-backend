package com.labweb.agrodoa_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.labweb.agrodoa_backend.repository.contas.ContaRepository;

@Service
public class ContaDetailsService implements UserDetailsService{
    //classe chamada automaticamaente pelo spring secutiry quando algm tenta se autenticar
    @Autowired
    private ContaRepository contaRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException { //seria tipo loadByEmail
        return contaRepo.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("\nConta não encontrada com o email:" + email + "!\n\n"));
    }
}