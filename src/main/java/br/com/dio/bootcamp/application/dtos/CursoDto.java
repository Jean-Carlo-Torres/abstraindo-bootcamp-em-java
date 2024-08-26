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
        List<Long> aulas,
        List<Long> mentorias,
        List<Long> alunos
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
                curso.getAulas().stream().map(Aula::getId).toList(),
                curso.getMentorias().stream().map(Mentoria::getId).toList(),
                curso.getAlunos().stream().map(Aluno::getMatricula).toList()
        );
    }
}

