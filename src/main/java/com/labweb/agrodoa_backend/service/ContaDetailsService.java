package com.labweb.agrodoa_backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.labweb.agrodoa_backend.model.pessoas.Conta;
import com.labweb.agrodoa_backend.repository.pessoas.ContaRepository;

@Service
public class ContaDetailsService implements UserDetailsService{
    //classe chamada automaticamaente pelo spring secutiry quando algm tenta se autenticar
    @Autowired
    private ContaRepository contaRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException { //seria tipo loadByEmail
        Conta conta = contaRepo.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("\nConta não encontrada no sistema!\n\n"));
    
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_USER"));

        return new org.springframework.security.core.userdetails.User(
                conta.getEmail(),
                conta.getSenha(),
                authorities
        );
    }
}

//vou precisar fazer aqui a diferenciação de acordo com os tipo de conta por causa dos roler