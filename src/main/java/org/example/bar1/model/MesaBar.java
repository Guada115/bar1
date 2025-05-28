package org.example.bar1.model;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

@Data
public class MesaBar {
    @Id
    private Long id;
    private int capacidad;
    private String ubicacion;
    private EstadoMesa estado;
    private TipoMesa tipo;

    @Column("horario_id")
    private Long horarioId;


    //Getters y Setters
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public int getCapacidad() {return capacidad;}
    public void setCapacidad(int capacidad) {this.capacidad = capacidad;}
    public String getUbicacion() {return ubicacion;}
    public void setUbicacion(String ubicacion) {this.ubicacion = ubicacion;}
    public EstadoMesa getEstado() {return estado;}
    public void setEstado(EstadoMesa estado) {this.estado = estado;}
    public TipoMesa getTipo() {return tipo;}
    public void setTipo(TipoMesa tipo) {this.tipo = tipo;}
    public Long getHorarioId() {return horarioId;}
    public void setHorarioId(Long horarioId) {this.horarioId = horarioId;}
//    public HorarioBar getHorario() {return horario;}
//    public void setHorario(HorarioBar horario) {this.horario = horario;}


}



