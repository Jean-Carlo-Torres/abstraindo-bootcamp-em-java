package br.com.dio.bootcamp.application.services;

import br.com.dio.bootcamp.application.dtos.CursoDto;
import br.com.dio.bootcamp.domain.entities.Aluno;
import br.com.dio.bootcamp.domain.entities.Aula;
import br.com.dio.bootcamp.domain.entities.Curso;
import br.com.dio.bootcamp.domain.entities.Mentoria;
import br.com.dio.bootcamp.infra.repository.AlunoRepository;
import br.com.dio.bootcamp.infra.repository.AulaRepository;
import br.com.dio.bootcamp.infra.repository.CursoRepository;
import br.com.dio.bootcamp.infra.repository.MentoriaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private AulaRepository aulaRepository;

    @Autowired
    private MentoriaRepository mentoriaRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    public Curso criarCurso(CursoDto dto) {
        Curso curso = new Curso();
        curso.setTitulo(dto.titulo());
        curso.setDescricao(dto.descricao());
        curso.setCargaHoraria(dto.cargaHoraria());

        List<Aula> aulas = dto.aulas().stream()
                .map(aulaId -> aulaRepository.findById(aulaId)
                        .orElseThrow(() -> new EntityNotFoundException("Aula não encontrada: " + aulaId)))
                .toList();
        curso.setAulas(aulas);

        aulas.forEach(aula -> aula.setCurso(curso));

        List<Mentoria> mentorias = dto.mentorias().stream()
                .map(mentoriaId -> mentoriaRepository.findById(mentoriaId)
                        .orElseThrow(() -> new EntityNotFoundException("Mentoria não encontrada: " + mentoriaId)))
                .toList();
        curso.setMentorias(mentorias);

        mentorias.forEach(mentoria -> mentoria.setCurso(curso));

        List<Aluno> alunos = dto.alunos().stream()
                .map(alunoId -> alunoRepository.findById(alunoId)
                        .orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado: " + alunoId)))
                .toList();
        curso.setAlunos(alunos);

        alunos.forEach(aluno -> aluno.getCursos().add(curso));

        return cursoRepository.save(curso);
    }


    public Iterable<Curso> listarCursos() {
        return cursoRepository.findAll();
    }

    public Curso buscarCursoPorId(Long id) {
        return cursoRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Curso não encontrado"));
    }

    public Curso atualizarCurso(Long id, CursoDto dto) {
        Curso cursoExistente = cursoRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Curso não encontrado"));

        cursoExistente.setTitulo(dto.titulo());
        cursoExistente.setDescricao(dto.descricao());
        cursoExistente.setCargaHoraria(dto.cargaHoraria());

        List<Aula> novasAulas = dto.aulas().stream()
                .map(aulaId -> aulaRepository.findById(aulaId)
                        .orElseThrow(() -> new EntityNotFoundException("Aula não encontrada")))
                .toList();
        cursoExistente.setAulas(novasAulas);

        List<Mentoria> novasMentorias = dto.mentorias().stream()
                .map(mentoriaId -> mentoriaRepository.findById(mentoriaId)
                        .orElseThrow(() -> new EntityNotFoundException("Mentoria não encontrada")))
                .toList();
        cursoExistente.setMentorias(novasMentorias);

        List<Aluno> novosAlunos = dto.alunos().stream()
                .map(alunoId -> alunoRepository.findById(alunoId)
                        .orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado")))
                .toList();
        cursoExistente.setAlunos(novosAlunos);

        return cursoRepository.save(cursoExistente);
    }

    public void excluirCurso(Long id) {
        cursoRepository.deleteById(id);
    }
}
