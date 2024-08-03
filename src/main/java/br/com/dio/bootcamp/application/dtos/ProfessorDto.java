package br.com.dio.bootcamp.application.dtos;

import br.com.dio.bootcamp.domain.entities.Aula;
import br.com.dio.bootcamp.domain.entities.Professor;

public record ProfessorDto(
        String nome,
        String especialidade,
        Aula aula
) {
    public ProfessorDto(Professor professor) {
        this(
                professor.getNome(),
                professor.getEspecialidade(),
                professor.getAula()
        );
    }
}
