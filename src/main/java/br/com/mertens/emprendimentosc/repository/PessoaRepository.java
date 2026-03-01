package br.com.mertens.emprendimentosc.repository;

import br.com.mertens.emprendimentosc.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
