package br.com.mertens.emprendimentosc.repository;

import br.com.mertens.emprendimentosc.entity.Empreendimento;
import br.com.mertens.emprendimentosc.enums.DominioAtivoInativo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmpreendimentoRepository extends JpaRepository<Empreendimento, Long> {

    List<Empreendimento> findAllByStatus(DominioAtivoInativo status);

    Optional<Empreendimento> findByCnpj(String cnpj);

    boolean existsByCnpj(String cnpj);
    
}
