package br.com.mertens.emprendimentosc.dto.segmento;

import lombok.Builder;

@Builder
public record SegmentoResponseDTO(
        Long idSegmento,
        String descricao
) {
}
