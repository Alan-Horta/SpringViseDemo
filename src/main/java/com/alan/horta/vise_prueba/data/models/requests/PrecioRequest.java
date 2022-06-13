package com.alan.horta.vise_prueba.data.models.requests;

import javax.persistence.Entity;
import java.io.Serializable;

public class PrecioRequest implements Serializable {
    private Double precioNecesario;
    private String marca;

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Double getPrecioNecesario() {
        return precioNecesario;
    }

    public void setPrecioNecesario(Double precioNecesario) {
        this.precioNecesario = precioNecesario;
    }
}
