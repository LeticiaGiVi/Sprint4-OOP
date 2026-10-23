package br.com.manutencao.repository;

import br.com.manutencao.model.IntervencaoOperacional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IntervencaoRepository extends JpaRepository<IntervencaoOperacional, Long> {
    List<IntervencaoOperacional> findByOperadorContainingIgnoreCase(String operador);
}
