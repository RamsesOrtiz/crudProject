package com.ventas.dao;

import com.ventas.exceptions.NotFoundException;
import com.ventas.model.Producto;

import java.util.List;

public interface IProductDao {

    void create(Producto producto);

    List<Producto> getAllProducts();

    Producto getProductById(int idProduct) throws NotFoundException;

    void update(Producto producto) throws NotFoundException;

    void delete(int idProduct) throws NotFoundException;
}
