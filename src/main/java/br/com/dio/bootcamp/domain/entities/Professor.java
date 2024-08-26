package br.com.dio.bootcamp.domain.entities;

import br.com.dio.bootcamp.application.dtos.ProfessorDto;
import jakarta.persistence.*;

@Entity
@Table(name = "professores")
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String especialidade;

    @OneToOne
    @JoinColumn(name = "aula_id")
    private Aula aula;

    public Professor() {
    }

    public Professor(ProfessorDto dados) {
        this.nome = dados.nome();
        this.especialidade = dados.especialidade();
        this.aula = dados.aula();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public Aula getAula() {
        return aula;
    }

    public void setAula(Aula aula) {
        this.aula = aula;
    }
}
