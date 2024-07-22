package dto;

import java.util.Objects;

public class TarefaDTO {
    private Long id;
    private String titulo;
    private String descricao;
    private String prioridade;
    private int estimativaHoras;

    public TarefaDTO() {
    }

    public TarefaDTO(Long id, String titulo, String descricao, String prioridade, int estimativaHoras) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.prioridade = prioridade;
        this.estimativaHoras = estimativaHoras;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TarefaDTO tarefaDTO = (TarefaDTO) o;
        return estimativaHoras == tarefaDTO.estimativaHoras &&
                Objects.equals(id, tarefaDTO.id) &&
                Objects.equals(titulo, tarefaDTO.titulo) &&
                Objects.equals(descricao, tarefaDTO.descricao) &&
                Objects.equals(prioridade, tarefaDTO.prioridade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titulo, descricao, prioridade, estimativaHoras);
    }

    @Override
    public String toString() {
        return "TarefaDTO{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", prioridade='" + prioridade + '\'' +
                ", estimativaHoras=" + estimativaHoras +
                '}';
    }
}