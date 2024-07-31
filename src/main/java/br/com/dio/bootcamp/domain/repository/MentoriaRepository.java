package br.com.dio.bootcamp.domain.repository;

import br.com.dio.bootcamp.domain.entities.Mentoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MentoriaRepository extends JpaRepository<Mentoria, Long> {
}
