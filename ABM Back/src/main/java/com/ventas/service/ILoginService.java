package com.ventas.service;

import com.ventas.exceptions.NotFoundException;
import com.ventas.model.Login;

public interface ILoginService {

    boolean login(Login login) throws NotFoundException;
}
