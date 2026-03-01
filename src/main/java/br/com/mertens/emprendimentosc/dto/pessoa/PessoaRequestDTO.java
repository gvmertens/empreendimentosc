package br.com.mertens.emprendimentosc.dto.pessoa;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PessoaRequestDTO(
        @NotBlank @Size(max = 200) String nome
) {
}
