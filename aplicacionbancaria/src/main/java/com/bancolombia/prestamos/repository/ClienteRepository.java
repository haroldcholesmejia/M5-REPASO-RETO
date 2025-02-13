package com.bancolombia.prestamos.repository;

import com.bancolombia.prestamos.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}