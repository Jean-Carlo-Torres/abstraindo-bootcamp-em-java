package br.com.dio.bootcamp.controller;

import br.com.dio.bootcamp.domain.entities.Curso;
import br.com.dio.bootcamp.domain.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/curso")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping
    public Curso criarCurso(@RequestBody Curso curso) {
        curso.getAulas().forEach(aula -> aula.setCurso(curso));
        curso.getMentorias().forEach(mentoria -> mentoria.setCurso(curso));
        return cursoRepository.save(curso);
    }

    @GetMapping
    public Iterable<Curso> listarCursos() {
        return cursoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Curso buscarCurso(@PathVariable Long id) {
        return cursoRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Curso atualizarCurso(@PathVariable Long id, @RequestBody Curso curso) {
        Curso cursoExistente = cursoRepository.findById(id).orElse(null);
        if (cursoExistente != null) {
            cursoExistente.setTitulo(curso.getTitulo());
            cursoExistente.setDescricao(curso.getDescricao());
            cursoExistente.setCargaHoraria(curso.getCargaHoraria());
            cursoExistente.setMentorias(curso.getMentorias());
            cursoExistente.getAulas().forEach(aula -> aula.setCurso(cursoExistente));
            cursoExistente.getMentorias().forEach(mentoria -> mentoria.setCurso(cursoExistente));
            cursoExistente.setAlunos(curso.getAlunos());
            return cursoRepository.save(cursoExistente);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void excluirCurso(@PathVariable Long id) {
        cursoRepository.deleteById(id);
    }
}
