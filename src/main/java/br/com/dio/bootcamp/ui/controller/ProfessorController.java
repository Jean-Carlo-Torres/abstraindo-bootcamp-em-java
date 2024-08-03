package br.com.dio.bootcamp.ui.controller;

import br.com.dio.bootcamp.application.dtos.ProfessorDto;
import br.com.dio.bootcamp.domain.entities.Professor;
import br.com.dio.bootcamp.infra.repository.AulaRepository;
import br.com.dio.bootcamp.infra.repository.ProfessorRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/professor")
public class ProfessorController {

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private AulaRepository aulaRepository;

    @PostMapping
    public Professor criarProfessor(@RequestBody @Valid ProfessorDto dto) {
        Professor professor = new Professor(dto);
        aulaRepository.findById(dto.aula().getId())
                .ifPresent(professor::setAula);
        return professorRepository.save(professor);
    }

    @GetMapping
    public Iterable<Professor> obterProfessores() {
        return professorRepository.findAll();
    }

    @GetMapping("/{id}")
    public Professor obterProfessorPorId(@PathVariable Long id) {
        return professorRepository.findById(id).orElse(null);
    }

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

    @DeleteMapping("/{id}")
    public void deletarProfessor(@PathVariable Long id) {
        professorRepository.deleteById(id);
    }
}
