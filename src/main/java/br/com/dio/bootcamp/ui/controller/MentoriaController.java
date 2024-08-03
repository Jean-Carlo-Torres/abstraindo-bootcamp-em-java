package br.com.dio.bootcamp.ui.controller;

import br.com.dio.bootcamp.application.dtos.MentoriaDto;
import br.com.dio.bootcamp.domain.entities.Mentoria;
import br.com.dio.bootcamp.infra.repository.MentoriaRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mentoria")
@Tag(name = "Mentoria", description = "Endpoints para gerenciamento de mentorias")
public class MentoriaController {

    @Autowired
    private MentoriaRepository mentoriaRepository;

    @Operation(
            summary = "Criar uma nova mentoria",
            description = "Cria uma nova mentoria com base nos dados fornecidos"
    )
    @PostMapping
    public Mentoria criarMentoria(@RequestBody @Valid MentoriaDto dto) {
        Mentoria mentoria = new Mentoria(dto);
        return mentoriaRepository.save(mentoria);
    }

    @Operation(
            summary = "Obter todas as mentorias",
            description = "Retorna uma lista de todas as mentorias cadastradas"
    )
    @GetMapping
    public Iterable<Mentoria> obterMentorias() {
        return mentoriaRepository.findAll();
    }

    @Operation(
            summary = "Obter mentoria por ID",
            description = "Retorna uma mentoria específica com base no ID fornecido"
    )
    @GetMapping("/{id}")
    public Mentoria obterMentoriaPorId(@PathVariable Long id) {
        return mentoriaRepository.findById(id).orElse(null);
    }

    @Operation(
            summary = "Atualizar mentoria",
            description = "Atualiza uma mentoria existente com base nos dados fornecidos"
    )
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

    @Operation(
            summary = "Deletar mentoria",
            description = "Exclui uma mentoria com base no ID fornecido"
    )
    @DeleteMapping("/{id}")
    public void deletarMentoria(@PathVariable Long id) {
        mentoriaRepository.deleteById(id);
    }
}
