package org.example.bar1.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import java.time.LocalDateTime;

@Data
public class PedidoBar {
    @Id
    private Long id;
    private String producto;
    private int cantidad;
    private double precioUnitario;
    private EstadoPedido estado;
    private Long mesaId;

    // Getters y Setters
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public String getProducto() {return producto;}
    public void setProducto(String producto) {this.producto = producto;}
    public int getCantidad() {return cantidad;}
    public void setCantidad(int cantidad) {this.cantidad = cantidad;}
    public double getPrecioUnitario() {return precioUnitario;}
    public void setPrecioUnitario(double precioUnitario) {this.precioUnitario = precioUnitario;}
    public EstadoPedido getEstado() {return estado;}
    public void setEstado(EstadoPedido estado) {
        if (estado == null) {
            throw new IllegalArgumentException("Estado no puede ser nulo");
        }
        this.estado = estado;
    }
    public Long getMesaId() {return mesaId;}
    public void setMesaId(Long mesaId) {this.mesaId = mesaId;}

}
