package br.com.dio.bootcamp.application.dtos;

import br.com.dio.bootcamp.domain.entities.Aluno;
import br.com.dio.bootcamp.domain.entities.Curso;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record AlunoDto(
        @NotBlank(message = "Nome é obrigatório")
        String nome,

        @Email(message = "Email deve ser válido")
        @NotBlank(message = "Email é obrigatório")
        String email,

        List<Curso> cursos
) {

    public AlunoDto(Aluno aluno) {
       this(aluno.getNome(), aluno.getEmail(), aluno.getCursos());
    }
}


