package org.example.bar1.model;

import org.springframework.data.annotation.Id;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

@Table("cliente_horario")
public class ClienteHorario {
    @Id
    private Long id;
    private Long clienteId;
    private Long horarioId;

    // Getters y Setters
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public Long getClienteId() {return clienteId;}
    public void setClienteId(Long clienteId) {this.clienteId = clienteId;}
    public Long getHorarioId() {return horarioId;}
    public void setHorarioId(Long horarioId) {this.horarioId = horarioId;}


}
