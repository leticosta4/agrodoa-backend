package com.labweb.agrodoa_backend.service.pessoas;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labweb.agrodoa_backend.dto.pessoas.usuario.UsuarioRespostaDTO;
import com.labweb.agrodoa_backend.model.pessoas.Usuario;
import com.labweb.agrodoa_backend.repository.pessoas.UsuarioRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UsuarioService { //acho que não pode ser abstrata (deve dar alguns problemas pq o model ta abstrato)
    //metodos padrão tipo editar e deletar, exibir específico e exibir todos > criar acho que não por causa do factory
    //no metodo de criar tentar aplicar a logica do factory

    @Autowired
    private UsuarioRepository userRepo;

    public ArrayList<UsuarioRespostaDTO> listarTodos(){
        ArrayList<Usuario> users = userRepo.findAll();
        ArrayList<UsuarioRespostaDTO> usersResposta = new ArrayList<>();

        for(Usuario user: users){
            usersResposta.add(new UsuarioRespostaDTO(user));
        }

        return usersResposta;
    }

    public ArrayList<UsuarioRespostaDTO> listarTodosPorTipo(String tipo){
        ArrayList<Usuario> users = userRepo.findAllByTipoUsuario_NomeIgnoreCase(tipo);
        ArrayList<UsuarioRespostaDTO> usersResposta = new ArrayList<>();

        for(Usuario user: users){
            usersResposta.add(new UsuarioRespostaDTO(user));
        }

        return usersResposta;
    }

    public UsuarioRespostaDTO acessarUsuarioPorId(String userId){
        Usuario user = userRepo.findUsuarioByIdConta(userId)
                    .orElseThrow(() -> new EntityNotFoundException("\n\n\nUsuário não encontrado com ID: " + userId + "\n\n"));

        return new UsuarioRespostaDTO(user);
    }
}


/**
 * logica fatory que pensei por cima
 * product > usuario
 * concrete products > fornecedor, usuario e hibrido
 * 
 * fica faltando um creator e talvez concrete creators, fiquei na dúvida onde seria (talvez o controller) mas é o creator que instancia a classe de factory
 * 
 * a logica do template method seria basicamente pq em usuario tem alguns metodos padrão e nas classes filhas outras de acordo com seu comportamento
 */