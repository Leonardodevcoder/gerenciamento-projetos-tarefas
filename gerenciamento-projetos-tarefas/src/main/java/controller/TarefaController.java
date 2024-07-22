package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dto.TarefaDTO;
import service.TarefaService;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.List;

@Controller
@Scope("view")
public class TarefaController implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private TarefaService tarefaService;
    private TarefaDTO tarefaDTO;
    private List<TarefaDTO> tarefas;
    private Long projetoId;

    @PostConstruct
    public void init() {
        tarefaDTO = new TarefaDTO();
    }

    public void listarTarefasPorProjeto(Long projetoId) {
        this.projetoId = projetoId;
        tarefas = tarefaService.listarPorProjeto(projetoId);
    }

    public void salvar() {
        try {
            tarefaService.salvar(tarefaDTO, projetoId);
            tarefaDTO = new TarefaDTO();
            listarTarefasPorProjeto(projetoId); // Atualiza a lista de tarefas
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Tarefa salva com sucesso!"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar tarefa", e.getMessage()));
        }
    }

    public void editar(TarefaDTO tarefa) {
        this.tarefaDTO = tarefa;
    }

    public void excluir(Long id) {
        try {
            tarefaService.excluir(id);
            listarTarefasPorProjeto(projetoId); // Atualiza a lista de tarefas
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Tarefa excluída com sucesso!"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao excluir tarefa", e.getMessage()));
        }
    }


    public TarefaDTO getTarefaDTO() {
        return tarefaDTO;
    }

    public void setTarefaDTO(TarefaDTO tarefaDTO) {
        this.tarefaDTO = tarefaDTO;
    }

    public List<TarefaDTO> getTarefas() {
        return tarefas;
    }

    public void setTarefas(List<TarefaDTO> tarefas) {
        this.tarefas = tarefas;
    }

    public Long getProjetoId() {
        return projetoId;
    }

    public void setProjetoId(Long projetoId) {
        this.projetoId = projetoId;
    }
}