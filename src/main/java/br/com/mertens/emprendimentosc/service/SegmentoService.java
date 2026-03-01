package br.com.mertens.emprendimentosc.service;

import br.com.mertens.emprendimentosc.dto.segmento.SegmentoRequestDTO;
import br.com.mertens.emprendimentosc.dto.segmento.SegmentoResponseDTO;
import br.com.mertens.emprendimentosc.entity.Pessoa;
import br.com.mertens.emprendimentosc.entity.Segmento;
import br.com.mertens.emprendimentosc.exception.NotFoundException;
import br.com.mertens.emprendimentosc.repository.SegmentoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SegmentoService {

    private final SegmentoRepository segmentoRepository;

    public SegmentoService(SegmentoRepository segmentoRepository) {
        this.segmentoRepository = segmentoRepository;
    }

    @Transactional
    public SegmentoResponseDTO salvar(SegmentoRequestDTO dto) {
        Segmento segmento = new Segmento();
        segmento.setDescricao(dto.descricao());
        Segmento segmentoSalva = segmentoRepository.save(segmento);
        return convertSegmentoToDTO(segmentoSalva);
    }

    public List<SegmentoResponseDTO> listar() {
        return segmentoRepository.findAll().stream().map(this::convertSegmentoToDTO).toList();
    }

    public SegmentoResponseDTO buscarPorId(Long id) {
        return segmentoRepository.findById(id).map(this::convertSegmentoToDTO).orElse(null);
    }

    public Segmento buscarSegmentoEntityPorId(Long id) {
        return segmentoRepository.findById(id).orElse(null);
    }

    @Transactional
    public SegmentoResponseDTO atualizar(Long id, SegmentoRequestDTO req) {
        Optional<Segmento> optionalSegmento = segmentoRepository.findById(id);
        if (!optionalSegmento.isPresent()) {
            throw new NotFoundException("Segmento não encontrada: id=" + id);
        }
        Segmento segmento = optionalSegmento.get();
        segmento.setDescricao(req.descricao());
        segmento = segmentoRepository.save(segmento);
        return convertSegmentoToDTO(segmento);
    }

    @Transactional
    public void remover(Long id) {
        if (!segmentoRepository.existsById(id)) {
            throw new NotFoundException("Segmento não encontrada: id=" + id);
        }
        segmentoRepository.deleteById(id);
    }

    private SegmentoResponseDTO convertSegmentoToDTO(Segmento segmento) {
        return SegmentoResponseDTO.builder().idSegmento(segmento.getId()).descricao(segmento.getDescricao()).build();
    }
}
