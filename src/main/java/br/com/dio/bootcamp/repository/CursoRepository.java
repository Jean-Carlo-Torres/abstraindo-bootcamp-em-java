package br.com.dio.bootcamp.repository;

import br.com.dio.bootcamp.domain.entities.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
}
