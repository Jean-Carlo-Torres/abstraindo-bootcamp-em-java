package br.com.dio.bootcamp.application.dtos;

import br.com.dio.bootcamp.domain.entities.Aula;
import br.com.dio.bootcamp.domain.entities.Professor;
import jakarta.validation.constraints.NotBlank;

public record ProfessorDto(
        @NotBlank(message = "Nome é obrigatório")
        String nome,
        @NotBlank(message = "Especialidade é obrigatória")
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
