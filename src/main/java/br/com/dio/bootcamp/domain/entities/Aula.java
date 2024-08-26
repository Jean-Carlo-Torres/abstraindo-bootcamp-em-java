package br.com.dio.bootcamp.domain.entities;

import br.com.dio.bootcamp.application.dtos.AulaDto;
import br.com.dio.bootcamp.domain.enums.Materia;
import jakarta.persistence.*;

@Entity
@Table(name = "aulas")
public class Aula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private Integer duracao;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;

    @Enumerated(EnumType.STRING)
    private Materia materia;

    @OneToOne(mappedBy = "aula", cascade = CascadeType.ALL)
    private Professor professor;


    public Aula() {
    }

    public Aula(AulaDto dados) {
        this.titulo = dados.titulo();
        this.descricao = dados.descricao();
        this.duracao = dados.duracao();
        this.curso = dados.curso();
        this.materia = dados.materia();
        this.professor = dados.professor();
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
