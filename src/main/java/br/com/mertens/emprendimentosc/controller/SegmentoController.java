package br.com.mertens.emprendimentosc.controller;

import br.com.mertens.emprendimentosc.dto.segmento.SegmentoRequestDTO;
import br.com.mertens.emprendimentosc.dto.segmento.SegmentoResponseDTO;
import br.com.mertens.emprendimentosc.service.SegmentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/segmentos")
@Tag(name = "Segmentos")
public class SegmentoController {
    
    private final SegmentoService segmentoService;

    public SegmentoController(SegmentoService segmentoService) {
        this.segmentoService = segmentoService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Pesquisa segmento pelo ID")
    public SegmentoResponseDTO buscarPorId(@PathVariable Long id) {
        return segmentoService.buscarPorId(id);
    }

    @GetMapping
    @Operation(summary = "Lista todas os segmentos")
    public List<SegmentoResponseDTO> listar() {
        return segmentoService.listar();
    }

    @PostMapping
    @Operation(summary = "Cria um novo seguimento")
    @ResponseStatus(HttpStatus.CREATED)
    public SegmentoResponseDTO salvar(@Valid @RequestBody SegmentoRequestDTO dto) {
        return segmentoService.salvar(dto);
    }

    @Operation(summary = "Atualiza um segmento")
    @PutMapping("/{id}")
    public SegmentoResponseDTO atualizar(@PathVariable Long id, @Valid @RequestBody SegmentoRequestDTO req) {
        return segmentoService.atualizar(id, req);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um segmento")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        segmentoService.remover(id);
    }
}
