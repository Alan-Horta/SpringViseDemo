package com.alan.horta.vise_prueba.data.models.producto;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "product")
public class Producto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String nombre;
    @Column(name = "brand", nullable = false)
    private String marca;
    @Column(name = "madein")
    private String hechoEn;
    @Column(name = "price", nullable = false)
    private Double precio;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getHechoEn() {
        return hechoEn;
    }

    public void setHechoEn(String hechoEn) {
        this.hechoEn = hechoEn;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
}
