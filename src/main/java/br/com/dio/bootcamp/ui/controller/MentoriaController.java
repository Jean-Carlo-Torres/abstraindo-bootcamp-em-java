package br.com.dio.bootcamp.ui.controller;

import br.com.dio.bootcamp.application.dtos.MentoriaDto;
import br.com.dio.bootcamp.domain.entities.Mentoria;
import br.com.dio.bootcamp.infra.repository.MentoriaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mentoria")
public class MentoriaController {

    @Autowired
    private MentoriaRepository mentoriaRepository;

    @PostMapping
    public Mentoria criarMentoria(@RequestBody @Valid MentoriaDto dto) {
        Mentoria mentoria = new Mentoria(dto);
        return mentoriaRepository.save(mentoria);
    }

    @GetMapping
    public Iterable<Mentoria> obterMentorias() {
        return mentoriaRepository.findAll();
    }

    @GetMapping("/{id}")
    public Mentoria obterMentoriaPorId(@PathVariable Long id) {
        return mentoriaRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Mentoria atualizarMentoria(@PathVariable Long id, @RequestBody @Valid MentoriaDto dto) {
        Mentoria mentoriaAtualizada = new Mentoria(dto);
        Mentoria mentoria = mentoriaRepository.findById(id).orElse(null);
        if (mentoria != null) {
            mentoria.setTitulo(mentoriaAtualizada.getTitulo());
            mentoria.setDescricao(mentoriaAtualizada.getDescricao());
            mentoria.setData(mentoriaAtualizada.getData());
            return mentoriaRepository.save(mentoria);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deletarMentoria(@PathVariable Long id) {
        mentoriaRepository.deleteById(id);
    }
}
