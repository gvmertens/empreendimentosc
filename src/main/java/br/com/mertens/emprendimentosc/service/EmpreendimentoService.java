package br.com.mertens.emprendimentosc.service;

import br.com.mertens.emprendimentosc.repository.EmpreendimentoRepository;
import org.springframework.stereotype.Service;

@Service
public class EmpreendimentoService {

    private final EmpreendimentoRepository empreendimentoRepository;

    public EmpreendimentoService(EmpreendimentoRepository empreendimentoRepository) {
        this.empreendimentoRepository = empreendimentoRepository;
    }
}
