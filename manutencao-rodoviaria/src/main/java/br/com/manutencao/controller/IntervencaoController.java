package br.com.manutencao.controller;

import br.com.manutencao.model.IntervencaoOperacional;
import br.com.manutencao.service.IntervencaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/intervencoes")
public class IntervencaoController {

    @Autowired
    private IntervencaoService service;

    @GetMapping
    public List<IntervencaoOperacional> listar() { return service.listarTodas(); }

    @GetMapping("/operador/{nome}")
    public List<IntervencaoOperacional> porOperador(@PathVariable String nome) {
        return service.buscarPorOperador(nome);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.buscarPorId(id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /** O campo "tipo" do JSON (PULVERIZACAO ou ROCADA) define a subclasse criada. */
    @PostMapping
    public ResponseEntity<?> registrar(@RequestBody IntervencaoOperacional intervencao) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.registrar(intervencao));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /** Polimorfismo em ação: cada subclasse monta seu próprio resumo. */
    @GetMapping("/{id}/resumo")
    public ResponseEntity<?> resumo(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.buscarPorId(id).executarServico());
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        try {
            service.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
