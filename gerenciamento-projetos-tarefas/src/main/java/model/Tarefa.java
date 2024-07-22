package model;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.util.Objects;

@Entity
public class Tarefa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 100)
    private String titulo;

    @NotBlank
    @Size(max = 255)
    private String descricao;

    @NotBlank
    private String prioridade;

    @Min(1)
    private int estimativaHoras;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "projeto_id")
    private Projeto projeto;

    public Tarefa() {
    }

    public Tarefa(Long id, String titulo, String descricao, String prioridade, int estimativaHoras, Projeto projeto) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.prioridade = prioridade;
        this.estimativaHoras = estimativaHoras;
        this.projeto = projeto;
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

    public String getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }

    public int getEstimativaHoras() {
        return estimativaHoras;
    }

    public void setEstimativaHoras(int estimativaHoras) {
        this.estimativaHoras = estimativaHoras;
    }

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tarefa tarefa = (Tarefa) o;
        return estimativaHoras == tarefa.estimativaHoras &&
                Objects.equals(id, tarefa.id) &&
                Objects.equals(titulo, tarefa.titulo) &&
                Objects.equals(descricao, tarefa.descricao) &&
                Objects.equals(prioridade, tarefa.prioridade) &&
                Objects.equals(projeto, tarefa.projeto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titulo, descricao, prioridade, estimativaHoras, projeto);
    }

    @Override
    public String toString() {
        return "Tarefa{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", prioridade='" + prioridade + '\'' +
                ", estimativaHoras=" + estimativaHoras +
                ", projeto=" + projeto +
                '}';
    }
}