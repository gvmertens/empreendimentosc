package br.com.mertens.emprendimentosc.dto.empreendimento;

import lombok.Builder;

@Builder
public record EmpreendimentoResponseDTO(
        Long idEmpreendimento,
        String descricao,
        String cnpj,
        String nomeEmpreendimento,
        Long idSegmento,
        String descricaoSegmento,
        String cidade,
        String email,
        Long empreendedorId,
        String nomeEmpreendedor,
        String status
) {
}
