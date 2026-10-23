package br.com.manutencao.model;

import jakarta.persistence.*;

@Entity
@Table(name = "equipe")
public class EquipeManutencao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_EQUIPE_GEN")
    @SequenceGenerator(name = "SEQ_EQUIPE_GEN", sequenceName = "SEQ_SPRINT", allocationSize = 1)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false)
    private Integer membros;

    @Column(length = 100)
    private String funcao;

    // Construtor padrão obrigatório pelo JPA
    public EquipeManutencao() {
    }

    // Construtor útil para instanciar sem ID
    public EquipeManutencao(String nome, Integer membros, String funcao) {
        this.nome = nome;
        this.membros = membros;
        this.funcao = funcao;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getMembros() {
        return membros;
    }

    public void setMembros(Integer membros) {
        this.membros = membros;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }
}