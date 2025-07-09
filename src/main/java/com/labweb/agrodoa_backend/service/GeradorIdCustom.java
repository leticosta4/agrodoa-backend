package com.labweb.agrodoa_backend.service;

import java.lang.reflect.Field;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public class GeradorIdCustom { //pra gerar os IDs com base no nosso padrão de prefixo com 3 letras e 4 digitos
     public static <T> String gerarIdComPrefixo(String prefixo, JpaRepository<T, String> repository, String nomeCampoId) {
        List<T> todasEntidades = repository.findAll();

        int maiorNumero = 0;
        
        for(T entidade: todasEntidades){
            try{
                Field campo = entidade.getClass().getDeclaredField(nomeCampoId);
                campo.setAccessible(true);
                String valorId = (String) campo.get(entidade);

                if (valorId != null && valorId.startsWith(prefixo)) {
                    String numeroStr = valorId.substring(prefixo.length());
                    int numero = Integer.parseInt(numeroStr);
                    if (numero > maiorNumero) {
                        maiorNumero = numero;
                    }
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
                // log: não conseguiu acessar o campo
            }
        }

        int proximoNumero = maiorNumero + 1;

        return String.format("%s%04d", prefixo, proximoNumero);
    }
}
