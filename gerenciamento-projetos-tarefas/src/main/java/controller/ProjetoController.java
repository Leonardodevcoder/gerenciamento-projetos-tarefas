package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import dto.ProjetoDTO;
import service.ProjetoService;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.List;

@Controller
@Scope("view")
public class ProjetoController {

    @Autowired
    private ProjetoService projetoService;
    private ProjetoDTO projetoDTO;
    private List<ProjetoDTO> projetos;

    @PostConstruct
    public void init() {
        projetoDTO = new ProjetoDTO();
        projetos = projetoService.listarTodos();
    }

    public void salvar() {
        try {
            projetoService.salvar(projetoDTO);
            projetoDTO = new ProjetoDTO();
            projetos = projetoService.listarTodos();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Projeto salvo com sucesso!"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar projeto", e.getMessage()));
        }
    }

    public void editar(ProjetoDTO projeto) {
        this.projetoDTO = projeto;
    }

    public void excluir(Long id) {
        try {
            projetoService.excluir(id);
            projetos = projetoService.listarTodos();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Projeto excluído com sucesso!"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao excluir projeto", e.getMessage()));
        }
    }

  
    public ProjetoDTO getProjetoDTO() {
        return projetoDTO;
    }

    public List<ProjetoDTO> getProjetos() {
        return projetos;
    }
}
