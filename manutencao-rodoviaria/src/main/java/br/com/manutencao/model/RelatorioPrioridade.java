package br.com.manutencao.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "relatorio_prioridade")
public class RelatorioPrioridade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer qtUrgente;
    private Integer qtCritico;
    private Integer qtAtencao;
    private Integer qtNormal;
    @Column(length = 4000)
    private String resumo;
    private LocalDateTime dataGeracao;

    public RelatorioPrioridade() { }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Integer getQtUrgente() { return qtUrgente; }
    public void setQtUrgente(Integer qtUrgente) { this.qtUrgente = qtUrgente; }
    public Integer getQtCritico() { return qtCritico; }
    public void setQtCritico(Integer qtCritico) { this.qtCritico = qtCritico; }
    public Integer getQtAtencao() { return qtAtencao; }
    public void setQtAtencao(Integer qtAtencao) { this.qtAtencao = qtAtencao; }
    public Integer getQtNormal() { return qtNormal; }
    public void setQtNormal(Integer qtNormal) { this.qtNormal = qtNormal; }
    public String getResumo() { return resumo; }
    public void setResumo(String resumo) { this.resumo = resumo; }
    public LocalDateTime getDataGeracao() { return dataGeracao; }
    public void setDataGeracao(LocalDateTime dataGeracao) { this.dataGeracao = dataGeracao; }
}
