package br.com.dio.bootcamp.controller;

import br.com.dio.bootcamp.domain.entities.Aula;
import br.com.dio.bootcamp.domain.repository.AulaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/aula")
public class AulaController {

    @Autowired
    private AulaRepository aulaRepository;

    @PostMapping
    public Aula criarAula(Aula aula) {
        return aulaRepository.save(aula);
    }

    @GetMapping
    public Iterable<Aula> obterAulas() {
        return aulaRepository.findAll();
    }

    @GetMapping("/{id}")
    public Aula obterAulaPorId(Long id) {
        return aulaRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Aula atualizarAula(@PathVariable Long id, @RequestBody Aula aulaAtualizada) {
        Aula aulaExistente = aulaRepository.findById(id).orElse(null);
        if (aulaExistente != null) {
            aulaExistente.setTitulo(aulaAtualizada.getTitulo());
            aulaExistente.setDescricao(aulaAtualizada.getDescricao());
            aulaExistente.setDuracao(aulaAtualizada.getDuracao());
            aulaExistente.setCurso(aulaAtualizada.getCurso());
            aulaExistente.setMateria(aulaAtualizada.getMateria());
            aulaExistente.setProfessor(aulaAtualizada.getProfessor());
            return aulaRepository.save(aulaExistente);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void excluirAula(@PathVariable Long id) {
        aulaRepository.deleteById(id);
    }

}
