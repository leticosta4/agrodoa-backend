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
import com.labweb.agrodoa_backend.dto.contas.usuario.UsuarioLoginDTO;
import com.labweb.agrodoa_backend.dto.contas.usuario.UsuarioRespostaDTO;
import com.labweb.agrodoa_backend.model.RequisicaoTrocaTipo;
import com.labweb.agrodoa_backend.model.Tipo;
import com.labweb.agrodoa_backend.model.contas.Conta;
import com.labweb.agrodoa_backend.model.contas.Usuario;
import com.labweb.agrodoa_backend.model.enums.SituacaoUsuario;
import com.labweb.agrodoa_backend.model.local.Cidade;
import com.labweb.agrodoa_backend.repository.RequisicaoTrocaTipoRepository;
import com.labweb.agrodoa_backend.repository.TipoRepository;
import com.labweb.agrodoa_backend.repository.contas.ContaRepository;
import com.labweb.agrodoa_backend.repository.contas.UsuarioRepository;
import com.labweb.agrodoa_backend.repository.local.CidadeRepository;
import com.labweb.agrodoa_backend.service.GeradorIdCustom;
import com.labweb.agrodoa_backend.specification.UsuarioSpecification;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository userRepo;
    @Autowired
    TipoRepository tipoRepo;
    @Autowired
    CidadeRepository cidadeRepo;
    @Autowired
    private RequisicaoTrocaTipoRepository requisicaoTipoRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    ContaRepository contaRepo;

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

    public void trocaTipoUsuario(String idUser) { 
        Usuario user = userRepo.findUsuarioByIdConta(idUser)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado com o ID: " + idUser));

        Tipo tipoHibrido = tipoRepo.findByNome("hibrido");
                

        if (user.getTipoUsuario().equals(tipoHibrido)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O usuário já possui o tipo de perfil 'hibrido'.");
        }
        
        RequisicaoTrocaTipo novaRequisicao = new RequisicaoTrocaTipo(user);

        String novoId = GeradorIdCustom.gerarIdComPrefixo("REQ", requisicaoTipoRepo, "idRequisicaoTrocaTipo");
        novaRequisicao.setIdRequisicaoTrocaTipo(novoId);

        requisicaoTipoRepo.save(novaRequisicao);

        user.setTipoUsuario(tipoHibrido);
        userRepo.save(user);
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
        tempUser.setSituacaoUser(SituacaoUsuario.ATIVO);

        return userRepo.save(tempUser);
    }


    public UsuarioRespostaDTO editarPerfilUser(String idUser, UsuarioDTO userRecebido){
        if (!userRecebido.getTipoUsuario().equalsIgnoreCase("fornecedor") && !userRecebido.getTipoUsuario().equalsIgnoreCase("beneficiario")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Os tipos permitido para cadastro são apenas 'fornecedor' e 'beneficiario'.");
        }

        Tipo tipoUsuario = tipoRepo.findByNome(userRecebido.getTipoUsuario());
        Cidade cidade = cidadeRepo.findByIdCidade(userRecebido.getIdCidade());

        if (tipoUsuario == null || cidade == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tipo de usuário ou cidade inválida.");
        }


        Usuario tempUser = userRepo.findUsuarioByIdConta(idUser)
        .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com o ID: " + idUser));


        tempUser.setNome(userRecebido.getNome());
        tempUser.setEmail(userRecebido.getEmail());
        tempUser.setSenha(passwordEncoder.encode(userRecebido.getSenha())); //criptografa a senha aqui e ja salva o hash no banco
        tempUser.setCpfOuCnpj(userRecebido.getCpfOuCnpj());
        tempUser.setTelefone(userRecebido.getTelefone());
        tempUser.setNomeArquivoFoto(userRecebido.getNomeArquivoFoto());
        tempUser.setCidade(cidade);
        tempUser.setTipoUsuario(tipoUsuario);
        tempUser.setSituacaoUser(SituacaoUsuario.ATIVO);

        userRepo.save(tempUser);
        return new UsuarioRespostaDTO(tempUser);
    }

    public UsuarioLoginDTO logarComToken(String emailUsuario){
        
    Optional<Conta> contaOptional = contaRepo.findByEmail(emailUsuario);

        if (!contaOptional.isPresent()) {
            System.err.println("Erro no serviço: Conta com email " + emailUsuario + " não encontrada na tabela base de contas.");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Conta não encontrada.");
        }

        Conta conta = contaOptional.get();

        if (!(conta instanceof Usuario)) {
            System.err.println("Erro no serviço: Conta autenticada com email " + emailUsuario + " não é do tipo Usuário.");
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Acesso negado: Tipo de conta inválido para este perfil.");
        }

        Optional<Usuario> usuarioCompletoOptional = userRepo.findById(conta.getIdConta());

        if (!usuarioCompletoOptional.isPresent()) {
            System.err.println("Erro no serviço: Detalhes completos do Usuário com ID " + conta.getIdConta() + " não encontrados na tabela de usuários.");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Detalhes do usuário não encontrados.");
        }
        Usuario usuarioCompleto = usuarioCompletoOptional.get();

        return new UsuarioLoginDTO(usuarioCompleto);
    }
}