package com.ventas.service;

import com.ventas.exceptions.NotFoundException;
import com.ventas.model.Producto;

import java.util.List;

public interface IProductService {

    List<Producto> getAllProducts();

    Producto getProductById(int idProduct) throws NotFoundException;

    boolean addProduct(Producto producto);

    void updateProduct(Producto producto) throws NotFoundException;

    void deleteProduct(int idProduct) throws NotFoundException;
}
