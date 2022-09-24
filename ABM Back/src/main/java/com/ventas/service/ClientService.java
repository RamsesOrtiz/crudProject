package com.ventas.service;

import com.ventas.dao.IClientDao;
import com.ventas.exceptions.NotFoundException;
import com.ventas.model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService implements IClientService {

    @Autowired
    private IClientDao clienteDao;

    private static final String CLIENT_NOT_FOUND_MESSAGE = "Client not found with ID: ";

    @Override
    public List<Cliente> getAllClients() {

        return clienteDao.getAllClients();
    }

    @Override
    public Cliente getClientById(int id_cliente) throws NotFoundException {

        Cliente cliente;

        try {
            cliente = clienteDao.getClientById(id_cliente);
        } catch (NotFoundException e){
            throw new NotFoundException(CLIENT_NOT_FOUND_MESSAGE + id_cliente, HttpStatus.NOT_FOUND);
        }

        return cliente;
    }

    @Override
    public boolean addClient(Cliente cliente) {

        clienteDao.create(cliente);

        return true;
    }

    @Override
    public void updateClient(Cliente cliente) throws NotFoundException {

        clienteDao.update(cliente);
    }

    @Override
    public void deleteClient(int id_cliente) throws NotFoundException {

        clienteDao.delete(id_cliente);
    }
}
