package br.com.manutencao.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("ROCADA")
public class RocadaMecanizada extends IntervencaoOperacional {

    private Double areaRocada;
    private Double alturaCorte;
    private String equipamentoUtilizado;

    public RocadaMecanizada() { }

    @Override
    public String executarServico() {
        return "Roçada em " + getDataExecucao() + " por " + getOperador()
                + " | área: " + areaRocada + " m² | altura de corte: " + alturaCorte
                + " cm | equipamento: " + equipamentoUtilizado;
    }

    public Double getAreaRocada() { return areaRocada; }
    public void setAreaRocada(Double areaRocada) { this.areaRocada = areaRocada; }
    public Double getAlturaCorte() { return alturaCorte; }
    public void setAlturaCorte(Double alturaCorte) { this.alturaCorte = alturaCorte; }
    public String getEquipamentoUtilizado() { return equipamentoUtilizado; }
    public void setEquipamentoUtilizado(String equipamentoUtilizado) { this.equipamentoUtilizado = equipamentoUtilizado; }
}
