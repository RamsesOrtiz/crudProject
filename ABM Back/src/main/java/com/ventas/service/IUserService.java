package com.ventas.service;

import com.ventas.exceptions.NotFoundException;
import com.ventas.model.Usuario;

import java.util.List;

public interface IUserService {

    List<Usuario> getAllUsers();

    Usuario getUserById(int idUser) throws NotFoundException;

    boolean addUser(Usuario usuario);

    void updateUser(Usuario usuario) throws NotFoundException;

    void deleteUser(int idUser) throws NotFoundException;
}
