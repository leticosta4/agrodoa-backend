package com.labweb.agrodoa_backend.config;

import java.io.IOException;


import jakarta.servlet.http.Cookie; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.labweb.agrodoa_backend.service.contas.ContaDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter{ //filtro que deixa as requisições autenticadas e autorizadas a acesar endpoints protegidos
    //faz tb a validação do token dps de ver se ele tá presente
    @Autowired private JwtUtil jwtUtil;
    @Autowired private ContaDetailsService contaDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

            String email = null; //equivalente ao email?
            String jwt = null;
            
            if (request.getCookies() != null){
                for (Cookie cookie : request.getCookies()){
                    if("jwt".equals(cookie.getName())){
                        jwt = cookie.getValue();
                        break;
                    }
                }
            }
            
            if (jwt == null) { // Se não encontrou o JWT no cookie, passa para o próximo filtro
            filterChain.doFilter(request, response);
            return;
        }
            
         if (SecurityContextHolder.getContext().getAuthentication() != null) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            email = jwtUtil.extraiEmail(jwt); 
        } catch (io.jsonwebtoken.ExpiredJwtException e) {
            System.err.printf("Token expirou amigo: %s/ \n ", e.getMessage());
           
        } catch (io.jsonwebtoken.JwtException e) {
            System.err.printf("Token Inválido: %s/ \n ", e.getMessage());
        } catch(Exception e){
            System.err.printf("Erro processando o token: %s/ \n ", e.getMessage());
        }

             if (email != null) { 
            UserDetails userDetails = contaDetailsService.loadUserByUsername(email);

        
            if (jwtUtil.tokenValido(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = 
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
            filterChain.doFilter(request, response);
    }
}
