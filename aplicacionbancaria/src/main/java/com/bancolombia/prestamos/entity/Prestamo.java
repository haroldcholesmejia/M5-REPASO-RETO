package com.bancolombia.prestamos.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal monto;
    private double interes;
    private int duracionMeses;

    @Enumerated(EnumType.STRING)
    private EstadoPrestamo estado;

    @Column(updatable = false)
    private LocalDateTime fechaCreacion;

    private LocalDateTime fechaActualizacion;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    @JsonBackReference
    private Cliente cliente;

    @PrePersist
    protected void onCreate() {
        fechaCreacion = LocalDateTime.now();
        fechaActualizacion = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        fechaActualizacion = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public double getInteres() {
        return interes;
    }

    public int getDuracionMeses() {
        return duracionMeses;
    }

    public EstadoPrestamo getEstado() {
        return estado;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public void setInteres(double interes) {
        this.interes = interes;
    }

    public void setDuracionMeses(int duracionMeses) {
        this.duracionMeses = duracionMeses;
    }

    public void setEstado(EstadoPrestamo estado) {
        this.estado = estado;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

}
