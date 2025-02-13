package com.bancolombia.prestamos.config;

public class SolicitudInvalidaException  extends RuntimeException {
    public SolicitudInvalidaException(String mensaje) {
        super(mensaje);
    }
}