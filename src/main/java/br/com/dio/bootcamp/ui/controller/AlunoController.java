package br.com.dio.bootcamp.ui.controller;

import br.com.dio.bootcamp.application.dtos.AlunoDto;
import br.com.dio.bootcamp.application.services.AlunoService;
import br.com.dio.bootcamp.domain.entities.Aluno;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/aluno")
@Tag(name = "Aluno", description = "Endpoints para gerenciamento de alunos")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @Operation(
            summary = "Criar um novo aluno",
            description = "Cria um novo aluno com base nos dados fornecidos")
    @PostMapping
    public Aluno criarAluno(@RequestBody @Valid AlunoDto dto) {
        return alunoService.criarAluno(dto);
    }

    @Operation(
            summary = "Listar todos os alunos",
            description = "Retorna uma lista de todos os alunos cadastrados")
    @GetMapping
    public Iterable<Aluno> listarAlunos() {
        return alunoService.listarAlunos();
    }

    @Operation(
            summary = "Buscar aluno por ID",
            description = "Retorna um aluno pelo seu ID")
    @GetMapping("/{id}")
    public Aluno buscarAluno(@PathVariable Long id) {
        return alunoService.buscarAluno(id);
    }

    @Operation(
            summary = "Atualizar aluno",
            description = "Atualiza um aluno existente com base nos dados fornecidos")
    @PutMapping("/{id}")
    public ResponseEntity<Aluno> atualizarAluno(@PathVariable Long id, @RequestBody @Valid AlunoDto alunoAtualizado) {
        Aluno aluno = alunoService.atualizarAluno(id, alunoAtualizado);
        return aluno != null ? ResponseEntity.ok(aluno) : ResponseEntity.notFound().build();
    }


    @Operation(
            summary = "Adicionar xp ao concluir a aula",
            description = "Adiciona 10 de xp ao aluno após concluir a aula")
    @PatchMapping("/{id}/conclusao/aula")
    public Aluno atualizarXpAlunoAoConcluirAula(@PathVariable Long id) {
        return alunoService.atualizarXpAlunoAoConcluirAula(id);
    }

    @Operation(
            summary = "Adicionar xp ao concluir a mentoria",
            description = "Adiciona 20 de xp ao aluno após concluir a mentoria")
    @PatchMapping("/{id}/conclusao/mentoria")
    public Aluno atualizarXpAlunoAoConcluirMentoria(@PathVariable Long id) {
        return alunoService.atualizarXpAlunoAoConcluirMentoria(id);
    }

    @Operation(
            summary = "Adicionar xp ao concluir o curso",
            description = "Adiciona 50 de xp ao aluno após concluir o curso")
    @PatchMapping("/{id}/conclusao/curso")
    public Aluno atualizarXpAlunoAoConcluirCurso(@PathVariable Long id) {
        return alunoService.atualizarXpAlunoAoConcluirCurso(id);
    }

    @Operation(
            summary = "Deletar aluno",
            description = "Deleta um aluno pelo seu ID"
    )
    @DeleteMapping("/{id}")
    public void excluirAluno(@PathVariable Long id) {
        alunoService.excluirAluno(id);
    }
}