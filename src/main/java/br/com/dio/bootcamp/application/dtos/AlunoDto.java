package br.com.dio.bootcamp.application.dtos;

import br.com.dio.bootcamp.domain.entities.Aluno;
import br.com.dio.bootcamp.domain.entities.Curso;

import java.util.List;

public record AlunoDto(
        String nome,
        String email,
        List<Curso> cursos
) {

    public AlunoDto(Aluno aluno) {
       this(aluno.getNome(), aluno.getEmail(), aluno.getCursos());
    }
}


