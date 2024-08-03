package br.com.dio.bootcamp.application.dtos;

import br.com.dio.bootcamp.domain.entities.Aula;
import br.com.dio.bootcamp.domain.entities.Curso;
import br.com.dio.bootcamp.domain.entities.Professor;
import br.com.dio.bootcamp.domain.enums.Materia;

public record AulaDto(
        String titulo,
        String descricao,
        Integer duracao,
        Curso curso,
        Materia materia,
        Professor professor
) {
    public AulaDto(Aula aula) {
        this(aula.getTitulo(), aula.getDescricao(), aula.getDuracao(), aula.getCurso(), aula.getMateria(), aula.getProfessor());
    }
}
