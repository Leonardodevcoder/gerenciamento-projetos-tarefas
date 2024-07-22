package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import model.Tarefa;

import java.util.List;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    List<Tarefa> findByProjetoId(Long projetoId);
}