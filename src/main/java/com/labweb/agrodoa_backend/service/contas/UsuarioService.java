package com.labweb.agrodoa_backend.service.contas;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.labweb.agrodoa_backend.dto.contas.usuario.UsuarioDTO;
import com.labweb.agrodoa_backend.dto.contas.usuario.UsuarioRespostaDTO;
import com.labweb.agrodoa_backend.model.Tipo;
import com.labweb.agrodoa_backend.model.contas.Usuario;
import com.labweb.agrodoa_backend.model.enums.SituacaoUsuario;
import com.labweb.agrodoa_backend.model.local.Cidade;
import com.labweb.agrodoa_backend.repository.TipoRepository;
import com.labweb.agrodoa_backend.repository.contas.UsuarioRepository;
import com.labweb.agrodoa_backend.repository.local.CidadeRepository;
import com.labweb.agrodoa_backend.service.GeradorIdCustom;
import com.labweb.agrodoa_backend.specification.UsuarioSpecification;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository userRepo;
    @Autowired
    TipoRepository tipoRepo;
    @Autowired
    CidadeRepository cidadeRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<UsuarioRespostaDTO> buscarUsuarioFiltro(String tipo, String situacao){
        Specification<Usuario> spec = Specification
                                                .where(UsuarioSpecification.filtrarPorTipo(tipo))
                                                .and(UsuarioSpecification.filtrarPorSituacao(situacao));

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

    public UsuarioRespostaDTO acessarUsuarioPorId(String idUser){ //ver ainda como fazer a validação pra quando o usuario tiver na conta dele ou vendo outro perfil
        Usuario user = userRepo.findUsuarioByIdConta(idUser)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "\n\n\nUsuário não encontrado com o ID: " + idUser + "!\n\n"));

        return new UsuarioRespostaDTO(user);
    }

    public boolean alterarSituacao(String idUser, SituacaoUsuario novaSituacao){
        Optional<Usuario> user = userRepo.findUsuarioByIdConta(idUser);
        if (user.isEmpty()) { return false; }
    
        Usuario usuario = user.get();
        usuario.setSituacaoUser(novaSituacao);

        if(novaSituacao == SituacaoUsuario.BANIDO){ /*enviar email para o usuario em questão sobre banimento por 3 alertas e motivos descritos */}

        userRepo.save(usuario);
        return true;
    }
    

    public void editarPerfilUser(String idUser){
        //...
    }

    public Usuario cadastrarUsuario(UsuarioDTO userDTO){
        if (!userDTO.getTipoUsuario().equalsIgnoreCase("fornecedor") && !userDTO.getTipoUsuario().equalsIgnoreCase("beneficiario")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Os tipos permitido para cadastro são apenas 'fornecedor' e 'beneficiario'.");
        }

        Tipo tipoUsuario = tipoRepo.findByNome(userDTO.getTipoUsuario());
        Cidade cidade = cidadeRepo.findByIdCidade(userDTO.getIdCidade());

        if (tipoUsuario == null || cidade == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tipo de usuário ou cidade inválida.");
        }

        Usuario tempUser = new Usuario();
        tempUser.setIdConta(GeradorIdCustom.gerarIdComPrefixo("CON", userRepo, "idConta"));
        tempUser.setNome(userDTO.getNome());
        tempUser.setEmail(userDTO.getEmail());
        tempUser.setSenha(passwordEncoder.encode(userDTO.getSenha())); //criptografa a senha aqui e ja salva o hash no banco
        tempUser.setCpfOuCnpj(userDTO.getCpfOuCnpj());
        tempUser.setTelefone(userDTO.getTelefone());
        tempUser.setNomeArquivoFoto(userDTO.getNomeArquivoFoto());
        tempUser.setCidade(cidade);
        tempUser.setTipoUsuario(tipoUsuario);

        return userRepo.save(tempUser);
    }
}