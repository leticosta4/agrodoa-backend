package com.labweb.agrodoa_backend.dto.contas.usuario;

import java.util.List;

import com.labweb.agrodoa_backend.model.Avaliacao;
import com.labweb.agrodoa_backend.model.contas.Usuario;

import lombok.Getter;

@Getter
public class UsuarioLoginDTO {
    private String nome;
    private String email;
    private String telefone;
    private String cpfOuCnpj; // Mapeado do campo 'cpf' solicitado
    private String tipoUsuario;
    private String cidade;
    private String estado;
    private double avaliacaoMedia; // A média calculada das avaliações

    public UsuarioLoginDTO(Usuario user) {
        this.nome = user.getNome();
        this.email = user.getEmail();
        this.telefone = user.getTelefone();
        this.cpfOuCnpj = user.getCpfOuCnpj();

        // Tratamento para evitar NullPointerException se tipo ou cidade forem nulos
        if (user.getTipoUsuario() != null) {
            this.tipoUsuario = user.getTipoUsuario().getNome();
        }

        if (user.getCidade() != null) {
            this.cidade = user.getCidade().getNome();
            if (user.getCidade().getEstado() != null) {
                this.estado = user.getCidade().getEstado().getNome(); // Supondo que Cidade tem getEstado()
            }
        }
        
        this.avaliacaoMedia = calcularMediaAvaliacoes(user.getAvaliacoes());
    }

    private double calcularMediaAvaliacoes(List<Avaliacao> avaliacoes) {
        if (avaliacoes == null || avaliacoes.isEmpty()) { // Retorna 0.0 se a lista de avaliações for nula ou vazia
            return 0.0;
        }

        // Soma todas as notas e calcula a média
        return avaliacoes.stream()
                         .mapToDouble(Avaliacao::getNota) // <-- Assumindo que sua classe Avaliacao tem o método getNota()
                         .average()
                         .orElse(0.0); // Retorna 0.0 se o stream estiver vazio por algum motivo
    }

}
