package br.com.dio.bootcamp.application.dtos;

import br.com.dio.bootcamp.domain.entities.Aula;
import br.com.dio.bootcamp.domain.entities.Curso;
import br.com.dio.bootcamp.domain.entities.Professor;
import br.com.dio.bootcamp.domain.enums.Materia;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AulaDto(
        @NotBlank(message = "Título é obrigatório")
        String titulo,
        @NotBlank(message = "Descrição é obrigatória")
        String descricao,
        @NotNull(message = "Duração é obrigatória")
        @Min(value = 1, message = "Duração deve ser maior que zero")
        Integer duracao,
        Curso curso,
        Materia materia,
        Professor professor
) {
    public AulaDto(Aula aula) {
        this(aula.getTitulo(), aula.getDescricao(), aula.getDuracao(), aula.getCurso(), aula.getMateria(), aula.getProfessor());
    }
}
