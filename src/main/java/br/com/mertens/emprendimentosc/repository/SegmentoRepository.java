package br.com.mertens.emprendimentosc.repository;

import br.com.mertens.emprendimentosc.entity.Segmento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SegmentoRepository extends JpaRepository<Segmento, Long> {
}
