package com.bancolombia.prestamos.repository;

import com.bancolombia.prestamos.entity.Cliente;
import com.bancolombia.prestamos.entity.EstadoPrestamo;
import com.bancolombia.prestamos.entity.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {
    List<Prestamo> findByClienteId(Long clienteId);
   List<Prestamo> findTop3ByClienteOrderByFechaCreacionDesc(Cliente cliente);
    boolean existsByClienteIdAndEstado(Long clienteId, Enum estado);


}
