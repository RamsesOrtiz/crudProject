package com.ventas.dao;

import com.ventas.exceptions.NotFoundException;
import com.ventas.model.Usuario;

import java.util.List;

public interface IUserDao {

    void create(Usuario usuario);

    List<Usuario> getAllUsers();

    Usuario getUserById(int idUser) throws NotFoundException;

    void update(Usuario usuario) throws NotFoundException;

    void delete(int idUser) throws NotFoundException;
}
