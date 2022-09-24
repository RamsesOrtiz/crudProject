package com.ventas.controller;

import com.ventas.exceptions.NotFoundException;
import com.ventas.model.Producto;
import com.ventas.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("api")
public class ProductController {

    @Autowired
    private IProductService iProductService;

    @CrossOrigin
    @GetMapping("/product/{id}")
    public ResponseEntity<Object> getProductById(@PathVariable("id") Integer idProduct) throws NotFoundException {

        Producto productoResp = iProductService.getProductById(idProduct);

        if (productoResp != null) {
            return new ResponseEntity<>(productoResp, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("Error", "Product ID " + idProduct + " not found"));
        }
    }

    @CrossOrigin
    @GetMapping("/products")
    public ResponseEntity<List<Producto>> getAllProducts() {

        List<Producto> productos = iProductService.getAllProducts();

        return new ResponseEntity<>(productos, HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping(value = "/createProduct", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_XML_VALUE})
    public ResponseEntity<Object> addProduct(@RequestBody Producto producto, UriComponentsBuilder builder) {

        boolean flag = iProductService.addProduct(producto);

        if (!flag) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("Error", "Product not created"));
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("product/{id}").buildAndExpand(producto.getId_producto()).toUri());

        return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonMap("Ok", "Product "
                + producto.getDescripcion() + " successfully created"));
    }

    @CrossOrigin
    @PostMapping("/updateProduct")
    public ResponseEntity<Object> updateClient(@RequestBody Producto producto) throws NotFoundException {

        iProductService.updateProduct(producto);

        return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonMap("Ok", "Product ID "
                + producto.getId_producto() + " successfully updated"));
    }

    @CrossOrigin
    @PostMapping("/deleteProduct/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable("id") Integer idProduct) throws NotFoundException {

        iProductService.deleteProduct(idProduct);

        return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonMap("Ok", "Product ID "
                + idProduct + " successfully deleted"));
    }
}
