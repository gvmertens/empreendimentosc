package br.com.mertens.emprendimentosc.repository;

import br.com.mertens.emprendimentosc.entity.Empreendimento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmpreendimentoRepository extends JpaRepository<Empreendimento, Long> {

    Optional<Empreendimento> findByCnpj(String cnpj);

    boolean existsByCnpj(String cnpj);
    
}
