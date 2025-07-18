package com.labweb.agrodoa_backend.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.labweb.agrodoa_backend.dto.anuncio.AnuncioDTO;
import com.labweb.agrodoa_backend.dto.anuncio.AnuncioFiltroDTO;
import com.labweb.agrodoa_backend.dto.anuncio.AnuncioFiltroUsuarioDTO;
import com.labweb.agrodoa_backend.dto.anuncio.AnuncioRespostaDTO;
import com.labweb.agrodoa_backend.model.Anuncio;
import com.labweb.agrodoa_backend.model.Produto;
import com.labweb.agrodoa_backend.model.contas.Usuario;
import com.labweb.agrodoa_backend.model.enums.StatusAnuncio;
import com.labweb.agrodoa_backend.model.local.Cidade;
import com.labweb.agrodoa_backend.repository.AnuncioRepository;
import com.labweb.agrodoa_backend.repository.ProdutoRepository;
import com.labweb.agrodoa_backend.repository.contas.UsuarioRepository;
import com.labweb.agrodoa_backend.repository.local.CidadeRepository;
import com.labweb.agrodoa_backend.service.auxiliares.CloudinaryService;
import com.labweb.agrodoa_backend.service.auxiliares.GeradorIdCustom;
import com.labweb.agrodoa_backend.specification.AnuncioSpecification;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class AnuncioService {
    @Autowired private AnuncioRepository anuncioRepo;
    @Autowired private CidadeRepository cidadeRepo;
    @Autowired private ProdutoRepository produtoRepo;
    @Autowired private UsuarioRepository userRepo; // Para buscar o anunciante
    @Autowired private CloudinaryService cloudnary;

    private Specification<Anuncio> criarSpecification(AnuncioFiltroDTO dto) {
        return Specification
            .where(AnuncioSpecification.filtrarPorNome(dto.getNome()))
            .and(AnuncioSpecification.filtrarPorCidade(dto.getCidade()))
            .and(AnuncioSpecification.filtrarPorDataExpiracao(dto.getDataExpiracao()))
            .and(AnuncioSpecification.filtrarPorStatus(dto.getStatusEnum()));
    }
    

    public List<AnuncioRespostaDTO> buscarAnunciosFiltro(AnuncioFiltroDTO dto){
        verificarDatasAnuncios();
        Specification<Anuncio> spec = criarSpecification(dto);
                                                
        return anuncioRepo.findAll(spec)
                          .stream()
                          .map(AnuncioRespostaDTO::new)
                          .toList();
    }   
    
    
    public Optional<AnuncioRespostaDTO> buscarAnuncioPorId(String anuncioId){
        verificarDatasAnuncios();
        return anuncioRepo.findById(anuncioId).map(AnuncioRespostaDTO::new);
    }

    public List<AnuncioRespostaDTO> buscarAnunciosFiltroUsuario(AnuncioFiltroUsuarioDTO dto){
        Specification<Anuncio> spec = 
                                criarSpecification(dto)
                                .and(AnuncioSpecification.filtrarPorAnuncianteId(dto.getIdAnunciante()));

        return anuncioRepo.findAll(spec)
                          .stream()
                          .map(AnuncioRespostaDTO::new)
                          .toList();
    }

    @Transactional
    public Anuncio criarAnuncio(AnuncioDTO dto, String idAnunciante, MultipartFile imagem) {
        dto.setNomeArquivoFoto(cloudnary.uploadImagem(imagem));
        Cidade cidade = cidadeRepo.findById(dto.getCidadeId())
                .orElseThrow(() -> new EntityNotFoundException("Cidade não encontrada com o ID: " + dto.getCidadeId()));
        Produto produto = produtoRepo.findById(dto.getProdutoId())
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado com o ID: " + dto.getProdutoId()));
        Usuario anunciante = userRepo.findById(idAnunciante)
                .orElseThrow(() -> new EntityNotFoundException("Usuário anunciante não encontrado."));

        String novoId = GeradorIdCustom.gerarIdComPrefixo("ANU", anuncioRepo, "idAnuncio");
        
        Anuncio novoAnuncio = dto.transformaParaObjeto(cidade, anunciante, produto);
        novoAnuncio.setIdAnuncio(novoId);

        return anuncioRepo.saveAndFlush(novoAnuncio);
    }

    @Transactional
    public AnuncioRespostaDTO editarAnuncio(String idAnuncio, AnuncioDTO dto) {
        Anuncio anuncio = anuncioRepo.findById(idAnuncio)
                .orElseThrow(() -> new EntityNotFoundException("Anúncio não encontrado com o ID: " + idAnuncio));

       
        Cidade cidade = cidadeRepo.findById(dto.getCidadeId())
                .orElseThrow(() -> new EntityNotFoundException("Cidade não encontrada com o ID: " + dto.getCidadeId()));
        Produto produto = produtoRepo.findById(dto.getProdutoId())
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado com o ID: " + dto.getProdutoId()));
        
        anuncio.setTitulo(dto.getTitulo());
        anuncio.setDescricao(dto.getDescricao());
        anuncio.setNomeArquivoFoto(dto.getNomeArquivoFoto());
        anuncio.setDataExpiracao(dto.getDataExpiracao());
        anuncio.setEntregaPeloFornecedor(dto.getEntregaPeloFornecedor());
        anuncio.setCidade(cidade);
        anuncio.setProduto(produto);

        anuncioRepo.save(anuncio);

        return new AnuncioRespostaDTO(anuncio);
    }

    @Transactional
    public void cancelarAnuncio(String idAnuncio) {
        Anuncio anuncio = anuncioRepo.findById(idAnuncio)
            .orElseThrow(() -> new EntityNotFoundException("Anúncio não encontrado com o ID: " + idAnuncio));
        
        anuncio.setStatus(StatusAnuncio.CANCELADO); //talvez colocar um novo status de cancelado
        anuncioRepo.save(anuncio);
    }

    public void verificarDatasAnuncios(){
        for(Anuncio a: anuncioRepo.findAll()){
            if(a.getDataExpiracao().isAfter(LocalDate.now())){
                a.setStatus(StatusAnuncio.EXPIRADO);
                anuncioRepo.save(a);
            }
        }
    }
}
