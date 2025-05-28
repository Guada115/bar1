package org.example.bar1.model;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalTime;

@Data
public class HorarioBar {
    @Id
    private Long id;
    private LocalTime horaApertura;
    private LocalTime horaCierre;
    private DiaSemana dia;
    private boolean horaFeliz;

    // Getters y Setters
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public LocalTime getHoraApertura() {return horaApertura;}
    public void setHoraApertura(LocalTime horaApertura) {this.horaApertura = horaApertura;}
    public LocalTime getHoraCierre() {return horaCierre;}
    public void setHoraCierre(LocalTime horaCierre) {this.horaCierre = horaCierre;}
    public DiaSemana getDia() {return dia;}
    public void setDia(DiaSemana dia) {this.dia = dia;}
    public boolean isHoraFeliz() {return horaFeliz;}
    public void setHoraFeliz(boolean horaFeliz) {this.horaFeliz = horaFeliz;}

}
