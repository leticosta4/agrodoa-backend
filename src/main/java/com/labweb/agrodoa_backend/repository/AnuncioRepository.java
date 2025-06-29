package com.labweb.agrodoa_backend.repository;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.labweb.agrodoa_backend.dto.anuncio.AnuncioDTO;
import com.labweb.agrodoa_backend.model.Anuncio;
import com.labweb.agrodoa_backend.model.enums.TipoAnuncio;

public interface AnuncioRepository extends JpaRepository<Anuncio, String>{
    boolean existsByIdAnuncio(String idAnuncio);
    ArrayList <AnuncioDTO> findAllBy();
    AnuncioDTO findByIdAnuncio(String idAnuncio);  //usado no acesso a um unico anuncio, para finalizar ou desativar

    @Query(value = "SELECT * FROM anuncio WHERE tipo_anuncio = :tipoAnuncio;", nativeQuery = true) //ver aqui como vai associar o item do enum a um caracter 'V' ou 'D'
    ArrayList<AnuncioDTO> findByTipoAnuncio(TipoAnuncio tipoAnuncio); //venda ou doação

    @Query(value = "SELECT * FROM anuncio WHERE LOWER(titulo) LIKE CONCAT('%', :pesquisa, '%');", nativeQuery = true)
    ArrayList<AnuncioDTO> findByTituloContaining(String pesquisa); //barra de pesquisa mas precisa refinar

    @Query(value = "SELECT * FROM anuncio WHERE data_expiracao = :dataExpiracao;", nativeQuery = true)
    ArrayList<AnuncioDTO> findByDataExpiracao(LocalDate dataExpiracao);


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
