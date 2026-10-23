package br.com.manutencao.model;

import jakarta.persistence.*;

@Entity
@Table(name = "t_motiva_trecho")
public class TrechosRodovia {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_TRECHO_GEN")
    @SequenceGenerator(name = "SQ_TRECHO_GEN", sequenceName = "SQ_TRECHO", allocationSize = 1)
    private Long id;

    private Integer kms;

    @Column(name = "nivel_vegetacao")
    private Double nivelVegetacao;

    @Column(name = "trecho_umido")
    private Double trechoUmido;

    @Column(name = "nivel_luz")
    private Double nivelLuz;

    // Construtor padrão obrigatório pelo JPA
    public TrechosRodovia() {
    }

    public TrechosRodovia(Integer kms, Double nivelVegetacao, Double trechoUmido, Double nivelLuz) {
        this.kms = kms;
        this.nivelVegetacao = nivelVegetacao;
        this.trechoUmido = trechoUmido;
        this.nivelLuz = nivelLuz;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getKms() {
        return kms;
    }

    public void setKms(Integer kms) {
        this.kms = kms;
    }

    public Double getNivelVegetacao() {
        return nivelVegetacao;
    }

    public void setNivelVegetacao(Double nivelVegetacao) {
        this.nivelVegetacao = nivelVegetacao;
    }

    public Double getTrechoUmido() {
        return trechoUmido;
    }

    public void setTrechoUmido(Double trechoUmido) {
        this.trechoUmido = trechoUmido;
    }

    public Double getNivelLuz() {
        return nivelLuz;
    }

    public void setNivelLuz(Double nivelLuz) {
        this.nivelLuz = nivelLuz;
    }
}