package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import dto.ProjetoDTO;
import dto.TarefaDTO;
import model.Projeto;
import model.Tarefa;
import repository.ProjetoRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProjetoService {

    @Autowired
    private ProjetoRepository projetoRepository;

    public List<ProjetoDTO> listarTodos() {
        return projetoRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ProjetoDTO salvar(ProjetoDTO projetoDTO) {
        Projeto projeto = convertToEntity(projetoDTO);
        projetoRepository.save(projeto);
        return convertToDTO(projeto);
    }

    public void excluir(Long id) {
        projetoRepository.deleteById(id);
    }

    private ProjetoDTO convertToDTO(Projeto projeto) {
        ProjetoDTO projetoDTO = new ProjetoDTO();
        projetoDTO.setId(projeto.getId());
        projetoDTO.setTitulo(projeto.getTitulo());
        projetoDTO.setDescricao(projeto.getDescricao());
        projetoDTO.setDataInicio(projeto.getDataInicio());
        projetoDTO.setTarefas(projeto.getTarefas().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList()));
        return projetoDTO;
    }

    private TarefaDTO convertToDTO(Tarefa tarefa) {
        TarefaDTO tarefaDTO = new TarefaDTO();
        tarefaDTO.setId(tarefa.getId());
        tarefaDTO.setTitulo(tarefa.getTitulo());
        tarefaDTO.setDescricao(tarefa.getDescricao());
        tarefaDTO.setPrioridade(tarefa.getPrioridade());
        tarefaDTO.setEstimativaHoras(tarefa.getEstimativaHoras());
        return tarefaDTO;
    }

    private Projeto convertToEntity(ProjetoDTO projetoDTO) {
        Projeto projeto = new Projeto();
        copyPropertiesToEntity(projetoDTO, projeto);
        return projeto;
    }

    private Tarefa convertToEntity(TarefaDTO tarefaDTO) {
        Tarefa tarefa = new Tarefa();
        copyPropertiesToEntity(tarefaDTO, tarefa);
        return tarefa;
    }

    private void copyPropertiesToEntity(ProjetoDTO projetoDTO, Projeto projeto) {
        projeto.setId(projetoDTO.getId());
        projeto.setTitulo(projetoDTO.getTitulo());
        projeto.setDescricao(projetoDTO.getDescricao());
        projeto.setDataInicio(projetoDTO.getDataInicio());
        projeto.setTarefas(projetoDTO.getTarefas().stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList()));
    }

    private void copyPropertiesToEntity(TarefaDTO tarefaDTO, Tarefa tarefa) {
        tarefa.setId(tarefaDTO.getId());
        tarefa.setTitulo(tarefaDTO.getTitulo());
        tarefa.setDescricao(tarefaDTO.getDescricao());
        tarefa.setPrioridade(tarefaDTO.getPrioridade());
        tarefa.setEstimativaHoras(tarefaDTO.getEstimativaHoras());
    }
}