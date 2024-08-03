package br.com.dio.bootcamp.ui.controller;

import br.com.dio.bootcamp.application.dtos.AulaDto;
import br.com.dio.bootcamp.domain.entities.Aula;
import br.com.dio.bootcamp.infra.repository.AulaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/aula")
public class AulaController {

    @Autowired
    private AulaRepository aulaRepository;

    @PostMapping
    public Aula criarAula(@RequestBody @Valid AulaDto dados) {
        Aula aula = new Aula(dados);
        return aulaRepository.save(aula);
    }

    @GetMapping
    public Iterable<Aula> obterAulas() {
        return aulaRepository.findAll();
    }

    @GetMapping("/{id}")
    public Aula obterAulaPorId(@PathVariable Long id) {
        return aulaRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Aula atualizarAula(@PathVariable Long id, @RequestBody @Valid AulaDto dados) {
        Aula aulaExistente = aulaRepository.findById(id).orElse(null);
        if (aulaExistente != null) {
            aulaExistente.setTitulo(dados.titulo());
            aulaExistente.setDescricao(dados.descricao());
            aulaExistente.setDuracao(dados.duracao());
            aulaExistente.setCurso(dados.curso());
            aulaExistente.setMateria(dados.materia());
            aulaExistente.setProfessor(dados.professor());
            return aulaRepository.save(aulaExistente);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void excluirAula(@PathVariable Long id) {
        aulaRepository.deleteById(id);
    }
}

