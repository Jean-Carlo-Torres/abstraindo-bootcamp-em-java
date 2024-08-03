package br.com.dio.bootcamp.application.dtos;

import br.com.dio.bootcamp.domain.entities.Curso;
import br.com.dio.bootcamp.domain.entities.Mentoria;

import java.time.LocalDate;

public record MentoriaDto(
        String titulo,
        String descricao,
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
