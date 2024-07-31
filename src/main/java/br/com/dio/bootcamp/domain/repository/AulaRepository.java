package br.com.dio.bootcamp.domain.repository;

import br.com.dio.bootcamp.domain.entities.Aula;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AulaRepository extends JpaRepository<Aula, Long> {
}