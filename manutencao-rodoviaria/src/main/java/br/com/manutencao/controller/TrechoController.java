package br.com.manutencao.controller;

import br.com.manutencao.dto.TrechoPrioridade;
import br.com.manutencao.model.TrechosRodovia;
import br.com.manutencao.service.TrechoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/trechos")
public class TrechoController {

    @Autowired
    private TrechoService service;

    @GetMapping
    public List<TrechosRodovia> listar() { return service.listarTodos(); }

    /** Cada trecho com sua categoria (URGENTE / CRITICO / ATENCAO / NORMAL). */
    @GetMapping("/prioridade")
    public List<TrechoPrioridade> prioridade() { return service.listarComPrioridade(); }

    @GetMapping("/vegetacao/acima/{nivel}")
    public List<TrechosRodovia> vegetacaoAcima(@PathVariable Double nivel) {
        return service.buscarVegetacaoAcima(nivel);
    }

    @GetMapping("/{km}")
    public ResponseEntity<?> buscar(@PathVariable Long km) {
        try {
            return ResponseEntity.ok(service.buscarPorKm(km));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody TrechosRodovia trecho) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(trecho));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{km}")
    public ResponseEntity<?> atualizar(@PathVariable Long km, @RequestBody TrechosRodovia dados) {
        try {
            return ResponseEntity.ok(service.atualizar(km, dados));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /** Atualiza só a vegetação: PATCH /api/trechos/{km}/vegetacao/{nivel} */
    @PatchMapping("/{km}/vegetacao/{nivel}")
    public ResponseEntity<?> atualizarVegetacao(@PathVariable Long km, @PathVariable Double nivel) {
        try {
            return ResponseEntity.ok(service.atualizarVegetacao(km, nivel));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{km}")
    public ResponseEntity<?> deletar(@PathVariable Long km) {
        try {
            service.deletar(km);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
