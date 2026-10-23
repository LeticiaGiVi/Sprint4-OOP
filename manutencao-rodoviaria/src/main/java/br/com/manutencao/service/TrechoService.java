package br.com.manutencao.service;

import br.com.manutencao.dto.TrechoPrioridade;
import br.com.manutencao.model.TrechosRodovia;
import br.com.manutencao.repository.TrechoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TrechoService {

    @Autowired
    private TrechoRepository repository;

    public List<TrechosRodovia> listarTodos() { return repository.findAll(); }

    public TrechosRodovia buscarPorKm(Long km) {
        return repository.findById(km).orElseThrow(() ->
                new NoSuchElementException("Trecho não encontrado: KM " + km));
    }

    public List<TrechosRodovia> buscarVegetacaoAcima(Double nivel) {
        return repository.findByNivelVegetacaoGreaterThan(nivel);
    }

    /** Lista cada trecho já com a categoria de prioridade (antes impressa no console). */
    public List<TrechoPrioridade> listarComPrioridade() {
        return repository.findAll().stream()
                .map(t -> new TrechoPrioridade( t.getNivelVegetacao(),
                        t.getTrechoUmido(), t.getNivelLuz()))
                .toList();
    }

    public TrechosRodovia criar(TrechosRodovia trecho) {
        validar(trecho);
        return repository.save(trecho);
    }

    public TrechosRodovia atualizar(Long km, TrechosRodovia dados) {
        TrechosRodovia atual = buscarPorKm(km);
        validar(dados);
        atual.setNivelVegetacao(dados.getNivelVegetacao());
        atual.setTrechoUmido(dados.getTrechoUmido());
        atual.setNivelLuz(dados.getNivelLuz());
        return repository.save(atual);
    }

    /** Equivalente ao antigo update(nivelVegetacao, km) do DAO. */
    public TrechosRodovia atualizarVegetacao(Long km, Double nivelVegetacao) {
        if (nivelVegetacao == null || nivelVegetacao < 0)
            throw new IllegalArgumentException("Nível de vegetação inválido");
        TrechosRodovia atual = buscarPorKm(km);
        atual.setNivelVegetacao(nivelVegetacao);
        return repository.save(atual);
    }

    public void deletar(Long km) {
        if (!repository.existsById(km))
            throw new NoSuchElementException("Trecho não encontrado: KM " + km);
        repository.deleteById(km);
    }

    private void validar(TrechosRodovia t) {
        if (t.getNivelVegetacao() == null || t.getNivelVegetacao() < 0)
            throw new IllegalArgumentException("Nível de vegetação inválido");
        if (t.getTrechoUmido() == null || t.getTrechoUmido() < 0 || t.getTrechoUmido() > 100)
            throw new IllegalArgumentException("Umidade deve estar entre 0 e 100");
        if (t.getNivelLuz() == null || t.getNivelLuz() < 0)
            throw new IllegalArgumentException("Nível de luminosidade inválido");
    }
}
