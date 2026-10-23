package br.com.manutencao.repository;

import br.com.manutencao.model.RelatorioPrioridade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RelatorioRepository extends JpaRepository<RelatorioPrioridade, Long> {
    List<RelatorioPrioridade> findAllByOrderByDataGeracaoDesc();
}
