package com.bancolombia.prestamos.controller;

import com.bancolombia.prestamos.entity.Cliente;
import com.bancolombia.prestamos.repository.ClienteRepository;
import com.bancolombia.prestamos.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    private final ClienteRepository clienteRepository;

    public ClienteController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @GetMapping
    public List<Cliente> listarClientes() {

        return clienteService.listarClientes();


/*                List<Cliente> clientes = clienteService.listarClientes();

        if (clientes.isEmpty()) {
            throw new SolicitudInvalidaException("No hay clientes registrados en el sistema");
        }

        return clientes.stream()
                .map(ClienteDTO::new) // Convierte Cliente a ClienteDTO
                .collect(Collectors.toList());
    }
    */
    }
}