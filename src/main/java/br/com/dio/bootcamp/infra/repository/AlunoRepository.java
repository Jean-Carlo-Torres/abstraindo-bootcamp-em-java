package br.com.dio.bootcamp.infra.repository;

import br.com.dio.bootcamp.domain.entities.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}
