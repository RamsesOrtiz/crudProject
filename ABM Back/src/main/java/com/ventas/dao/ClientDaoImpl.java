package com.ventas.dao;

import com.ventas.exceptions.NotFoundException;
import com.ventas.model.Cliente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public class ClientDaoImpl implements IClientDao {

    @PersistenceContext
    private EntityManager entityManager;

    private static final String CLIENT_NOT_FOUND_MESSAGE = "No se encontro un cliente con el id: ";

    public void create(Cliente cliente) {

        entityManager.persist(cliente);
    }

    @SuppressWarnings("unchecked")
    @Query(nativeQuery = true)
    public List<Cliente> getAllClients() {

        String hql = "FROM Cliente as cli ORDER BY cli.id_cliente";

        return (List<Cliente>) entityManager.createQuery(hql).getResultList();
    }

    public Cliente getClientById(int id_cliente) throws NotFoundException{

        return entityManager.find(Cliente.class, id_cliente);
    }

    public void update(Cliente cliente) throws NotFoundException {

        Cliente clienteUpdate = getClientById(cliente.getId_cliente());

        clienteUpdate.setNombre(cliente.getNombre());

        clienteUpdate.setApellido(cliente.getApellido());

        clienteUpdate.setTelefono(cliente.getTelefono());

        entityManager.flush();
    }

    public void delete(int id_cliente) throws NotFoundException {

        entityManager.remove(getClientById(id_cliente));
    }
}
