package br.com.manutencao.controller;

import br.com.manutencao.model.EquipeManutencao;
import br.com.manutencao.service.EquipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/equipes")
public class EquipeController {

    @Autowired
    private EquipeService service;

    @GetMapping
    public List<EquipeManutencao> listar() { return service.listarTodas(); }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.buscarPorId(id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/funcao/{funcao}")
    public List<EquipeManutencao> porFuncao(@PathVariable String funcao) {
        return service.buscarPorFuncao(funcao);
    }

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody EquipeManutencao equipe) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(equipe));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody EquipeManutencao dados) {
        try {
            return ResponseEntity.ok(service.atualizar(id, dados));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /** Atualiza só o nº de membros: PATCH /api/equipes/{id}/membros/{quantidade} */
    @PatchMapping("/{id}/membros/{quantidade}")
    public ResponseEntity<?> atualizarMembros(@PathVariable Long id, @PathVariable Integer quantidade) {
        try {
            return ResponseEntity.ok(service.atualizarMembros(id, quantidade));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
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
