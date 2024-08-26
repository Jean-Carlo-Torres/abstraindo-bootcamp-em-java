package br.com.dio.bootcamp.ui.controller;

import br.com.dio.bootcamp.application.dtos.CursoDto;
import br.com.dio.bootcamp.application.services.CursoService;
import br.com.dio.bootcamp.domain.entities.Aluno;
import br.com.dio.bootcamp.domain.entities.Aula;
import br.com.dio.bootcamp.domain.entities.Curso;
import br.com.dio.bootcamp.domain.entities.Mentoria;
import br.com.dio.bootcamp.infra.repository.AlunoRepository;
import br.com.dio.bootcamp.infra.repository.AulaRepository;
import br.com.dio.bootcamp.infra.repository.CursoRepository;
import br.com.dio.bootcamp.infra.repository.MentoriaRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/curso")
@Tag(name = "Curso", description = "Endpoints para gerenciamento de cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @Operation(
            summary = "Criar um novo curso",
            description = "Cria um novo curso com base nos dados fornecidos"
    )
    @PostMapping
    public Curso criarCurso(@RequestBody @Valid CursoDto dto) {
        return cursoService.criarCurso(dto);
    }

    @Operation(
            summary = "Listar todos os cursos",
            description = "Retorna uma lista de todos os cursos cadastrados"
    )
    @GetMapping
    public Iterable<Curso> listarCursos() {
        return cursoService.listarCursos();
    }

    @Operation(
            summary = "Buscar curso por ID",
            description = "Retorna um curso pelo seu ID"
    )
    @GetMapping("/{id}")
    public Curso buscarCursoPorId(@PathVariable Long id) {
        return cursoService.buscarCursoPorId(id);
    }

    @Operation(
            summary = "Atualizar curso",
            description = "Atualiza um curso existente com base nos dados fornecidos"
    )
    @PutMapping("/{id}")
    public ResponseEntity<Curso> atualizarCurso(@PathVariable Long id, @RequestBody @Valid CursoDto dto) {
        Curso curso = cursoService.atualizarCurso(id, dto);
        return curso != null ? ResponseEntity.ok(curso) : ResponseEntity.notFound().build();
    }

    @Operation(
            summary = "Excluir curso",
            description = "Exclui um curso existente com base no ID fornecido"
    )
    @DeleteMapping("/{id}")
    public void excluirCurso(@PathVariable Long id) {
        cursoService.excluirCurso(id);
    }
}
