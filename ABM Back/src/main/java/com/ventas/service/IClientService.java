package com.ventas.service;

import java.util.List;

import com.ventas.exceptions.NotFoundException;
import com.ventas.model.Cliente;


public interface IClientService {

    List<Cliente> getAllClients();

    Cliente getClientById(int id_cliente) throws NotFoundException;

    boolean addClient(Cliente cliente);

    void updateClient(Cliente cliente) throws NotFoundException;

    void deleteClient(int id_cliente) throws NotFoundException;
}