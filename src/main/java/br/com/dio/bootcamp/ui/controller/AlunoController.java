package br.com.dio.bootcamp.ui.controller;

import br.com.dio.bootcamp.application.dtos.AlunoDto;
import br.com.dio.bootcamp.domain.entities.Aluno;
import br.com.dio.bootcamp.domain.entities.Curso;
import br.com.dio.bootcamp.infra.repository.AlunoRepository;
import br.com.dio.bootcamp.infra.repository.CursoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/aluno")
@Tag(name = "Aluno", description = "Endpoints para gerenciamento de alunos")
public class AlunoController {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Operation(
            summary = "Criar um novo aluno",
            description = "Cria um novo aluno com base nos dados fornecidos")
    @PostMapping
    public Aluno criarAluno(@RequestBody @Valid AlunoDto dto) {
        Aluno aluno = new Aluno(dto);
        List<Curso> cursos = new ArrayList<>();
        for (Curso curso : dto.cursos()) {
            Curso cursoPersistido = cursoRepository.findById(curso.getId()).orElse(null);
            if (cursoPersistido != null) {
                cursos.add(cursoPersistido);
                cursoPersistido.getAlunos().add(aluno);
            }
        }
        aluno.setCursos(cursos);
        return alunoRepository.save(aluno);
    }

    @Operation(
            summary = "Listar todos os alunos",
            description = "Retorna uma lista de todos os alunos cadastrados")
    @GetMapping
    public Iterable<Aluno> listarAlunos() {
        return alunoRepository.findAll();
    }

    @Operation(
            summary = "Buscar aluno por ID",
            description = "Retorna um aluno pelo seu ID")
    @GetMapping("/{id}")
    public Aluno buscarAluno(@PathVariable Long id) {
        return alunoRepository.findById(id).orElse(null);
    }

    @Operation(
            summary = "Atualizar aluno",
            description = "Atualiza um aluno existente com base nos dados fornecidos")
    @PutMapping("/{id}")
    public Aluno atualizarAluno(@PathVariable Long id, @RequestBody @Valid AlunoDto alunoAtualizado) {
        Aluno alunoExistente = alunoRepository.findById(id).orElse(null);
        if (alunoExistente != null) {
            alunoExistente.setNome(alunoAtualizado.nome());
            alunoExistente.setEmail(alunoAtualizado.email());
            alunoExistente.setCursos(alunoAtualizado.cursos());
            return alunoRepository.save(alunoExistente);
        }
        return null;
    }

    @Operation(
            summary = "Adicionar xp ao concluir a aula",
            description = "Adiciona 10 de xp ao aluno após concluir a aula")
    @PatchMapping("/{id}/conclusao/aula")
    public Aluno atualizarXpAlunoAoConcluirAula(@PathVariable Long id) {
        Aluno alunoExistente = alunoRepository.findById(id).orElse(null);
        if (alunoExistente != null) {
            int xp = 10;
            Integer xpAtual = alunoExistente.getXp();
            if (xpAtual == null) {
                xpAtual = 0;
            }
            alunoExistente.setXp(xpAtual + xp);
            return alunoRepository.save(alunoExistente);
        }
        return null;
    }

    @Operation(
            summary = "Adicionar xp ao concluir a mentoria",
            description = "Adiciona 20 de xp ao aluno após concluir a mentoria")
    @PatchMapping("/{id}/conclusao/mentoria")
    public Aluno atualizarXpAlunoAoConcluirMentoria(@PathVariable Long id) {
        Aluno alunoExistente = alunoRepository.findById(id).orElse(null);
        if (alunoExistente != null) {
            int xp = 20;
            Integer xpAtual = alunoExistente.getXp();
            if (xpAtual == null) {
                xpAtual = 0;
            }
            alunoExistente.setXp(xpAtual + xp);
            return alunoRepository.save(alunoExistente);
        }

        return null;
    }

    @Operation(
            summary = "Adicionar xp ao concluir o curso",
            description = "Adiciona 50 de xp ao aluno após concluir o curso")
    @PatchMapping("/{id}/conclusao/curso")
    public Aluno atualizarXpAlunoAoConcluirCurso(@PathVariable Long id) {
        Aluno alunoExistente = alunoRepository.findById(id).orElse(null);
        if (alunoExistente != null) {
            int xp = 50;
            Integer xpAtual = alunoExistente.getXp();
            if (xpAtual == null) {
                xpAtual = 0;
            }
            alunoExistente.setXp(xpAtual + xp);
            return alunoRepository.save(alunoExistente);
        }
        return null;
    }

    @Operation(
            summary = "Deletar aluno",
            description = "Deleta um aluno pelo seu ID"
    )
    @DeleteMapping("/{id}")
    public void excluirAluno(@PathVariable Long id) {
        alunoRepository.deleteById(id);
    }
}