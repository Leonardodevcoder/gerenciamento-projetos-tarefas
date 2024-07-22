package model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
public class Projeto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 100)
    private String titulo;

    @NotBlank
    @Size(max = 255)
    private String descricao;

    @NotNull
    @Temporal(TemporalType.DATE)
    private Date dataInicio;

    @OneToMany(mappedBy = "projeto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Tarefa> tarefas;

    public Projeto() {
    }

    public Projeto(Long id, String titulo, String descricao, Date dataInicio, List<Tarefa> tarefas) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.tarefas = tarefas;
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

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public List<Tarefa> getTarefas() {
        return tarefas;
    }

    public void setTarefas(List<Tarefa> tarefas) {
        this.tarefas = tarefas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Projeto projeto = (Projeto) o;
        return Objects.equals(id, projeto.id) &&
                Objects.equals(titulo, projeto.titulo) &&
                Objects.equals(descricao, projeto.descricao) &&
                Objects.equals(dataInicio, projeto.dataInicio) &&
                Objects.equals(tarefas, projeto.tarefas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titulo, descricao, dataInicio, tarefas);
    }

    @Override
    public String toString() {
        return "Projeto{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", dataInicio=" + dataInicio +
                ", tarefas=" + tarefas +
                '}';
    }
}