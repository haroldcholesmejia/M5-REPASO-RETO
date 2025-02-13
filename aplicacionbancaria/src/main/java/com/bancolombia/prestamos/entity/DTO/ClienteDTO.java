package com.bancolombia.prestamos.entity.DTO;

import com.bancolombia.prestamos.entity.Cliente;

public class ClienteDTO {

    private Long id;
    private String nombre;
    private String email;
    private String telefono;
    private String direccion;

    public ClienteDTO(Cliente cliente) {
        this.id = cliente.getId();
        this.nombre = cliente.getNombre();
        this.email = cliente.getEmail();
        this.telefono = cliente.getTelefono();
        this.direccion = cliente.getDireccion();
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
