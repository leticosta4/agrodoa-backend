package com.labweb.agrodoa_backend.dto.relacoes;

import com.labweb.agrodoa_backend.dto.contas.usuario.UsuarioResumidoDTO;
import com.labweb.agrodoa_backend.model.relacoes.DoacaoCausa;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DoacaoCausaDTO {
    private double valorArrecadado;
    private UsuarioResumidoDTO user;

    public DoacaoCausaDTO(DoacaoCausa doacaoCausa){
        this.valorArrecadado = doacaoCausa.getValorDoado();
        this.user = new UsuarioResumidoDTO(doacaoCausa.getUsuario());
    }
}
