package dto;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class ProjetoDTO {
    private Long id;
    private String titulo;
    private String descricao;
    private Date dataInicio;
    private List<TarefaDTO> tarefas;

    public ProjetoDTO() {
    }

    public ProjetoDTO(Long id, String titulo, String descricao, Date dataInicio, List<TarefaDTO> tarefas) {
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

    public List<TarefaDTO> getTarefas() {
        return tarefas;
    }

    public void setTarefas(List<TarefaDTO> tarefas) {
        this.tarefas = tarefas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjetoDTO that = (ProjetoDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(titulo, that.titulo) &&
                Objects.equals(descricao, that.descricao) &&
                Objects.equals(dataInicio, that.dataInicio) &&
                Objects.equals(tarefas, that.tarefas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titulo, descricao, dataInicio, tarefas);
    }

    @Override
    public String toString() {
        return "ProjetoDTO{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", dataInicio=" + dataInicio +
                ", tarefas=" + tarefas +
                '}';
    }
}