package com.labweb.agrodoa_backend.service.pessoas;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.labweb.agrodoa_backend.dto.pessoas.usuario.UsuarioRespostaDTO;
import com.labweb.agrodoa_backend.model.pessoas.Usuario;
import com.labweb.agrodoa_backend.repository.pessoas.UsuarioRepository;
import com.labweb.agrodoa_backend.specification.UsuarioSpecification;

@Service
public class UsuarioService {
    //metodos padrão tipo editar e deletar, exibir específico e exibir todos
    //no metodo de criar tentar aplicar a logica do factory

    @Autowired
    private UsuarioRepository userRepo;

    public List<UsuarioRespostaDTO> buscarUsuarioFiltro(String tipo){
        Specification<Usuario> spec = Specification
                                                .where(UsuarioSpecification.filtrarPorTipo(tipo));

        return userRepo.findAll(spec)
                       .stream()
                       .map(UsuarioRespostaDTO::new)
                       .toList();
    }

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

    public UsuarioRespostaDTO acessarUsuarioPorId(String userId){ //ver ainda como fazer a validação pra quando o usuario tiver na conta dele ou vendo outro perfil
        Usuario user = userRepo.findUsuarioByIdConta(userId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "\n\n\nUsuário não encontrado com o ID: " + userId + "!\n\n"));

        return new UsuarioRespostaDTO(user);
    }

    public void apagarPerfilUser(String userId){
        userRepo.findUsuarioByIdConta(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "\n\n\nUsuário não encontrado com o ID: " + userId + "!\n\n"));

        userRepo.removeByIdConta(userId);
    }

    public void editarPerfilUser(String userId){
        //...
    }
}