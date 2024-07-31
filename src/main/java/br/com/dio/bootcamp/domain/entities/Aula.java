package br.com.dio.bootcamp.domain.entities;

import br.com.dio.bootcamp.domain.enums.Materia;
import jakarta.persistence.*;

@Entity
@Table(name = "aulas")
public class Aula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descricao;
    private Integer duracao;
    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;
    private Materia materia;
    @ManyToOne
    @JoinColumn(name = "professor_id")
    private Professor professor;

    public Aula() {
    }

    public Aula(String titulo, String descricao, Integer duracao, Curso curso, Materia materia, Professor professor) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.duracao = duracao;
        this.curso = curso;
        this.materia = materia;
        this.professor = professor;
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

    public Integer getDuracao() {
        return duracao;
    }

    public void setDuracao(Integer duracao) {
        this.duracao = duracao;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
}
