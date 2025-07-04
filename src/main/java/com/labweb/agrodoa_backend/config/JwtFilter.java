package com.labweb.agrodoa_backend.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.labweb.agrodoa_backend.service.ContaDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtFilter extends OncePerRequestFilter{ //filtro que deixa as requisições autenticadas e autorizadas a acesar endpoints protegidos
    //faz tb a validação do token dps de ver se ele tá presente

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private ContaDetailsService contaDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

            final String authHeader = request.getHeader("Authorization");
            String email = null; //equivalente ao email?
            String jwt = null;

            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                jwt = authHeader.substring(7);
                email = jwtUtil.extraiEmail(jwt);
            }

            if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = contaDetailsService.loadUserByUsername(email);
            if (jwtUtil.tokenValido(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken token =
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(token);
            }
        }

        filterChain.doFilter(request, response);
    }
}
