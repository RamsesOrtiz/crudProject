package com.ventas.controller;

import com.ventas.exceptions.NotFoundException;
import com.ventas.model.Cliente;
import com.ventas.service.IClientService;
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
public class ClientController {

    @Autowired
    private IClientService iClientService;

    @CrossOrigin
    @GetMapping("/client/{id}")
    public ResponseEntity<Object> getClientById(@PathVariable("id") Integer id_cliente) throws NotFoundException {

        Cliente clienteResp = iClientService.getClientById(id_cliente);

        if (clienteResp != null) {
            return new ResponseEntity<>(clienteResp, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("Error", "Client ID " + id_cliente + " not found"));
        }
    }

    @CrossOrigin
    @GetMapping("/clients")
    public ResponseEntity<List<Cliente>> getAllClients() {

        List<Cliente> clientes = iClientService.getAllClients();

        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping(value = "/createClient", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_XML_VALUE})
    public ResponseEntity<Object> addClient(@RequestBody Cliente cliente, UriComponentsBuilder builder) {

        boolean flag = iClientService.addClient(cliente);

        if (!flag) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("Error", "Client not created"));
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("client/{id}").buildAndExpand(cliente.getId_cliente()).toUri());

        return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonMap("Ok", "Client "
                + cliente.getNombre() + " successfully created"));
    }

    @CrossOrigin
    @PostMapping("/updateClient")
    public ResponseEntity<Object> updateClient(@RequestBody Cliente cliente) throws NotFoundException {

        iClientService.updateClient(cliente);

        return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonMap("Ok", "Client ID "
                + cliente.getId_cliente() + " successfully updated"));
    }

    @CrossOrigin
    @PostMapping("/deleteClient/{id}")
    public ResponseEntity<Object> deleteClient(@PathVariable("id") Integer id_cliente) throws NotFoundException {

        iClientService.deleteClient(id_cliente);

        return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonMap("Ok", "Client ID "
                + id_cliente + " successfully deleted"));
    }
}
