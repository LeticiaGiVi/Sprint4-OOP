package br.com.manutencao.repository;

import br.com.manutencao.model.EquipeManutencao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipeRepository extends JpaRepository<EquipeManutencao, Long> {
    List<EquipeManutencao> findByFuncaoIgnoreCase(String funcao);
}
