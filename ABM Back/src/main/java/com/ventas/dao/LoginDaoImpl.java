package com.ventas.dao;

import com.ventas.model.Login;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Transactional
@Repository
public class LoginDaoImpl implements ILoginDao{

    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    @Query(nativeQuery = true)
    public boolean login(Login login){

        String hql = "FROM Usuario WHERE username='" + login.getUsername()
                + "' AND password='" + login.getPassword() +"'";

        return !entityManager.createQuery(hql).getResultList().isEmpty();
    }
}
