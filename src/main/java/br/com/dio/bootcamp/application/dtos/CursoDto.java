package br.com.dio.bootcamp.application.dtos;

import br.com.dio.bootcamp.domain.entities.Aluno;
import br.com.dio.bootcamp.domain.entities.Aula;
import br.com.dio.bootcamp.domain.entities.Curso;
import br.com.dio.bootcamp.domain.entities.Mentoria;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

public record CursoDto(
        @NotBlank(message = "Título é obrigatório")
        String titulo,
        @NotBlank(message = "Descrição é obrigatória")
        String descricao,
        @NotNull(message = "Carga horária é obrigatória")
        @Min(value = 1, message = "Carga horária deve ser maior que 0")
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

