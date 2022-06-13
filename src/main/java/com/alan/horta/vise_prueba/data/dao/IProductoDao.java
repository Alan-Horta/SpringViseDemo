package com.alan.horta.vise_prueba.data.dao;

import com.alan.horta.vise_prueba.data.models.producto.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IProductoDao extends JpaRepository<Producto, Long> {
    List<Producto> findByMarcaContainingIgnoreCase(String marca);

    List<Producto> findByPrecioGreaterThanEqual(Double precio);

    List<Producto> findByMarcaContainingIgnoreCaseAndPrecioGreaterThanEqual(String marca, Double precio);
}
