package com.bancolombia.prestamos.service;

import com.bancolombia.prestamos.config.SolicitudInvalidaException;
import com.bancolombia.prestamos.entity.Cliente;
import com.bancolombia.prestamos.entity.DTO.PrestamoDTO;
import com.bancolombia.prestamos.entity.Prestamo;
import com.bancolombia.prestamos.entity.EstadoPrestamo;
import com.bancolombia.prestamos.repository.ClienteRepository;
import com.bancolombia.prestamos.repository.PrestamoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrestamoService {

    private final PrestamoRepository prestamoRepository;
    private final ClienteRepository clienteRepository;

    public PrestamoService(PrestamoRepository prestamoRepository, ClienteRepository clienteRepository) {
        this.prestamoRepository = prestamoRepository;
        this.clienteRepository = clienteRepository;
    }

    public Prestamo solicitarPrestamo(PrestamoDTO prestamoDTO) {
        Cliente cliente = clienteRepository.findById(prestamoDTO.getClienteId())
                .orElseThrow(() -> new SolicitudInvalidaException("El cliente no existe."));

        boolean tienePendiente = prestamoRepository.existsByClienteIdAndEstado(cliente.getId(), EstadoPrestamo.PENDIENTE);
        if (tienePendiente) {
            throw new SolicitudInvalidaException("El cliente ya tiene un préstamo pendiente y no puede solicitar otro.");
        }

        Prestamo prestamo = new Prestamo();
        prestamo.setMonto(prestamoDTO.getMonto());
        prestamo.setInteres(prestamoDTO.getInteres());
        prestamo.setDuracionMeses(prestamoDTO.getDuracionMeses());
        prestamo.setEstado(EstadoPrestamo.PENDIENTE);
        prestamo.setCliente(cliente);

        return prestamoRepository.save(prestamo);
    }


    public PrestamoDTO consultarPrestamo(Long id) {
        return prestamoRepository.findById(id)
                .map(PrestamoDTO::new)
                .orElseThrow(() -> new SolicitudInvalidaException("El préstamo con ID " + id + " no existe"));
    }

    public List<PrestamoDTO> obtenerHistorialPrestamos(Long clienteId) {
        if (!clienteRepository.existsById(clienteId)) {
            throw new SolicitudInvalidaException("El cliente con ID " + clienteId + " no existe");
        }

        List<Prestamo> prestamos = prestamoRepository.findByClienteId(clienteId);

        if (prestamos.isEmpty()) {
            throw new SolicitudInvalidaException("El cliente con ID " + clienteId + " no tiene historial de préstamos");
        }

        return prestamos.stream()
                .map(PrestamoDTO::new)
                .collect(Collectors.toList());
    }

    public PrestamoDTO aprobarPrestamo(Long id) {
        return prestamoRepository.findById(id)
                .map(prestamo -> {
                    if (prestamo.getEstado() != EstadoPrestamo.PENDIENTE) {
                        throw new SolicitudInvalidaException("El préstamo ya ha sido procesado y no puede modificarse.");
                    }
                    prestamo.setEstado(EstadoPrestamo.APROBADO);
                    return new PrestamoDTO(prestamoRepository.save(prestamo)); // Retorna DTO
                })
                .orElseThrow(() -> new SolicitudInvalidaException("Préstamo con ID " + id + " no encontrado"));
    }

    public PrestamoDTO rechazarPrestamo(Long id) {
        return prestamoRepository.findById(id)
                .map(prestamo -> {
                    if (prestamo.getEstado() != EstadoPrestamo.PENDIENTE) {
                        throw new SolicitudInvalidaException("El préstamo ya ha sido procesado y no puede modificarse.");
                    }
                    prestamo.setEstado(EstadoPrestamo.RECHAZADO);
                    return new PrestamoDTO(prestamoRepository.save(prestamo)); // Retorna DTO
                })
                .orElseThrow(() -> new SolicitudInvalidaException("Préstamo con ID " + id + " no encontrado"));
    }


}