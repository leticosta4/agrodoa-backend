package com.labweb.agrodoa_backend.service;

import java.util.List;

import com.labweb.agrodoa_backend.model.Anuncio;
import com.labweb.agrodoa_backend.model.Denuncia;
import com.labweb.agrodoa_backend.model.pessoas.Administrador;
import com.labweb.agrodoa_backend.model.pessoas.Usuario;

public class NotificacaoService {
    public void processarNovaDenuncia(Denuncia denuncia, Usuario denunciado, List<Administrador> todosOsAdmins) {
        for (Administrador admin : todosOsAdmins) {
            denunciado.adicionarObservador(admin);
        }
        denunciado.adicionarDenuncia(denuncia);
    }

    public void registrarInteresseEmAnuncio(Usuario usuario, Anuncio anuncio) {
        // anuncio.adicionarObservador(anuncio.getAnunciante());
        // anuncio.adicionarObservador(usuario);
    }

    public void checagemDiariaDeValidade(List<Anuncio> anunciosAtivos) {
        for (Anuncio anuncio : anunciosAtivos) {
            anuncio.checarValidade();
        }
    }
}
