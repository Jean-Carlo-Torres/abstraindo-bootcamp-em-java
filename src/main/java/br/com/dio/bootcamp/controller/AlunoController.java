package br.com.dio.bootcamp.controller;

import br.com.dio.bootcamp.domain.entities.Aluno;
import br.com.dio.bootcamp.domain.entities.Curso;
import br.com.dio.bootcamp.domain.repository.AlunoRepository;
import br.com.dio.bootcamp.domain.repository.CursoRepository;
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

    @PostMapping
    public Aluno criarAluno(@RequestBody Aluno aluno) {
        List<Curso> cursos = aluno.getCursos();
        List<Curso> cursosPersistidos = new ArrayList<>();

        for (Curso curso : cursos) {
            Curso cursoPersistido = cursoRepository.findById(curso.getId()).orElse(null);
            if (cursoPersistido != null) {
                cursosPersistidos.add(cursoPersistido);
                cursoPersistido.getAlunos().add(aluno);
            }
        }

        aluno.setCursos(cursosPersistidos);
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
    public Aluno atualizarAluno(@PathVariable Long id, @RequestBody Aluno alunoAtualizado) {
        Aluno alunoExistente = alunoRepository.findById(id).orElse(null);
        if (alunoExistente != null) {
            alunoExistente.setNome(alunoAtualizado.getNome());
            alunoExistente.setEmail(alunoAtualizado.getEmail());

            List<Curso> cursos = alunoAtualizado.getCursos();
            List<Curso> cursosPersistidos = new ArrayList<>();

            for (Curso curso : cursos) {
                Curso cursoPersistido = cursoRepository.findById(curso.getId()).orElse(null);
                if (cursoPersistido != null) {
                    cursosPersistidos.add(cursoPersistido);
                    cursoPersistido.getAlunos().add(alunoExistente);
                }
            }

            alunoExistente.setCursos(cursosPersistidos);
            return alunoRepository.save(alunoExistente);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void excluirAluno(@PathVariable Long id) {
        alunoRepository.deleteById(id);
    }
}

