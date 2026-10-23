package br.com.manutencao.repository;

import br.com.manutencao.model.TrechosRodovia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrechoRepository extends JpaRepository<TrechosRodovia, Long> {
    List<TrechosRodovia> findByNivelVegetacaoGreaterThan(Double nivel);
}
