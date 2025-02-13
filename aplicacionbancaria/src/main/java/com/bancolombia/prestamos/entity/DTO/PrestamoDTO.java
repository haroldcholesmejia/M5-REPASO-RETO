package com.bancolombia.prestamos.entity.DTO;

import com.bancolombia.prestamos.entity.EstadoPrestamo;
import com.bancolombia.prestamos.entity.Prestamo;

import java.math.BigDecimal;
import java.time.LocalDateTime;


public class PrestamoDTO {
    private Long clienteId;
    private BigDecimal monto;
    private double interes;
    private int duracionMeses;
    private String estado;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;

    public PrestamoDTO() {
    }

    public PrestamoDTO(double interes) {
        this.interes = interes;
    }

    public PrestamoDTO(Prestamo prestamo) {
        this.clienteId = prestamo.getCliente().getId();  // Asegurar que cliente no sea null
        this.monto = prestamo.getMonto();
        this.interes = prestamo.getInteres();
        this.duracionMeses = prestamo.getDuracionMeses();
        this.estado = prestamo.getEstado().toString();
        this.fechaCreacion = prestamo.getFechaCreacion();
        this.fechaActualizacion = prestamo.getFechaActualizacion();
    }

    public Long getClienteId() {
        return clienteId;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public double getInteres() {
        return interes;
    }

    public void setInteres(double interes) {
        this.interes = interes;
    }

    public int getDuracionMeses() {
        return duracionMeses;
    }

    public void setDuracionMeses(int duracionMeses) {
        this.duracionMeses = duracionMeses;
    }
}


