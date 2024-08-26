package br.com.dio.bootcamp.application.services;

import br.com.dio.bootcamp.application.dtos.MentoriaDto;
import br.com.dio.bootcamp.domain.entities.Mentoria;
import br.com.dio.bootcamp.infra.repository.MentoriaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MentoriaService {

    @Autowired
    private MentoriaRepository mentoriaRepository;

    public Mentoria criarMentoria(MentoriaDto dto) {
        Mentoria mentoria = new Mentoria(dto);
        return mentoriaRepository.save(mentoria);
    }

    public Iterable<Mentoria> obterMentorias() {
        return mentoriaRepository.findAll();
    }

    public Mentoria obterMentoriaPorId(Long id) {
        return mentoriaRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Mentoria não encontrado"));
    }

    public Mentoria atualizarMentoria(Long id, MentoriaDto dto) {
        Mentoria mentoriaAtualizada = new Mentoria(dto);
        Mentoria mentoria = mentoriaRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Mentoria não encontrado"));

        mentoria.setTitulo(mentoriaAtualizada.getTitulo());
        mentoria.setDescricao(mentoriaAtualizada.getDescricao());
        mentoria.setData(mentoriaAtualizada.getData());

        return mentoriaRepository.save(mentoria);
    }

    public void excluirMentoria(Long id) {
        mentoriaRepository.deleteById(id);
    }
}
