package com.labweb.agrodoa_backend.service.strategyDenuncia;

import org.springframework.stereotype.Component;
import com.labweb.agrodoa_backend.model.Denuncia;


@Component("ARQUIVAR_STRATEGY")
public class ArquivarDenunciaStrategy implements AcaoDenunciaStrategy {

    @Override
    public void processar(Denuncia denuncia) {

        // denuncia.setStatus("ARQUIVADA");
    }
    
}
