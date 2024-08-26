package br.com.dio.bootcamp.ui.controller;

import br.com.dio.bootcamp.application.dtos.MentoriaDto;
import br.com.dio.bootcamp.application.services.MentoriaService;
import br.com.dio.bootcamp.domain.entities.Mentoria;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mentoria")
@Tag(name = "Mentoria", description = "Endpoints para gerenciamento de mentorias")
public class MentoriaController {

    @Autowired
    private MentoriaService mentoriaService;

    @Operation(
            summary = "Criar uma nova mentoria",
            description = "Cria uma nova mentoria com base nos dados fornecidos"
    )
    @PostMapping
    public Mentoria criarMentoria(@RequestBody @Valid MentoriaDto dto) {
        return mentoriaService.criarMentoria(dto);
    }

    @Operation(
            summary = "Obter todas as mentorias",
            description = "Retorna uma lista de todas as mentorias cadastradas"
    )
    @GetMapping
    public Iterable<Mentoria> obterMentorias() {
        return mentoriaService.obterMentorias();
    }

    @Operation(
            summary = "Obter mentoria por ID",
            description = "Retorna uma mentoria específica com base no ID fornecido"
    )
    @GetMapping("/{id}")
    public Mentoria obterMentoriaPorId(@PathVariable Long id) {
        return mentoriaService.obterMentoriaPorId(id);
    }

    @Operation(
            summary = "Atualizar mentoria",
            description = "Atualiza uma mentoria existente com base nos dados fornecidos"
    )
    @PutMapping("/{id}")
    public ResponseEntity<Mentoria> atualizarMentoria(@PathVariable Long id, @RequestBody @Valid MentoriaDto dto) {
        Mentoria mentoria = mentoriaService.atualizarMentoria(id, dto);
        return mentoria != null ? ResponseEntity.ok(mentoria) : ResponseEntity.notFound().build();
    }

    @Operation(
            summary = "Deletar mentoria",
            description = "Exclui uma mentoria com base no ID fornecido"
    )
    @DeleteMapping("/{id}")
    public void deletarMentoria(@PathVariable Long id) {
        mentoriaService.excluirMentoria(id);
    }
}
