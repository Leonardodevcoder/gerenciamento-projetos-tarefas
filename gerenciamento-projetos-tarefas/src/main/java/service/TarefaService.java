package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import dto.TarefaDTO;
import model.Tarefa;
import repository.ProjetoRepository;
import repository.TarefaRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;
    @Autowired
    private ProjetoRepository projetoRepository;

    public List<TarefaDTO> listarPorProjeto(Long projetoId) {
        return tarefaRepository.findByProjetoId(projetoId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public TarefaDTO salvar(TarefaDTO tarefaDTO, Long projetoId) {
        Tarefa tarefa = convertToEntity(tarefaDTO);
        tarefa.setProjeto(projetoRepository.findById(projetoId).orElse(null));
        tarefa = tarefaRepository.save(tarefa);
        return convertToDTO(tarefa);
    }

    public void excluir(Long id) {
        tarefaRepository.deleteById(id);
    }

    private TarefaDTO convertToDTO(Tarefa tarefa) {
        TarefaDTO tarefaDTO = new TarefaDTO();
        copyPropertiesToDTO(tarefa, tarefaDTO);
        return tarefaDTO;
    }

    private Tarefa convertToEntity(TarefaDTO tarefaDTO) {
        Tarefa tarefa = new Tarefa();
        copyPropertiesToEntity(tarefaDTO, tarefa);
        return tarefa;
    }

    private void copyPropertiesToDTO(Tarefa tarefa, TarefaDTO tarefaDTO) {
        tarefaDTO.setId(tarefa.getId());
        tarefaDTO.setTitulo(tarefa.getTitulo());
        tarefaDTO.setDescricao(tarefa.getDescricao());
        tarefaDTO.setPrioridade(tarefa.getPrioridade());
        tarefaDTO.setEstimativaHoras(tarefa.getEstimativaHoras());
    }

    private void copyPropertiesToEntity(TarefaDTO tarefaDTO, Tarefa tarefa) {
        tarefa.setId(tarefaDTO.getId());
        tarefa.setTitulo(tarefaDTO.getTitulo());
        tarefa.setDescricao(tarefaDTO.getDescricao());
        tarefa.setPrioridade(tarefaDTO.getPrioridade());
        tarefa.setEstimativaHoras(tarefaDTO.getEstimativaHoras());
    }
}