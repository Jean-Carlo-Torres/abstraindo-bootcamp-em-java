package br.com.dio.bootcamp.application.dtos;

import br.com.dio.bootcamp.domain.entities.Aluno;
import br.com.dio.bootcamp.domain.entities.Aula;
import br.com.dio.bootcamp.domain.entities.Curso;
import br.com.dio.bootcamp.domain.entities.Mentoria;

import java.util.ArrayList;
import java.util.List;

public record CursoDto(
        String titulo,
        String descricao,
        Integer cargaHoraria,
        List<Aula> aulas,
        List<Mentoria> mentorias,
        List<Aluno> alunos
) {
    public CursoDto {
        if (aulas == null) {
            aulas = new ArrayList<>();
        }
        if (mentorias == null) {
            mentorias = new ArrayList<>();
        }
        if (alunos == null) {
            alunos = new ArrayList<>();
        }
    }

    public CursoDto(Curso curso) {
        this(
                curso.getTitulo(),
                curso.getDescricao(),
                curso.getCargaHoraria(),
                curso.getAulas(),
                curso.getMentorias(),
                curso.getAlunos()
        );
    }
}

