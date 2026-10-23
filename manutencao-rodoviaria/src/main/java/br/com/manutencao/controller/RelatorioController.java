package br.com.manutencao.controller;

import br.com.manutencao.model.RelatorioPrioridade;
import br.com.manutencao.service.GeradorRelatorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/relatorios")
public class RelatorioController {

    @Autowired
    private GeradorRelatorio gerador;

    @GetMapping
    public List<RelatorioPrioridade> listar() { return gerador.listarTodos(); }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(gerador.buscarPorId(id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /** Gera e grava um novo relatório de prioridade com base nos trechos atuais. */
    @PostMapping
    public ResponseEntity<?> gerar() {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(gerador.gerar());
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}
