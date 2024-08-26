package br.com.dio.bootcamp.application.services;

import br.com.dio.bootcamp.application.dtos.AulaDto;
import br.com.dio.bootcamp.domain.entities.Aula;
import br.com.dio.bootcamp.infra.repository.AulaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AulaService {

    @Autowired
    private AulaRepository aulaRepository;

    public Aula criarAula(AulaDto dto) {
        Aula aula = new Aula(dto);
        return aulaRepository.save(aula);
    }

    public Iterable<Aula> obterAulas() {
        return aulaRepository.findAll();
    }

    public Aula obterAulaPorId(Long id) {
        return aulaRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Aula não encontrada"));
    }

    public Aula atualizarAula(Long id, AulaDto dto) {
        Aula aulaExistente = aulaRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Aula não encontrada"));

        aulaExistente.setTitulo(dto.titulo());
        aulaExistente.setDescricao(dto.descricao());
        aulaExistente.setDuracao(dto.duracao());
        aulaExistente.setCurso(dto.curso());
        aulaExistente.setMateria(dto.materia());
        aulaExistente.setProfessor(dto.professor());

        return aulaRepository.save(aulaExistente);
    }

    public void excluirAula(Long id) {
        aulaRepository.deleteById(id);
    }
}
