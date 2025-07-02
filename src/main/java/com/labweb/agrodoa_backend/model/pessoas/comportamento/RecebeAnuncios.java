package com.labweb.agrodoa_backend.model.pessoas.comportamento;

import java.util.List;

import com.labweb.agrodoa_backend.model.pessoas.Usuario;
import com.labweb.agrodoa_backend.model.relacoes.RelacaoBeneficiario;

public interface RecebeAnuncios {
    List<RelacaoBeneficiario> getRelacoesAnuncios();
    
    default String getIdConta(){
        return ((Usuario) this).getIdConta();
    }
}
