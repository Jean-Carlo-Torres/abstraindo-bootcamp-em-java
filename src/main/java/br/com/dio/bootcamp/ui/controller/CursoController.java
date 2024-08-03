package br.com.dio.bootcamp.ui.controller;

import br.com.dio.bootcamp.application.dtos.CursoDto;
import br.com.dio.bootcamp.domain.entities.Aluno;
import br.com.dio.bootcamp.domain.entities.Aula;
import br.com.dio.bootcamp.domain.entities.Curso;
import br.com.dio.bootcamp.domain.entities.Mentoria;
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
@RequestMapping("/curso")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private AulaRepository aulaRepository;

    @Autowired
    private MentoriaRepository mentoriaRepository;

    @Autowired
    private AlunoRepository alunoRepository;


    @PostMapping
    public Curso criarCurso(@RequestBody @Valid CursoDto dto) {
        Curso curso = new Curso(dto);

        List<Aula> aulas = new ArrayList<>();
        List<Mentoria> mentorias = new ArrayList<>();
        List<Aluno> alunos = new ArrayList<>();

        if (dto.aulas() != null) {
            for (Aula aula : dto.aulas()) {
                Aula aulaPersistida = aulaRepository.findById(aula.getId()).orElse(null);
                if (aulaPersistida != null) {
                    aulas.add(aulaPersistida);
                    aulaPersistida.setCurso(curso);
                }
            }
            curso.setAulas(aulas);
        }

        if (dto.mentorias() != null) {
            for (Mentoria mentoria : dto.mentorias()) {
                Mentoria mentoriaPersistida = mentoriaRepository.findById(mentoria.getId()).orElse(null);
                if (mentoriaPersistida != null) {
                    mentorias.add(mentoriaPersistida);
                    mentoriaPersistida.setCurso(curso);
                }
            }
            curso.setMentorias(mentorias);
        }

        if (dto.alunos() != null) {
            for (Aluno aluno : dto.alunos()) {
                Aluno alunoPersistido = alunoRepository.findById(aluno.getMatricula()).orElse(null);
                if (alunoPersistido != null) {
                    alunos.add(alunoPersistido);
                    alunoPersistido.getCursos().add(curso);
                }
            }
            curso.setAlunos(alunos);
        }

        return cursoRepository.save(curso);
    }

    @GetMapping
    public Iterable<Curso> listarCursos() {
        return cursoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Curso buscarCurso(@PathVariable Long id) {
        return cursoRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Curso atualizarCurso(@PathVariable Long id, @RequestBody @Valid Curso curso) {
        Curso cursoExistente = cursoRepository.findById(id).orElse(null);
        if (cursoExistente != null) {
            cursoExistente.setTitulo(curso.getTitulo());
            cursoExistente.setDescricao(curso.getDescricao());
            cursoExistente.setCargaHoraria(curso.getCargaHoraria());
            cursoExistente.setMentorias(curso.getMentorias());
            cursoExistente.getAulas().forEach(aula -> aula.setCurso(cursoExistente));
            cursoExistente.getMentorias().forEach(mentoria -> mentoria.setCurso(cursoExistente));
            cursoExistente.setAlunos(curso.getAlunos());
            return cursoRepository.save(cursoExistente);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void excluirCurso(@PathVariable Long id) {
        cursoRepository.deleteById(id);
    }
}
