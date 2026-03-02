package br.com.mertens.emprendimentosc.controller;

import br.com.mertens.emprendimentosc.dto.empreendimento.EmpreendimentoRequestDTO;
import br.com.mertens.emprendimentosc.dto.empreendimento.EmpreendimentoResponseDTO;
import br.com.mertens.emprendimentosc.enums.DominioAtivoInativo;
import br.com.mertens.emprendimentosc.service.EmpreendimentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empreendimentos")
@Tag(name = "Empreendimentos")
public class EmpreendimentoController {

    private final EmpreendimentoService empreendimentoService;

    public EmpreendimentoController(EmpreendimentoService empreendimentoService) {
        this.empreendimentoService = empreendimentoService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Pesquisa empreendimento pelo ID")
    public EmpreendimentoResponseDTO buscarPorId(@PathVariable Long id) {
        return empreendimentoService.buscarPorId(id);
    }

    @GetMapping
    @Operation(summary = "Lista todas os empreendimentos")
    public List<EmpreendimentoResponseDTO> listar(@RequestParam(name = "status", required = false) DominioAtivoInativo status) {
        return empreendimentoService.listar(status);
    }

    @PostMapping
    @Operation(summary = "Cria um novo seguimento")
    @ResponseStatus(HttpStatus.CREATED)
    public EmpreendimentoResponseDTO salvar(@Valid @RequestBody EmpreendimentoRequestDTO dto) {
        return empreendimentoService.salvar(dto);
    }

    @Operation(summary = "Atualiza um empreendimento")
    @PutMapping("/{id}")
    public EmpreendimentoResponseDTO atualizar(@PathVariable Long id, @Valid @RequestBody EmpreendimentoRequestDTO req) {
        return empreendimentoService.atualizar(id, req);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um empreendimento")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        empreendimentoService.remover(id);
    }

    @PatchMapping("/{id}/ativar")
    public EmpreendimentoResponseDTO ativar(@PathVariable Long id) {
        return empreendimentoService.ativar(id);
    }

    @PatchMapping("/{id}/inativar")
    public EmpreendimentoResponseDTO desativar(@PathVariable Long id) {
        return empreendimentoService.desativar(id);
    }

}
