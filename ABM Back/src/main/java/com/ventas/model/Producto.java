package com.ventas.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="producto")
public class Producto implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id_producto")
    private int id_producto;

    @Column(name="descripcion")
    private String descripcion;

    @Column(name="precio")
    private int precio;

    @Column(name="categoria")
    private String categoria;

    public Producto(){};

    public Producto(String description, int price, String category) {
        this.descripcion = description;
        this.precio = price;
        this.categoria = category;
    }

    public Producto(int idProduct, String description, int price, String category) {
        this.id_producto = idProduct;
        this.descripcion = description;
        this.precio = price;
        this.categoria = category;
    }

    public Producto(int idProduct) {
        this.id_producto = idProduct;
    }

    @Id
    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Product{" +
                "idProduct=" + id_producto +
                ", description='" + descripcion + '\'' +
                ", price=" + descripcion +
                ", category='" + categoria + '\'' +
                '}';
    }
}
