package com.ventas.dao;

import com.ventas.exceptions.NotFoundException;
import com.ventas.model.Login;

public interface ILoginDao {

    boolean login(Login login) throws NotFoundException;
}
