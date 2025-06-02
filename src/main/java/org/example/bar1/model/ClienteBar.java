package org.example.bar1.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class ClienteBar {
    @Id
    private Long id;
    private String nombre;
    private String telefono;
    private TipoCliente tipoCliente;
    private String bebidaFavorita;

    //getters y setters generados
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public String getTelefono() {return telefono;}
    public void setTelefono(String telefono) {this.telefono = telefono;}
    public TipoCliente getTipoCliente() {return tipoCliente;}
    public void setTipoCliente(TipoCliente tipoCliente) {this.tipoCliente = tipoCliente;}
    public String getBebidaFavorita() {return bebidaFavorita;}
    public void setBebidaFavorita(String bebidaFavorita) {this.bebidaFavorita = bebidaFavorita;}




}
