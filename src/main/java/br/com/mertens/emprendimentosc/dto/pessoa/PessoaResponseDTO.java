package br.com.mertens.emprendimentosc.dto.pessoa;

import lombok.Builder;

@Builder
public record PessoaResponseDTO(
        Long idPessoa,
        String nome
) {
}
