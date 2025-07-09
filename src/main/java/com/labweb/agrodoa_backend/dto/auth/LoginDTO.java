package com.labweb.agrodoa_backend.dto.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO { //entrada
    @NotBlank(message = "O email é um campo obrigatório!")
    private String email;

    @NotBlank(message = "A senha é um campo obrigatório!")
    private String senha;
}
