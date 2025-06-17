package com.labweb.agrodoa_backend.repository;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.labweb.agrodoa_backend.model.Anuncio;
import com.labweb.agrodoa_backend.model.enums.TipoAnuncio;
import com.labweb.agrodoa_backend.model.projection.AnuncioProjection;

public interface AnuncioRepository extends JpaRepository<Anuncio, Long>{
    boolean existsByAnuncioId(Long anuncioId);
    ArrayList <AnuncioProjection> findAllBy();
    ArrayList <AnuncioProjection> findAllByAnuncianteId(Long anuncianteId);   //todos os anúncios de um anunciante
    AnuncioProjection findByAnuncioId(Long anuncioId);  //usado no acesso a um unico anuncio, para finalizar ou desativar

    @Query(value = "SELECT * FROM anuncio WHERE tipo_anuncio = :tipoAnuncio;", nativeQuery = true) //ver aqui como vai associar o item do enum a um caracter 'V' ou 'D'
    ArrayList<AnuncioProjection> findByTipoAnuncio(TipoAnuncio tipoAnuncio); //venda ou doação

    @Query(value = "SELECT * FROM anuncio WHERE LOWER(titulo) LIKE CONCAT('%', :pesquisa, '%');", nativeQuery = true)
    ArrayList<AnuncioProjection> findByTituloContaining(String pesquisa); //barra de pesquisa mas precisa refinar

    @Query(value = "SELECT * FROM anuncio WHERE data_expiracao = :dataExpiracao;", nativeQuery = true)
    ArrayList<AnuncioProjection> findByDataExpiracao(LocalDate dataExpiracao);


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
