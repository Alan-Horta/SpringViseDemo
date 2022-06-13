package com.alan.horta.vise_prueba.data.services;

import com.alan.horta.vise_prueba.data.models.producto.Producto;

import java.util.List;

public interface IProductoService {

    public List<Producto> findAllProductos();

    public Producto findProductoById(Long id);

    public Producto guardarProducto(Producto producto);

    public void borrarProducto(Long id);

    public List<Producto> findMarca(String marca);

    public List<Producto> findPrecioAPartir(Double precioMinimo);

    public List<Producto> findMarcaYPrecioAPartir(String marca, Double precio);

}
