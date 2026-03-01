package br.com.mertens.emprendimentosc.service;

import br.com.mertens.emprendimentosc.dto.pessoa.PessoaRequestDTO;
import br.com.mertens.emprendimentosc.dto.pessoa.PessoaResponseDTO;
import br.com.mertens.emprendimentosc.entity.Pessoa;
import br.com.mertens.emprendimentosc.exception.NotFoundException;
import br.com.mertens.emprendimentosc.repository.PessoaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    @Transactional
    public PessoaResponseDTO salvar(PessoaRequestDTO dto) {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(dto.nome());
        Pessoa pessoaSalva = pessoaRepository.save(pessoa);
        return convertPessoaToDTO(pessoaSalva);
    }

    public List<PessoaResponseDTO> listar() {
        return pessoaRepository.findAll().stream().map(this::convertPessoaToDTO).toList();
    }

    public PessoaResponseDTO buscarPorId(Long id) {
        return pessoaRepository.findById(id).map(this::convertPessoaToDTO).orElse(null);
    }

    @Transactional
    public PessoaResponseDTO atualizar(Long id, PessoaRequestDTO req) {
        Optional<Pessoa> optionalPessoa = pessoaRepository.findById(id);
        if (!optionalPessoa.isPresent()) {
            throw new NotFoundException("Pessoa não encontrada: id=" + id);
        }
        Pessoa pessoa = optionalPessoa.get();
        pessoa.setNome(req.nome());
        pessoa = pessoaRepository.save(pessoa);
        return convertPessoaToDTO(pessoa);
    }

    @Transactional
    public void remover(Long id) {
        if (!pessoaRepository.existsById(id)) {
            throw new NotFoundException("Pessoa não encontrada: id=" + id);
        }
        pessoaRepository.deleteById(id);
    }

    private PessoaResponseDTO convertPessoaToDTO(Pessoa pessoa) {
        return PessoaResponseDTO.builder().idPessoa(pessoa.getId()).nome(pessoa.getNome()).build();
    }
}
