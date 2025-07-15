package com.labweb.agrodoa_backend.dto;

import com.labweb.agrodoa_backend.dto.contas.usuario.UsuarioResumidoDTO;
import com.labweb.agrodoa_backend.model.Tipo;
import com.labweb.agrodoa_backend.model.contas.Usuario;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequisicaoTipoDTO {
    private UsuarioResumidoDTO user;
    private Tipo tipoAnterior;

    public RequisicaoTipoDTO(Usuario u){
        this.user = new UsuarioResumidoDTO(u);
        this.tipoAnterior = u.getTipoUsuario();
    }
}
