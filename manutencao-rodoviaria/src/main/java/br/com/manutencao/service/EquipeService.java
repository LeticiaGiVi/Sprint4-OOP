package br.com.manutencao.service;

import br.com.manutencao.model.EquipeManutencao;
import br.com.manutencao.repository.EquipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EquipeService {

    @Autowired
    private EquipeRepository repository;

    public List<EquipeManutencao> listarTodas() { return repository.findAll(); }

    public EquipeManutencao buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Equipe não encontrada: " + id));
    }

    public List<EquipeManutencao> buscarPorFuncao(String funcao) {
        return repository.findByFuncaoIgnoreCase(funcao);
    }

    public EquipeManutencao criar(EquipeManutencao equipe) {
        equipe.setId(null);
        validar(equipe);
        return repository.save(equipe);
    }

    public EquipeManutencao atualizar(Long id, EquipeManutencao dados) {
        EquipeManutencao atual = buscarPorId(id);
        validar(dados);
        atual.setNome(dados.getNome());
        atual.setMembros(dados.getMembros());
        atual.setFuncao(dados.getFuncao());
        return repository.save(atual);
    }

    /** Equivalente ao antigo update(membros, id) do DAO. */
    public EquipeManutencao atualizarMembros(Long id, Integer membros) {
        if (membros == null || membros <= 0)
            throw new IllegalArgumentException("Número de membros deve ser maior que zero");
        EquipeManutencao atual = buscarPorId(id);
        atual.setMembros(membros);
        return repository.save(atual);
    }

    public void deletar(Long id) {
        if (!repository.existsById(id))
            throw new NoSuchElementException("Equipe não encontrada: " + id);
        repository.deleteById(id);
    }

    private void validar(EquipeManutencao e) {
        if (e.getNome() == null || e.getNome().isBlank())
            throw new IllegalArgumentException("Nome da equipe é obrigatório");
        if (e.getMembros() == null || e.getMembros() <= 0)
            throw new IllegalArgumentException("Número de membros deve ser maior que zero");
        if (e.getFuncao() == null || e.getFuncao().isBlank())
            throw new IllegalArgumentException("Função da equipe é obrigatória");
    }
}
