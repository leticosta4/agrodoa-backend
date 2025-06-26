package com.labweb.agrodoa_backend.model.projection;

import com.labweb.agrodoa_backend.model.Tipo;
import com.labweb.agrodoa_backend.model.local.Cidade;

public interface UsuarioProjection {
    public String getCpfOuCnpj();
    public String getTelefone();
    public Cidade getCidade();
    public Tipo getTipoUsuario();
    public int getEhVoluntario();
}
