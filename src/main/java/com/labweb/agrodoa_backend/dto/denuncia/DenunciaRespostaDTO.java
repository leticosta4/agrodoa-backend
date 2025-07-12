package com.labweb.agrodoa_backend.dto.denuncia;


import com.labweb.agrodoa_backend.model.Denuncia;
import com.labweb.agrodoa_backend.model.enums.StatusDenuncia;

import lombok.Getter;

@Getter
public class DenunciaRespostaDTO {
    private String idDenuncia;
    private String motivo;
    private String denunciante;
    private String denunciado;
    private StatusDenuncia status;

    public DenunciaRespostaDTO(Denuncia denuncia) {
        this.idDenuncia = denuncia.getIdDenuncia();
        this.motivo = denuncia.getMotivo().getNome();
        this.denunciante = denuncia.getDenunciante().getIdConta();
        this.denunciado = denuncia.getDenunciado().getIdConta();
        this.status = denuncia.getStatus();
    }
}
