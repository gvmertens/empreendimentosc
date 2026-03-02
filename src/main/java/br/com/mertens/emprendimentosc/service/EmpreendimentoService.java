package br.com.mertens.emprendimentosc.service;

import br.com.mertens.emprendimentosc.dto.empreendimento.EmpreendimentoRequestDTO;
import br.com.mertens.emprendimentosc.dto.empreendimento.EmpreendimentoResponseDTO;
import br.com.mertens.emprendimentosc.dto.pessoa.PessoaRequestDTO;
import br.com.mertens.emprendimentosc.dto.pessoa.PessoaResponseDTO;
import br.com.mertens.emprendimentosc.entity.Empreendimento;
import br.com.mertens.emprendimentosc.entity.Pessoa;
import br.com.mertens.emprendimentosc.entity.Segmento;
import br.com.mertens.emprendimentosc.enums.DominioAtivoInativo;
import br.com.mertens.emprendimentosc.exception.NotFoundException;
import br.com.mertens.emprendimentosc.repository.EmpreendimentoRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmpreendimentoService {

    private final EmpreendimentoRepository empreendimentoRepository;
    private final PessoaService pessoaService;
    private final SegmentoService segmentoService;

    public EmpreendimentoService(EmpreendimentoRepository empreendimentoRepository, PessoaService pessoaService, SegmentoService segmentoService) {
        this.empreendimentoRepository = empreendimentoRepository;
        this.pessoaService = pessoaService;
        this.segmentoService = segmentoService;
    }

    @Transactional
    public EmpreendimentoResponseDTO salvar(EmpreendimentoRequestDTO dto) {
        validarCnpjCriacao(dto.cnpj());
        Pessoa empreendedor = resolverEmpreendedor(dto);
        Segmento segmento = resolverSegmento(dto);

        Empreendimento e = new Empreendimento();
        populaDadosEmpreendimento(dto, e, empreendedor, segmento);

        e = empreendimentoRepository.save(e);
        return convertEmpreendimentoToDTO(e);
    }

    public List<EmpreendimentoResponseDTO> listar(DominioAtivoInativo status) {
        List<Empreendimento> lista = (status == null)
                ? empreendimentoRepository.findAll()
                : empreendimentoRepository.findAllByStatus(status);
        return lista.stream().map(this::convertEmpreendimentoToDTO).toList();
    }

    public EmpreendimentoResponseDTO buscarPorId(Long id) {
        return empreendimentoRepository.findById(id).map(this::convertEmpreendimentoToDTO).orElse(null);
    }

    @Transactional
    public EmpreendimentoResponseDTO atualizar(Long id, EmpreendimentoRequestDTO req) {
        Optional<Empreendimento> optionalEmpreendimento = empreendimentoRepository.findById(id);
        if (!optionalEmpreendimento.isPresent()) {
            throw new NotFoundException("Empreendimento não encontrado: id=" + id);
        }
        Empreendimento empreendimento = optionalEmpreendimento.get();
        empreendimento.setNomeEmpreendimento(StringUtils.isNotBlank(req.nomeEmpreendimento()) ? req.nomeEmpreendimento() : empreendimento.getNomeEmpreendimento());
        empreendimento.setDescricao(StringUtils.isNotBlank(req.descricao()) ? req.descricao() : empreendimento.getDescricao());
        empreendimento.setCnpj(StringUtils.isNotBlank(req.cnpj()) ? req.cnpj() : empreendimento.getCnpj());
        empreendimento.setCidade(StringUtils.isNotBlank(req.cidade()) ? req.cidade() : empreendimento.getCidade());
        empreendimento.setEmail(StringUtils.isNotBlank(req.email()) ? req.email() : empreendimento.getEmail());

        if (req.idSegmento() != null) {
            Segmento segmento = this.resolverSegmento(req);
            empreendimento.setSegmento(segmento);
        }

        if (req.empreendedorId() != null) {
            Pessoa empreendedor = this.resolverEmpreendedor(req);
            empreendimento.setEmpreendedor(empreendedor);
        }

        empreendimento = empreendimentoRepository.save(empreendimento);
        return convertEmpreendimentoToDTO(empreendimento);
    }

    @Transactional
    public void remover(Long id) {
        if (!empreendimentoRepository.existsById(id)) {
            throw new NotFoundException("Empreendimento não encontrado: id=" + id);
        }
        empreendimentoRepository.deleteById(id);
    }

    @Transactional
    public EmpreendimentoResponseDTO ativar(Long id) {
        Empreendimento e = empreendimentoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Empreendimento não encontrado: id=" + id));

        e.setStatus(DominioAtivoInativo.ATIVO);
        e = empreendimentoRepository.save(e);

        return convertEmpreendimentoToDTO(e);
    }

    @Transactional
    public EmpreendimentoResponseDTO desativar(Long id) {
        Empreendimento e = empreendimentoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Empreendimento não encontrado: id=" + id));

        e.setStatus(DominioAtivoInativo.INATIVO);
        e = empreendimentoRepository.save(e);

        return convertEmpreendimentoToDTO(e);
    }

    private Pessoa resolverEmpreendedor(EmpreendimentoRequestDTO dto) {
        Pessoa pessoaEntityPorId = pessoaService.buscarPessoaEntityPorId(dto.empreendedorId());
        if (pessoaEntityPorId == null) {
            throw new NotFoundException("Pessoa não encontrada: id=" + dto.empreendedorId());
        }

        return pessoaEntityPorId;
    }

    private Segmento resolverSegmento(EmpreendimentoRequestDTO dto) {
        Segmento segmentoEntityPorId = segmentoService.buscarSegmentoEntityPorId(dto.idSegmento());
        if (segmentoEntityPorId == null) {
            throw new NotFoundException("Segmento não encontrado: id=" + dto.idSegmento());
        }

        return segmentoEntityPorId;
    }

    private void validarCnpjCriacao(String cnpj) {
        String normalized = normalizarCnpj(cnpj);
        if (normalized != null && empreendimentoRepository.existsByCnpj(normalized)) {
            throw new IllegalArgumentException("CNPJ já cadastrado.");
        }
    }

    private String normalizarCnpj(String cnpj) {
        if (cnpj == null) return null;
        String trimmed = cnpj.trim();
        return trimmed.isBlank() ? null : trimmed.replace(".", "").replace("/", "").replace("-", "");
    }

    private void populaDadosEmpreendimento(EmpreendimentoRequestDTO req, Empreendimento emp, Pessoa empreendedor, Segmento segmento) {
        emp.setDescricao(req.descricao());
        emp.setCnpj(this.normalizarCnpj(req.cnpj()));
        emp.setNomeEmpreendimento(req.nomeEmpreendimento());
        emp.setCnpj(normalizarCnpj(req.cnpj()));
        emp.setCidade(req.cidade());
        emp.setEmail(req.email());
        emp.setEmpreendedor(empreendedor);
        emp.setSegmento(segmento);
        emp.setStatus(DominioAtivoInativo.ATIVO);
    }

    private EmpreendimentoResponseDTO convertEmpreendimentoToDTO(Empreendimento empreendimento) {
        return EmpreendimentoResponseDTO.builder()
                .idEmpreendimento(empreendimento.getIdEmpreendimento())
                .descricao(empreendimento.getDescricao())
                .cnpj(empreendimento.getCnpj())
                .nomeEmpreendedor(empreendimento.getEmpreendedor().getNome())
                .nomeEmpreendimento(empreendimento.getNomeEmpreendimento())
                .cidade(empreendimento.getCidade())
                .email(empreendimento.getEmail())
                .descricaoSegmento(empreendimento.getSegmento().getDescricao())
                .status(empreendimento.getStatus().getDesc())
                .build();
    }

}
