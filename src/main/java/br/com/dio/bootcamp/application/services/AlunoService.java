package br.com.dio.bootcamp.application.services;

import br.com.dio.bootcamp.application.dtos.AlunoDto;
import br.com.dio.bootcamp.domain.entities.Aluno;
import br.com.dio.bootcamp.domain.entities.Curso;
import br.com.dio.bootcamp.infra.repository.AlunoRepository;
import br.com.dio.bootcamp.infra.repository.CursoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    public Aluno criarAluno(AlunoDto dto) {
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

    public Iterable<Aluno> listarAlunos() {
        return alunoRepository.findAll();
    }

    public Aluno buscarAluno(Long id) {
        return alunoRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Aluno não encontrado"));
    }

    public Aluno atualizarAluno(Long id, AlunoDto alunoAtualizado) {
        Aluno alunoExistente = alunoRepository.findById(id).orElse(null);
        if (alunoExistente != null) {
            alunoExistente.setNome(alunoAtualizado.nome());
            alunoExistente.setEmail(alunoAtualizado.email());
            alunoExistente.setCursos(alunoAtualizado.cursos());
            return alunoRepository.save(alunoExistente);
        }
        return null;
    }

    private Aluno atualizarXpAluno(Long id, int xpParaAdicionar) {
        Aluno alunoExistente = alunoRepository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("Aluno não encontrado"));
        Integer xpAtual = alunoExistente.getXp() == null ? 0 : alunoExistente.getXp();
        alunoExistente.setXp(xpAtual + xpParaAdicionar);
        return alunoRepository.save(alunoExistente);
    }

    public Aluno atualizarXpAlunoAoConcluirAula(Long id) {
        return atualizarXpAluno(id, 10);
    }

    public Aluno atualizarXpAlunoAoConcluirMentoria(Long id) {
        return atualizarXpAluno(id, 20);
    }

    public Aluno atualizarXpAlunoAoConcluirCurso(Long id) {
        return atualizarXpAluno(id, 50);
    }


    public void excluirAluno(Long id) {
        alunoRepository.deleteById(id);
    }
}
