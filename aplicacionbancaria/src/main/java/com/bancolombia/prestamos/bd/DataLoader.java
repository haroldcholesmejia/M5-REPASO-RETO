package com.bancolombia.prestamos.bd;

import com.bancolombia.prestamos.entity.Cliente;
import com.bancolombia.prestamos.repository.ClienteRepository;
import com.bancolombia.prestamos.repository.PrestamoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final ClienteRepository clienteRepository;

    public DataLoader(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public void run(String... args) {
        if (clienteRepository.count() == 0) {
            List<Cliente> clientes = Arrays.asList(
                    new Cliente(null, "Harold Choles Mejia", "harold.choles@mail.com", "3001234567", "Calle 5421"),
                    new Cliente(null, "Larry Ramirez", "larry.ramirez@mail.com", "3001234567", "Calle 123"),
                    new Cliente(null, "Juan Pérez", "juan.perez@example.com", "3001234567", "Calle 123"),
                    new Cliente(null, "María Gómez", "maria.gomez@mail.com", "3012345678", "Carrera 45"),
                    new Cliente(null, "Carlos Ramírez", "carlos.ramirez@example.com", "3023456789", "Avenida 10")
            );

            clienteRepository.saveAll(clientes);
            System.out.println("✔ Clientes grabados en la base de datos correctamente.");
        } else {
            System.out.println("✔ Ya existen clientes en la BD, validar si se deben eliminar o si requiere duplicar la información");
            System.out.println("✔ No se insertaron nuevos registros.");
        }
    }
}
