package br.com.manutencao.service;

import br.com.manutencao.model.IntervencaoOperacional;
import br.com.manutencao.model.Pulverizacao;
import br.com.manutencao.model.RocadaMecanizada;
import br.com.manutencao.repository.IntervencaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class IntervencaoService {

    @Autowired
    private IntervencaoRepository repository;

    public List<IntervencaoOperacional> listarTodas() { return repository.findAll(); }

    public IntervencaoOperacional buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Intervenção não encontrada: " + id));
    }

    public List<IntervencaoOperacional> buscarPorOperador(String operador) {
        return repository.findByOperadorContainingIgnoreCase(operador);
    }

    public IntervencaoOperacional registrar(IntervencaoOperacional intervencao) {
        intervencao.setId(null);
        validar(intervencao);
        return repository.save(intervencao);
    }

    public IntervencaoOperacional atualizar(Long id, IntervencaoOperacional dados) {
        IntervencaoOperacional atual = buscarPorId(id);

        if (!atual.getClass().equals(dados.getClass()))
            throw new IllegalArgumentException(
                    "O tipo da intervenção não pode ser alterado (use o mesmo \"tipo\" do registro)");

        validar(dados);

        atual.setDataExecucao(dados.getDataExecucao());
        atual.setOperador(dados.getOperador());

        if (atual instanceof Pulverizacao pAtual && dados instanceof Pulverizacao pNovo) {
            pAtual.setTipoProduto(pNovo.getTipoProduto());
            pAtual.setPressao(pNovo.getPressao());
            pAtual.setVazao(pNovo.getVazao());
            pAtual.setTipoBico(pNovo.getTipoBico());
        } else if (atual instanceof RocadaMecanizada rAtual && dados instanceof RocadaMecanizada rNovo) {
            rAtual.setAreaRocada(rNovo.getAreaRocada());
            rAtual.setAlturaCorte(rNovo.getAlturaCorte());
            rAtual.setEquipamentoUtilizado(rNovo.getEquipamentoUtilizado());
        }

        return repository.save(atual);
    }

    public void deletar(Long id) {
        if (!repository.existsById(id))
            throw new NoSuchElementException("Intervenção não encontrada: " + id);
        repository.deleteById(id);
    }

    private void validar(IntervencaoOperacional i) {
        if (i.getOperador() == null || i.getOperador().isBlank())
            throw new IllegalArgumentException("Operador é obrigatório");
        if (i.getDataExecucao() == null)
            throw new IllegalArgumentException("Data de execução é obrigatória");

        if (i instanceof Pulverizacao p) {
            if (p.getTipoProduto() == null || p.getTipoProduto().isBlank())
                throw new IllegalArgumentException("Tipo de produto é obrigatório");
            if (p.getPressao() == null || p.getPressao() <= 0)
                throw new IllegalArgumentException("Pressão inválida");
            if (p.getVazao() == null || p.getVazao() <= 0)
                throw new IllegalArgumentException("Vazão inválida");
            if (p.getTipoBico() == null || p.getTipoBico().isBlank())
                throw new IllegalArgumentException("Tipo de bico é obrigatório");
        } else if (i instanceof RocadaMecanizada r) {
            if (r.getAreaRocada() == null || r.getAreaRocada() <= 0)
                throw new IllegalArgumentException("Área roçada inválida");
            if (r.getAlturaCorte() == null || r.getAlturaCorte() <= 0)
                throw new IllegalArgumentException("Altura de corte inválida");
            if (r.getEquipamentoUtilizado() == null || r.getEquipamentoUtilizado().isBlank())
                throw new IllegalArgumentException("Equipamento é obrigatório");
        }
    }
}