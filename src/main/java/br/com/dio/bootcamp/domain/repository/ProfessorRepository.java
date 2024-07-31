package br.com.dio.bootcamp.domain.repository;

import br.com.dio.bootcamp.domain.entities.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
}
