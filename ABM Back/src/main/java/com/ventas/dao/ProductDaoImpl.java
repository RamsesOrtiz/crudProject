package com.ventas.dao;

import com.ventas.exceptions.NotFoundException;
import com.ventas.model.Producto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public class ProductDaoImpl implements IProductDao{

    @PersistenceContext
    private EntityManager entityManager;

    public void create(Producto producto) {

        entityManager.persist(producto);
    }

    @SuppressWarnings("unchecked")
    @Query(nativeQuery = true)
    public List<Producto> getAllProducts() {

        String hql = "FROM Producto ORDER BY id_producto";

        return (List<Producto>) entityManager.createQuery(hql).getResultList();
    }

    public Producto getProductById(int idProduct) throws NotFoundException {

        return entityManager.find(Producto.class, idProduct);
    }

    public void update(Producto producto) throws NotFoundException {

        Producto productoUpdate = getProductById(producto.getId_producto());

        productoUpdate.setDescripcion(producto.getDescripcion());

        productoUpdate.setPrecio(producto.getPrecio());

        productoUpdate.setCategoria(producto.getCategoria());

        entityManager.flush();
    }

    public void delete(int idProduct) throws NotFoundException {

        entityManager.remove(getProductById(idProduct));
    }
}
