package com.labweb.agrodoa_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labweb.agrodoa_backend.dto.produto.ProdutoDTO;
import com.labweb.agrodoa_backend.model.Produto;
import com.labweb.agrodoa_backend.repository.ProdutoRepository;
import com.labweb.agrodoa_backend.repository.contas.ContaRepository;

import jakarta.transaction.Transactional;

@Service
public class ProdutoService {

    @Autowired private ProdutoRepository produtoRepo;
    @Autowired private ContaRepository contaRepo;

    @Transactional
    public Produto criarProduto(ProdutoDTO dto) {
        String novoId = GeradorIdCustom.gerarIdComPrefixo("PRO", produtoRepo, "idProduto");

        Produto produto = dto.transformaParaObjeto();
        produto.setIdProduto(novoId);

        Produto produtoSalvo = produtoRepo.save(produto);

        return produtoSalvo;
    }
}
