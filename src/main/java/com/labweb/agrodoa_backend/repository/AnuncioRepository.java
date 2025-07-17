package com.labweb.agrodoa_backend.repository;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.labweb.agrodoa_backend.model.Anuncio;

public interface AnuncioRepository extends JpaRepository<Anuncio, String>, JpaSpecificationExecutor<Anuncio>{
    boolean existsByIdAnuncio(String idAnuncio);
    ArrayList <Anuncio> findAll();
    Optional<Anuncio> findByIdAnuncio(String idAnuncio);  //usado no acesso a um unico anuncio, para finalizar ou desativar
    //criar query para retornar a quantidade de negociantes

    // @Query(value = "SELECT * FROM anuncio WHERE LOWER(titulo) LIKE CONCAT('%', :pesquisa, '%');", nativeQuery = true)
    // ArrayList<Anuncio> findByTituloContaining(String pesquisa); //barra de pesquisa mas precisa refinar

    // @Query(value = "SELECT * FROM anuncio WHERE data_expiracao = :dataExpiracao;", nativeQuery = true)
    // ArrayList<Anuncio> findByDataExpiracao(LocalDate dataExpiracao);


    /*
     * geral:
     * anuncios disponiveis para negociacao >> vai ter que fazer verificação do status aq logo ou na função que chama o repo
     * anuncios por filtro de preço  >>> acho que vai precisar fazer um join com produto
     * 
     * 
     * fornecedor:
     * anuncios em negociacao  >> esse vai precisar fazer um join com relacao_beneficiario
     */
}
