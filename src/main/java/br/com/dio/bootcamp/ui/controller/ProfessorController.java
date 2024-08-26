package br.com.dio.bootcamp.ui.controller;

import br.com.dio.bootcamp.application.dtos.ProfessorDto;
import br.com.dio.bootcamp.application.services.ProfessorService;
import br.com.dio.bootcamp.domain.entities.Professor;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/professor")
@Tag(name = "Professor", description = "Endpoints para gerenciamento de professores")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @Operation(
            summary = "Criar um novo professor",
            description = "Cria um novo professor com base nos dados fornecidos"
    )
    @PostMapping
    public Professor criarProfessor(@RequestBody @Valid ProfessorDto dto) {
        return professorService.criarProfessor(dto);
    }

    @Operation(
            summary = "Obter todos os professores",
            description = "Retorna uma lista de todos os professores cadastrados"
    )
    @GetMapping
    public Iterable<Professor> obterProfessores() {
        return professorService.obterProfessores();
    }

    @Operation(
            summary = "Obter professor por ID",
            description = "Retorna um professor pelo seu ID"
    )
    @GetMapping("/{id}")
    public Professor obterProfessorPorId(@PathVariable Long id) {
        return professorService.obterProfessorPorId(id);
    }

    @Operation(
            summary = "Atualizar professor",
            description = "Atualiza um professor existente com base nos dados fornecidos"
    )
    @PutMapping("/{id}")
    public ResponseEntity<Professor> atualizarProfessor(@PathVariable Long id, @RequestBody @Valid ProfessorDto dto) {
        Professor professor = professorService.atualizarProfessor(id, dto);
        return professor != null ? ResponseEntity.ok(professor) : ResponseEntity.notFound().build();
    }

    @Operation(
            summary = "Deletar professor",
            description = "Deleta um professor pelo seu ID"
    )
    @DeleteMapping("/{id}")
    public void deletarProfessor(@PathVariable Long id) {
        professorService.deletarProfessor(id);
    }
}
