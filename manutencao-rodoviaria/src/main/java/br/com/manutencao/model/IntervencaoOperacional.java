package br.com.manutencao.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;

import java.time.LocalDate;

/**
 * Herança mapeada em UMA tabela (INTERVENCAO), com a coluna "tipo" como discriminador
 * — exatamente como o DAO antigo gravava 'PULVERIZACAO' / 'ROCADA'.
 * No JSON, o campo "tipo" escolhe a subclasse.
 */
@Entity
@Table(name = "intervencao")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo", length = 20)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "tipo")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Pulverizacao.class, name = "PULVERIZACAO"),
        @JsonSubTypes.Type(value = RocadaMecanizada.class, name = "ROCADA")
})
public abstract class IntervencaoOperacional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dataExecucao;
    private String operador;

    /** Substitui o antigo ExecutarServico(Scanner): agora devolve o resumo do serviço. */
    public abstract String executarServico();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDate getDataExecucao() { return dataExecucao; }
    public void setDataExecucao(LocalDate dataExecucao) { this.dataExecucao = dataExecucao; }
    public String getOperador() { return operador; }
    public void setOperador(String operador) { this.operador = operador; }
}
