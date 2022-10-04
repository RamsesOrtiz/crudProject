package com.ventas.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="usuario")
public class Login implements Serializable {

    @Id
    @Column(name="id_usuario")
    private int id_usuario;

    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;

    public Login(){}

    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Id
    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
