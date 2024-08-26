package br.com.dio.bootcamp.application.dtos;

import br.com.dio.bootcamp.domain.entities.Curso;
import br.com.dio.bootcamp.domain.entities.Mentoria;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record MentoriaDto(
        @NotBlank(message = "Título é obrigatório")
        String titulo,
        @NotBlank(message = "Descrição é obrigatória")
        String descricao,
        @NotNull(message = "Data é obrigatória")
        LocalDate data,
        Curso curso
) {
    public MentoriaDto(Mentoria mentoria){
        this(
                mentoria.getTitulo(),
                mentoria.getDescricao(),
                mentoria.getData(),
                mentoria.getCurso()
        );
    }

}
