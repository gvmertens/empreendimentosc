package br.com.mertens.emprendimentosc.dto.empreendimento;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EmpreendimentoRequestDTO(
        @NotBlank @Size(max = 120) String nome,
        @Size(max = 500) String descricao,
        @Size(max = 14) String cnpj,
        @NotBlank @Size(max = 80) String segmento,
        @NotBlank @Size(max = 80) String cidade,
        @Size(max = 200) String website,
        Long empreendedorId
) {
}
