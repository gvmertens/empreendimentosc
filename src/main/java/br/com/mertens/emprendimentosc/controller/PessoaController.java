package br.com.mertens.emprendimentosc.controller;

import br.com.mertens.emprendimentosc.dto.pessoa.PessoaRequestDTO;
import br.com.mertens.emprendimentosc.dto.pessoa.PessoaResponseDTO;
import br.com.mertens.emprendimentosc.service.PessoaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pessoas")
@Tag(name = "Pessoas")
public class PessoaController {

    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Pesquisa pessoa pelo ID")
    public PessoaResponseDTO buscarPorId(@PathVariable Long id) {
        return pessoaService.buscarPorId(id);
    }

    @GetMapping
    @Operation(summary = "Lista todas as pessoas")
    public List<PessoaResponseDTO> listar() {
        return pessoaService.listar();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PessoaResponseDTO salvar(@Valid @RequestBody PessoaRequestDTO dto) {
        return pessoaService.salvar(dto);
    }

    @PutMapping("/{id}")
    public PessoaResponseDTO atualizar(@PathVariable Long id, @Valid @RequestBody PessoaRequestDTO req) {
        return pessoaService.atualizar(id, req);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        pessoaService.remover(id);
    }

}
