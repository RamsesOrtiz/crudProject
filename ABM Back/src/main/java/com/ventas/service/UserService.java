package com.ventas.service;

import com.ventas.dao.IUserDao;
import com.ventas.exceptions.NotFoundException;
import com.ventas.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService{

    @Autowired
    private IUserDao iUserDao;

    private static final String USER_NOT_FOUND_MESSAGE = "User not found with ID: ";

    @Override
    public List<Usuario> getAllUsers() {

        return iUserDao.getAllUsers();
    }

    @Override
    public Usuario getUserById(int idUser) throws NotFoundException {

        Usuario usuario;

        try {
            usuario = iUserDao.getUserById(idUser);
        } catch (NotFoundException e){
            throw new NotFoundException(USER_NOT_FOUND_MESSAGE + idUser, HttpStatus.NOT_FOUND);
        }

        return usuario;
    }

    @Override
    public boolean addUser(Usuario usuario) {

        iUserDao.create(usuario);

        return true;
    }

    @Override
    public void updateUser(Usuario usuario) throws NotFoundException {

        iUserDao.update(usuario);
    }

    @Override
    public void deleteUser(int idUser) throws NotFoundException {

        iUserDao.delete(idUser);
    }
}
