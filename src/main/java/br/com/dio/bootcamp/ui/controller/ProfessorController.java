package br.com.dio.bootcamp.ui.controller;

import br.com.dio.bootcamp.application.dtos.ProfessorDto;
import br.com.dio.bootcamp.domain.entities.Professor;
import br.com.dio.bootcamp.infra.repository.AulaRepository;
import br.com.dio.bootcamp.infra.repository.ProfessorRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/professor")
@Tag(name = "Professor", description = "Endpoints para gerenciamento de professores")
public class ProfessorController {

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private AulaRepository aulaRepository;

    @Operation(
            summary = "Criar um novo professor",
            description = "Cria um novo professor com base nos dados fornecidos"
    )
    @PostMapping
    public Professor criarProfessor(@RequestBody @Valid ProfessorDto dto) {
        Professor professor = new Professor(dto);
        aulaRepository.findById(dto.aula().getId())
                .ifPresent(professor::setAula);
        return professorRepository.save(professor);
    }

    @Operation(
            summary = "Obter todos os professores",
            description = "Retorna uma lista de todos os professores cadastrados"
    )
    @GetMapping
    public Iterable<Professor> obterProfessores() {
        return professorRepository.findAll();
    }

    @Operation(
            summary = "Obter professor por ID",
            description = "Retorna um professor pelo seu ID"
    )
    @GetMapping("/{id}")
    public Professor obterProfessorPorId(@PathVariable Long id) {
        return professorRepository.findById(id).orElse(null);
    }

    @Operation(
            summary = "Atualizar professor",
            description = "Atualiza um professor existente com base nos dados fornecidos"
    )
    @PutMapping("/{id}")
    public Professor atualizarProfessor(@PathVariable Long id, @RequestBody @Valid Professor professorAtualizado) {
        Professor professorExistente = professorRepository.findById(id).orElse(null);
        if (professorExistente != null) {
            professorExistente.setNome(professorAtualizado.getNome());
            professorExistente.setEspecialidade(professorAtualizado.getEspecialidade());
            professorExistente.setAula(professorAtualizado.getAula());
            return professorRepository.save(professorExistente);
        }
        return null;
    }

    @Operation(
            summary = "Deletar professor",
            description = "Deleta um professor pelo seu ID"
    )
    @DeleteMapping("/{id}")
    public void deletarProfessor(@PathVariable Long id) {
        professorRepository.deleteById(id);
    }
}
