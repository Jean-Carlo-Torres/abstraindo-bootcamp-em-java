package br.com.dio.bootcamp.domain.entities;

import br.com.dio.bootcamp.application.dtos.MentoriaDto;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "mentorias")
public class Mentoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private LocalDate data;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;

    public Mentoria() {
    }

    public Mentoria(MentoriaDto dados) {
        this.titulo = dados.titulo();
        this.descricao = dados.descricao();
        this.data = dados.data();
        this.curso = dados.curso();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
}
