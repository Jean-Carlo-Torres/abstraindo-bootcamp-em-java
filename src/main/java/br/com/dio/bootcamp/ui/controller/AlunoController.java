package br.com.dio.bootcamp.ui.controller;

import br.com.dio.bootcamp.application.dtos.AlunoDto;
import br.com.dio.bootcamp.domain.entities.Aluno;
import br.com.dio.bootcamp.domain.entities.Curso;
import br.com.dio.bootcamp.infra.repository.AlunoRepository;
import br.com.dio.bootcamp.infra.repository.AulaRepository;
import br.com.dio.bootcamp.infra.repository.CursoRepository;
import br.com.dio.bootcamp.infra.repository.MentoriaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private AulaRepository aulaRepository;

    @Autowired
    private MentoriaRepository mentoriaRepository;

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

    @GetMapping
    public Iterable<Aluno> listarAlunos() {
        return alunoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Aluno buscarAluno(@PathVariable Long id) {
        return alunoRepository.findById(id).orElse(null);
    }

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

    @DeleteMapping("/{id}")
    public void excluirAluno(@PathVariable Long id) {
        alunoRepository.deleteById(id);
    }
}