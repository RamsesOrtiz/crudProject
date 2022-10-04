package com.ventas.controller;

import com.ventas.exceptions.NotFoundException;
import com.ventas.model.Login;
import com.ventas.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("api")
public class LoginController {

    @Autowired
    private ILoginService iLoginService;

    @CrossOrigin
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody Login login) throws NotFoundException{

        boolean flag = iLoginService.login(login);

        if (!flag) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("Error", "Username/Password not found"));
        }

        return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonMap("Ok", "Successful login"));
    }
}
