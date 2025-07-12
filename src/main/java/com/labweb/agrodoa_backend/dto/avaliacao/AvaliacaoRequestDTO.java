package com.labweb.agrodoa_backend.dto.avaliacao;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AvaliacaoRequestDTO {

    @NotNull(message = "A nota não pode ser nula.")
    @Min(value = 1, message = "A nota mínima é 1.")
    @Max(value = 5, message = "A nota máxima é 5.")
    private Integer nota; 

    @NotBlank(message = "O comentário não pode estar em branco.")
    private String comentario;
}
