package com.alan.horta.vise_prueba.data.services;

import com.alan.horta.vise_prueba.data.dao.IProductoDao;
import com.alan.horta.vise_prueba.data.models.producto.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductoServiceImpl implements IProductoService{

    @Autowired
    private IProductoDao productoDao;

    @Override
    @Transactional(readOnly = true)
    public List<Producto> findAllProductos() {
        return productoDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Producto findProductoById(Long id) {
        return productoDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Producto guardarProducto(Producto producto) {
        return productoDao.save(producto);
    }

    @Override
    @Transactional
    public void borrarProducto(Long id) {
        productoDao.deleteById(id);
    }

    @Override
    @Transactional
    public List<Producto> findMarca(String marca) {
        return productoDao.findByMarcaContainingIgnoreCase(marca);
    }

    @Override
    @Transactional
    public List<Producto> findPrecioAPartir(Double precioMinimo) {
        return productoDao.findByPrecioGreaterThanEqual(precioMinimo);
    }

    @Override
    @Transactional
    public List<Producto> findMarcaYPrecioAPartir(String marca, Double precio) {
        return productoDao.findByMarcaContainingIgnoreCaseAndPrecioGreaterThanEqual(marca,precio);
    }

}
