package com.ventas.service;

import com.ventas.dao.IProductDao;
import com.ventas.exceptions.NotFoundException;
import com.ventas.model.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService{

    @Autowired
    private IProductDao productDao;

    private static final String PRODUCT_NOT_FOUND_MESSAGE = "Product not found with ID: ";

    @Override
    public List<Producto> getAllProducts() {

        return productDao.getAllProducts();
    }

    @Override
    public Producto getProductById(int idProduct) throws NotFoundException {

        Producto producto;

        try {
            producto = productDao.getProductById(idProduct);
        } catch (NotFoundException e){
            throw new NotFoundException(PRODUCT_NOT_FOUND_MESSAGE + idProduct, HttpStatus.NOT_FOUND);
        }

        return producto;
    }

    @Override
    public boolean addProduct(Producto producto) {

        productDao.create(producto);

        return true;
    }

    @Override
    public void updateProduct(Producto producto) throws NotFoundException {

        productDao.update(producto);
    }

    @Override
    public void deleteProduct(int idProduct) throws NotFoundException {

        productDao.delete(idProduct);
    }
}
