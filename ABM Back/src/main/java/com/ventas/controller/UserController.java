package com.ventas.controller;

import com.ventas.exceptions.NotFoundException;
import com.ventas.model.Usuario;
import com.ventas.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("api")
public class UserController {

    @Autowired
    private IUserService iUserService;

    @CrossOrigin
    @GetMapping("/user/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable("id") Integer idUser) throws NotFoundException {

        Usuario userResp = iUserService.getUserById(idUser);

        if (userResp != null) {
            return new ResponseEntity<>(userResp, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("Error", "User ID " + idUser + " not found"));
        }
    }

    @CrossOrigin
    @GetMapping("/users")
    public ResponseEntity<List<Usuario>> getAllUsers() {

        List<Usuario> usuarios = iUserService.getAllUsers();

        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping(value = "/createUser", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_XML_VALUE})
    public ResponseEntity<Object> addUser(@RequestBody Usuario usuario, UriComponentsBuilder builder) {

        boolean flag = iUserService.addUser(usuario);

        if (!flag) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("Error", "User not created"));
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("user/{id}").buildAndExpand(usuario.getId_usuario()).toUri());

        return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonMap("Ok", "User "
                + usuario.getUsername() + " successfully created"));
    }

    @CrossOrigin
    @PostMapping("/updateUser")
    public ResponseEntity<Object> updateUser(@RequestBody Usuario usuario) throws NotFoundException {

        iUserService.updateUser(usuario);

        return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonMap("Ok", "User ID "
                + usuario.getId_usuario() + " successfully updated"));
    }

    @CrossOrigin
    @PostMapping("/deleteUser/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable("id") Integer idUser) throws NotFoundException {

        iUserService.deleteUser(idUser);

        return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonMap("Ok", "User ID "
                + idUser + " successfully deleted"));
    }
}
