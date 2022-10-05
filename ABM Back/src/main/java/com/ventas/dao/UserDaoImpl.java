package com.ventas.dao;

import com.ventas.exceptions.NotFoundException;
import com.ventas.model.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public class UserDaoImpl implements IUserDao{

    @PersistenceContext
    private EntityManager entityManager;

    public void create(Usuario usuario) {

        entityManager.persist(usuario);
    }

    @SuppressWarnings("unchecked")
    @Query(nativeQuery = true)
    public List<Usuario> getAllUsers() {

        String hql = "FROM Usuario ORDER BY id_usuario";

        return (List<Usuario>) entityManager.createQuery(hql).getResultList();
    }

    public Usuario getUserById(int idUser) throws NotFoundException {

        return entityManager.find(Usuario.class, idUser);
    }

    public void update(Usuario usuario) throws NotFoundException {

        Usuario userUpdate = getUserById(usuario.getId_usuario());

        userUpdate.setNombre(usuario.getNombre());

        userUpdate.setApellido(usuario.getApellido());

        userUpdate.setUsername(usuario.getUsername());

        userUpdate.setPassword(usuario.getPassword());

        entityManager.flush();
    }

    public void delete(int idUser) throws NotFoundException {

        entityManager.remove(getUserById(idUser));
    }

}
