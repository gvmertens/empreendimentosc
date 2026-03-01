package br.com.mertens.emprendimentosc.dto.empreendimento;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record EmpreendimentoRequestDTO(
        @Size(max = 255) String nomeEmpreendimento,
        @Size(max = 500) String descricao,
        @NotBlank @Size(max = 14) String cnpj,
        @NotNull Long idSegmento,
        @NotBlank @Size(max = 80) String cidade,
        @Size(max = 200) String email,
        Long empreendedorId
) {
}
