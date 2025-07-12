package com.labweb.agrodoa_backend.controller.contas;

import java.net.URI;
import java.time.Duration;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.labweb.agrodoa_backend.config.JwtUtil;
import com.labweb.agrodoa_backend.dto.anuncio.AnuncioRespostaDTO;
import com.labweb.agrodoa_backend.dto.avaliacao.AvaliacaoRequestDTO;
import com.labweb.agrodoa_backend.dto.contas.usuario.UsuarioDTO;
import com.labweb.agrodoa_backend.dto.contas.usuario.UsuarioLoginDTO;
import com.labweb.agrodoa_backend.dto.contas.usuario.UsuarioRespostaDTO;
import com.labweb.agrodoa_backend.dto.denuncia.DenunciaRequestDTO;
import com.labweb.agrodoa_backend.service.AvaliacaoService;
import com.labweb.agrodoa_backend.service.DenunciaService;
import com.labweb.agrodoa_backend.model.contas.Usuario;
import com.labweb.agrodoa_backend.model.enums.SituacaoUsuario;
import com.labweb.agrodoa_backend.model.enums.TipoRelacaoBenef;
import com.labweb.agrodoa_backend.service.RelacaoBeneficiarioService;
import com.labweb.agrodoa_backend.service.contas.ContaDetailsService;
import com.labweb.agrodoa_backend.service.contas.UsuarioService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService userService;

    @Autowired
    private ContaDetailsService contaService;

    @Autowired
    private DenunciaService denunciaService;

    @Autowired
    private RelacaoBeneficiarioService relacaoBenefService;

    @Autowired
    private AvaliacaoService avalaicaoService;

    @Autowired
    private JwtUtil jwt;

    @GetMapping
    public ResponseEntity<List<UsuarioRespostaDTO>> listarUsuariosPorTipo(
        @RequestParam(required = false) String tipo,
        @RequestParam(required = false) String situacao){

        List<UsuarioRespostaDTO> usuarios = userService.buscarUsuarioFiltro(tipo, situacao);
        if(usuarios.isEmpty()){
             return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuarios);
    }

 
    @GetMapping({"/ver_perfil/{idUser}"})
    public ResponseEntity<UsuarioRespostaDTO> exibirUserPorId(@PathVariable String idUser) {

        UsuarioRespostaDTO usuario = userService.acessarUsuarioPorId(idUser);
        return ResponseEntity.ok(usuario);
    }

    @PostMapping("/ver_perfil/{idUser}/denunciar")
    public ResponseEntity<?> denunciarUsuario(@PathVariable String idUser, @RequestBody DenunciaRequestDTO denunciaDTO, @AuthenticationPrincipal UserDetails userDetails) {

        String idDenunciante = contaService.findIdByEmail(userDetails.getUsername());

        denunciaService.criarDenuncia(idDenunciante, idUser, denunciaDTO.getNomeMotivo());

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/ver_perfil/{idUser}/avaliar")
    public ResponseEntity<?> avaliarUsuario(@PathVariable String idUser, @RequestBody AvaliacaoRequestDTO avaliacaoDTO, @AuthenticationPrincipal UserDetails userDetails) {

        String idAvaliador = contaService.findIdByEmail(userDetails.getUsername());

        avalaicaoService.criarAvaliacao(idAvaliador, idUser, avaliacaoDTO);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/meu_perfil") 
    public ResponseEntity<UsuarioRespostaDTO> exibirMeuPerfil(@AuthenticationPrincipal UserDetails userDetails) {

        String idUsuario = contaService.findIdByEmail(userDetails.getUsername());

        UsuarioRespostaDTO usuario = userService.acessarUsuarioPorId(idUsuario);

        return ResponseEntity.ok(usuario);
    }


    @PatchMapping({"/desativar_conta"})
    public ResponseEntity<Void> desativarContaUser(@AuthenticationPrincipal UserDetails userDetails){
        String idUser = contaService.findIdByEmail(userDetails.getUsername());

        boolean inativo = userService.alterarSituacao(idUser, SituacaoUsuario.INATIVO);
        
        if(inativo){
            return ResponseEntity.noContent().build(); //204
        }
        return ResponseEntity.notFound().build(); //404 - acho que não precisa
    } 

    @PostMapping({"/cadastrar_usuario"})
    public ResponseEntity<UsuarioLoginDTO> cadastrar(@RequestBody @Valid UsuarioDTO userDTO) {
        Usuario userSalvo = userService.cadastrarUsuario(userDTO);
        String token = jwt.geraToken(userSalvo.getEmail());

        ResponseCookie cookie = ResponseCookie.from("jwt", token)
        .httpOnly(true)
        .secure(false) //mudar p true dps
        .path("/")
        .maxAge(Duration.ofHours(2))
        .sameSite("Lax")
        .build();
        
        UsuarioLoginDTO usuarioDados = new UsuarioLoginDTO(userSalvo);

        URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{idUser}")
            .buildAndExpand(userSalvo.getIdConta())
            .toUri();

        return ResponseEntity.created(location).header(HttpHeaders.SET_COOKIE,cookie.toString()).body(usuarioDados);
    }

    @PutMapping("/meu_perfil/editar")
    public ResponseEntity<UsuarioRespostaDTO> editarUsuario(@RequestBody @Valid UsuarioDTO userDTO, @AuthenticationPrincipal UserDetails userDetails) {
        String idUser = contaService.findIdByEmail(userDetails.getUsername());
        UsuarioRespostaDTO userAtualizado = userService.editarPerfilUser(idUser, userDTO);
        
        return ResponseEntity.ok(userAtualizado);
    }
    

    @GetMapping("/meu_perfil/meus_salvos")
    public ResponseEntity<List<AnuncioRespostaDTO>> meusSalvos(@AuthenticationPrincipal UserDetails userDetails) {
        String idBeneficiario = contaService.findIdByEmail(userDetails.getUsername());
        List<AnuncioRespostaDTO> retorno = relacaoBenefService.exibirGrupoAnuncios(idBeneficiario, TipoRelacaoBenef.SALVOU);
        
        return ResponseEntity.ok(retorno);
    }

    @GetMapping("/meu_perfil/minhas_negociacoes")
    public ResponseEntity<List<AnuncioRespostaDTO>> minhasNegociacoes(@AuthenticationPrincipal UserDetails userDetails) {
        String idBeneficiario = contaService.findIdByEmail(userDetails.getUsername());
        List<AnuncioRespostaDTO> retorno =  relacaoBenefService.exibirGrupoAnuncios(idBeneficiario, TipoRelacaoBenef.NEGOCIANDO);
        
        return ResponseEntity.ok(retorno);
    }
    //reativar_conta >> se der tempo
    //bloquear conta usuario >> para os ADMs
    //notificar usuario?
}
