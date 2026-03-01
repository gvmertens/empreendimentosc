package br.com.mertens.emprendimentosc.dto.segmento;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SegmentoRequestDTO(
        @NotBlank @Size(max = 200) String descricao
) {
}
