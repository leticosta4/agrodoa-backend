package com.labweb.agrodoa_backend.model.contas;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "conta")
@Inheritance(strategy = InheritanceType.JOINED) //cria uma tabela para cada classe, com JOIN nas consultas, e faz as tables/classes filhas herdarem o mesmo id
public abstract class Conta implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //para indicar que o banco de dados deve gerar o valor da PK
    @Column(name = "idconta")
    private String idConta;

    @Column(name = "nome")
    private String nome;

    @Column(name = "email")
    private String email;

    @Column(name = "senha")
    private String senha;

    public Conta(String nome, String email, String senha){
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }
    
    public abstract List<String> getRoles();

    @Override
    public String getUsername() {
        return this.email; // O username é o email
    }

    @Override
    public String getPassword() {
        return this.senha; // A senha é a senha
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<String> roles = this.getRoles();
        return roles.stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
    }

    //metodos padrao do UserDetails => melhorar depois talvez
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
