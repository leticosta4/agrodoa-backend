package com.labweb.agrodoa_backend.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import com.labweb.agrodoa_backend.model.Anuncio;
import com.labweb.agrodoa_backend.model.projection.AnuncioProjection;

public interface AnuncioRepository extends JpaRepository<Anuncio, Long>{
    boolean existsByAnuncioId(Long anuncioId);
    ArrayList <AnuncioProjection> findAllBy();
    ArrayList <AnuncioProjection> findAllByAnuncianteId(Long anuncianteId);
    AnuncioProjection findByAnuncioId(Long anuncioId);
    AnuncioProjection findByAnuncioIdAnuncianteId(Long anuncioId, Long anuncianteId);

    /*
     * geral:
     * anuncios disponiveis para negociacao
     * anuncios por filtro de preço
     * anuncios por filtro de tipo: doacao ou venda
     * anuncios por filtro de user (adm)
     * anuncios por barra de pesquisa
     * 
     * get um unico anuncio
     * 
     * fornecedor:
     * anuncios postados
     * anuncios em negociacao
     * 
     * beneficiario:
     * anuncios salvos
     * anuncios negociando
     * 
     * outros sem ser get:
     * finalizar anuncio por id - pagamento/doado
     * desativar anuncio por id - data de vencimento (prod)
     */
}
