package com.bancolombia.prestamos.controller;

import com.bancolombia.prestamos.entity.Cliente;
import com.bancolombia.prestamos.entity.DTO.PrestamoDTO;
import com.bancolombia.prestamos.entity.Prestamo;
import com.bancolombia.prestamos.repository.ClienteRepository;
import com.bancolombia.prestamos.repository.PrestamoRepository;
import com.bancolombia.prestamos.service.PrestamoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/prestamos")
public class PrestamoController {

    @Autowired
    private PrestamoService prestamoService;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PrestamoRepository prestamoRepository;

    @PostMapping("/solicitar")
    public ResponseEntity<?> solicitarPrestamo(@RequestBody PrestamoDTO prestamoDTO) {
        Prestamo prestamo = prestamoService.solicitarPrestamo(prestamoDTO);
        return ResponseEntity.ok(prestamo);
    }



    @GetMapping("/historial/{clienteId}")
    public List<PrestamoDTO> historialPrestamos(@PathVariable Long clienteId) {
        return prestamoService.obtenerHistorialPrestamos(clienteId);
    }

    @PutMapping("/aprobar/{id}")
    public PrestamoDTO aprobarPrestamo(@PathVariable Long id) {
        return prestamoService.aprobarPrestamo(id);
    }

    @PutMapping("/rechazar/{id}")
    public PrestamoDTO rechazarPrestamo(@PathVariable Long id) {
        return prestamoService.rechazarPrestamo(id);
    }

    @GetMapping("/estado/{id}")
    public ResponseEntity<?> consultarEstadoPrestamo(@PathVariable Long id) {
        Optional<Prestamo> prestamoOpt = prestamoRepository.findById(id);

        if (prestamoOpt.isPresent()) {
            Prestamo prestamo = prestamoOpt.get();
            Cliente cliente = prestamo.getCliente();
            List<Prestamo> historial = prestamoRepository.findTop3ByClienteOrderByFechaCreacionDesc(cliente);

            Map<String, Object> response = new HashMap<>();
            response.put("estado", prestamo.getEstado());
            response.put("historial", historial);

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Préstamo no encontrado");
        }
    }

    @GetMapping("/simular-cuotas")
    public ResponseEntity<?> simularCuotas(@RequestParam double monto, @RequestParam double interes, @RequestParam int duracion) {
        if (monto <= 0 || interes <= 0 || duracion <= 0) {
            return ResponseEntity.badRequest().body("Los valores deben ser mayores a 0");
        }

        double tasaMensual = (interes / 100) / 12;
        double cuota = (monto * tasaMensual) / (1 - Math.pow(1 + tasaMensual, -duracion));

        Map<String, Object> response = new HashMap<>();
        response.put("monto", monto);
        response.put("interesAnual", interes);
        response.put("duracionMeses", duracion);
        response.put("cuotaMensual", cuota);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public PrestamoDTO consultarPrestamo(@PathVariable Long id) {
        return prestamoService.consultarPrestamo(id);
    }

}