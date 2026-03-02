package br.com.mertens.emprendimentosc.dto.empreendimento;

import lombok.Builder;

@Builder
public record EmpreendimentoResponseDTO(
        Long idEmpreendimento,
        String descricao,
        String cnpj,
        String nomeEmpreendimento,
        String descricaoSegmento,
        String cidade,
        String email,
        String nomeEmpreendedor,
        String status
) {
}
