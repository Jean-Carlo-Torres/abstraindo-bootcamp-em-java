package br.com.dio.bootcamp.ui.controller;

import br.com.dio.bootcamp.application.dtos.AulaDto;
import br.com.dio.bootcamp.domain.entities.Aula;
import br.com.dio.bootcamp.infra.repository.AulaRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/aula")
@Tag(name = "Aula", description = "Endpoints para gerenciamento de aulas")
public class AulaController {

    @Autowired
    private AulaRepository aulaRepository;

    @Operation(
            summary = "Criar uma nova aula",
            description = "Cria uma nova aula com base nos dados fornecidos"
    )
    @PostMapping
    public Aula criarAula(@RequestBody @Valid AulaDto dados) {
        Aula aula = new Aula(dados);
        return aulaRepository.save(aula);
    }

    @Operation(
            summary = "Obter todas as aulas",
            description = "Retorna uma lista de todas as aulas cadastradas"
    )
    @GetMapping
    public Iterable<Aula> obterAulas() {
        return aulaRepository.findAll();
    }

    @Operation(
            summary = "Obter uma aula por ID",
            description = "Retorna uma aula específica com base no ID fornecido"
    )
    @GetMapping("/{id}")
    public Aula obterAulaPorId(@PathVariable Long id) {
        return aulaRepository.findById(id).orElse(null);
    }

    @Operation(
            summary = "Atualizar uma aula",
            description = "Atualiza uma aula existente com base nos dados fornecidos"
    )
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

    @Operation(
            summary = "Excluir uma aula",
            description = "Exclui uma aula existente com base no ID fornecido"
    )
    @DeleteMapping("/{id}")
    public void excluirAula(@PathVariable Long id) {
        aulaRepository.deleteById(id);
    }
}

