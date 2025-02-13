package com.bancolombia.prestamos.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum EstadoPrestamo {
    PENDIENTE,
    APROBADO,
    RECHAZADO;
}
