package com.labweb.agrodoa_backend.model.projection;

import java.time.LocalDate;

import com.labweb.agrodoa_backend.model.Produto;
import com.labweb.agrodoa_backend.model.enums.StatusAnuncio;
import com.labweb.agrodoa_backend.model.local.Cidade;
import com.labweb.agrodoa_backend.model.pessoas.Fornecedor;

public interface AnuncioProjection {
    String getTitulo();
    LocalDate getDataExpiracao();
    int getEntregaPeloFornecedor();
    StatusAnuncio geStatusAnuncio();
    Cidade getCidade();
    Fornecedor getAnunciante();
    Produto getProduto();

    //ver se precisa do nome do arquivo
}
