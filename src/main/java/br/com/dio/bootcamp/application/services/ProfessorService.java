package br.com.dio.bootcamp.application.services;

import br.com.dio.bootcamp.application.dtos.ProfessorDto;
import br.com.dio.bootcamp.domain.entities.Professor;
import br.com.dio.bootcamp.infra.repository.AulaRepository;
import br.com.dio.bootcamp.infra.repository.ProfessorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private AulaRepository aulaRepository;

    public Professor criarProfessor(ProfessorDto dto) {
        Professor professor = new Professor(dto);
        aulaRepository.findById(dto.aula().getId())
                .ifPresent(professor::setAula);
        return professorRepository.save(professor);
    }

    public Iterable<Professor> obterProfessores() {
        return professorRepository.findAll();
    }

    public Professor obterProfessorPorId(Long id) {
        return professorRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Professor não encontrado"));
    }

    public Professor atualizarProfessor(Long id, ProfessorDto dto) {
        Professor professorExistente = professorRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Professor não encontrado"));

        professorExistente.setNome(dto.nome());
        professorExistente.setEspecialidade(dto.especialidade());
        professorExistente.setAula(dto.aula());

        return professorRepository.save(professorExistente);
    }

    public void deletarProfessor(long id){
        professorRepository.deleteById(id);
    }
}
