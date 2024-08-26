package br.com.dio.bootcamp.domain.entities;

import br.com.dio.bootcamp.application.dtos.AlunoDto;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "alunos")
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long matricula;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @ManyToMany(mappedBy = "alunos")
    private List<Curso> cursos = new ArrayList<>();

    private Integer xp;

    public Aluno() {
    }

    public Aluno(AlunoDto dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.xp = 0;
    }

    public Long getMatricula() {
        return matricula;
    }

    public void setMatricula(Long matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }

    public Integer getXp() {
        return xp;
    }

    public void setXp(Integer xp) {
        if (xp < 0) {
            this.xp = 0;
        } else {
            this.xp = xp;
        }
    }
}
