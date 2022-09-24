package com.ventas.dao;

import com.ventas.exceptions.NotFoundException;
import com.ventas.model.Cliente;

import java.util.List;

public interface IClientDao {

    void create(Cliente cliente);

    List<Cliente> getAllClients();

    Cliente getClientById(int id_cliente) throws NotFoundException;

    void update(Cliente cliente) throws NotFoundException;

    void delete(int id_cliente) throws NotFoundException;
}