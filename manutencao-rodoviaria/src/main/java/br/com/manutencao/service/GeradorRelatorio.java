package br.com.manutencao.service;

import br.com.manutencao.model.RelatorioPrioridade;
import br.com.manutencao.model.TrechosRodovia;
import br.com.manutencao.repository.RelatorioRepository;
import br.com.manutencao.repository.TrechoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class GeradorRelatorio {

    @Autowired
    private TrechoRepository trechoRepository;

    @Autowired
    private RelatorioRepository relatorioRepository;

    public List<RelatorioPrioridade> listarTodos() {
        return relatorioRepository.findAllByOrderByDataGeracaoDesc();
    }

    public RelatorioPrioridade buscarPorId(Long id) {
        return relatorioRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Relatório não encontrado: " + id));
    }

    /** Classifica todos os trechos, conta por categoria e grava o relatório. */
    public RelatorioPrioridade gerar() {
        List<TrechosRodovia> trechos = trechoRepository.findAll();
        if (trechos.isEmpty())
            throw new IllegalStateException("Nenhum trecho cadastrado para gerar relatório");

        int urgente = 0, critico = 0, atencao = 0, normal = 0;
        StringBuilder resumo = new StringBuilder();



        RelatorioPrioridade r = new RelatorioPrioridade();
        r.setQtUrgente(urgente);
        r.setQtCritico(critico);
        r.setQtAtencao(atencao);
        r.setQtNormal(normal);
        String texto = resumo.toString();
        r.setResumo(texto.length() > 4000 ? texto.substring(0, 4000) : texto);
        r.setDataGeracao(LocalDateTime.now());
        return relatorioRepository.save(r);
    }
}
