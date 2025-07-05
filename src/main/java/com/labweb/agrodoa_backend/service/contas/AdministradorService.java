package com.labweb.agrodoa_backend.service.contas;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labweb.agrodoa_backend.dto.contas.administrador.AdministradorRespostaDTO;
import com.labweb.agrodoa_backend.model.contas.Administrador;
import com.labweb.agrodoa_backend.repository.contas.AdministradorRepository;

@Service
public class AdministradorService {
    @Autowired
    private AdministradorRepository admRepo;

    public ArrayList<AdministradorRespostaDTO> listarTodos() { //o certo é usar qual? 
        ArrayList<Administrador> admins =  admRepo.findAll();
        ArrayList<AdministradorRespostaDTO> adminsResposta = new ArrayList<>();

        for(Administrador adm: admins){
            adminsResposta.add(new AdministradorRespostaDTO(adm));
        }

        return adminsResposta;
    }

}  
