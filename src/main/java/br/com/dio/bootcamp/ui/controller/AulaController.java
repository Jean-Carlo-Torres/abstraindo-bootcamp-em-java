package br.com.dio.bootcamp.ui.controller;

import br.com.dio.bootcamp.application.dtos.AulaDto;
import br.com.dio.bootcamp.application.services.AulaService;
import br.com.dio.bootcamp.domain.entities.Aula;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/aula")
@Tag(name = "Aula", description = "Endpoints para gerenciamento de aulas")
public class AulaController {

    @Autowired
    private AulaService aulaService;

    @Operation(
            summary = "Criar uma nova aula",
            description = "Cria uma nova aula com base nos dados fornecidos"
    )
    @PostMapping
    public Aula criarAula(@RequestBody @Valid AulaDto dto) {
        return aulaService.criarAula(dto);
    }

    @Operation(
            summary = "Obter todas as aulas",
            description = "Retorna uma lista de todas as aulas cadastradas"
    )
    @GetMapping
    public Iterable<Aula> obterAulas() {
        return aulaService.obterAulas();
    }

    @Operation(
            summary = "Obter uma aula por ID",
            description = "Retorna uma aula específica com base no ID fornecido"
    )
    @GetMapping("/{id}")
    public Aula obterAulaPorId(@PathVariable Long id) {
        return aulaService.obterAulaPorId(id);
    }

    @Operation(
            summary = "Atualizar uma aula",
            description = "Atualiza uma aula existente com base nos dados fornecidos"
    )
    @PutMapping("/{id}")
    public ResponseEntity<Aula> atualizarAula(@PathVariable Long id, @RequestBody @Valid AulaDto aulaAtualizada) {
        Aula aula = aulaService.atualizarAula(id, aulaAtualizada);
        return aula != null ? ResponseEntity.ok(aula) : ResponseEntity.notFound().build();
    }

    @Operation(
            summary = "Excluir uma aula",
            description = "Exclui uma aula existente com base no ID fornecido"
    )
    @DeleteMapping("/{id}")
    public void excluirAula(@PathVariable Long id) {
        aulaService.excluirAula(id);
    }
}

