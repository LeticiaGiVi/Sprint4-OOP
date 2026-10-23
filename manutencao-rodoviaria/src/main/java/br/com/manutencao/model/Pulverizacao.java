package br.com.manutencao.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("PULVERIZACAO")
public class Pulverizacao extends IntervencaoOperacional {

    private String tipoProduto;
    private Double pressao;
    private Double vazao;
    private String tipoBico;

    public Pulverizacao() { }

    @Override
    public String executarServico() {
        return "Pulverização em " + getDataExecucao() + " por " + getOperador()
                + " | produto: " + tipoProduto + " | pressão: " + pressao
                + " | vazão: " + vazao + " | bico: " + tipoBico;
    }

    public String getTipoProduto() { return tipoProduto; }
    public void setTipoProduto(String tipoProduto) { this.tipoProduto = tipoProduto; }
    public Double getPressao() { return pressao; }
    public void setPressao(Double pressao) { this.pressao = pressao; }
    public Double getVazao() { return vazao; }
    public void setVazao(Double vazao) { this.vazao = vazao; }
    public String getTipoBico() { return tipoBico; }
    public void setTipoBico(String tipoBico) { this.tipoBico = tipoBico; }
}
