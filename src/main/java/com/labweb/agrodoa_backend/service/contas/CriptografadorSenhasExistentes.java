package com.labweb.agrodoa_backend.service.contas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.labweb.agrodoa_backend.model.contas.Conta;
import com.labweb.agrodoa_backend.repository.contas.ContaRepository;

import jakarta.annotation.PostConstruct;

//só vai executar uma vez qunado rodar o projeto

// @Component
// public class CriptografadorSenhasExistentes {
//     @Autowired private ContaRepository contaRepository;
//     @Autowired private PasswordEncoder passwordEncoder;

//     @PostConstruct 
//     public void criptografarSenhas() {
//         List<Conta> contas = contaRepository.findAll();
//         for (Conta conta : contas) {
//             String senhaAtual = conta.getSenha();
//             if (!senhaAtual.startsWith("$2a$")) { //evita recriptografar senhas já criptografadas
//                 conta.setSenha(passwordEncoder.encode(senhaAtual));
//                 contaRepository.save(conta);
//             }
//         }
//         System.out.println("Todas as senhas foram criptografadas com sucesso.");
//     }
// }
