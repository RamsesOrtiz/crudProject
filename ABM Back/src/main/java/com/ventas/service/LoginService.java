package com.ventas.service;

import com.ventas.dao.ILoginDao;
import com.ventas.exceptions.NotFoundException;
import com.ventas.model.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements ILoginService{

    @Autowired
    private ILoginDao iLoginDao;

    private static final String LOGIN_ERROR = "Error: username/password not found";

    @Override
    public boolean login(Login login) throws NotFoundException {

        try {
            return iLoginDao.login(login);
        } catch (NotFoundException e){
            throw new NotFoundException(LOGIN_ERROR, HttpStatus.NOT_FOUND);
        }
    }
}
