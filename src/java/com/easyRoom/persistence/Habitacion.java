package com.easyRoom.persistence;

import java.util.LinkedList;

public class Habitacion {
    private LinkedList<Resena> resenas = new LinkedList<>();
    private LinkedList<Servicio> servicios = new LinkedList<>();
    private int id;
    private String ciudad;
    private String direccion;
    private int capacidad;
    private int propietarioId;
    private boolean verificada;

    public Habitacion(int id, String ciudad, String direccion, int capacidad, int propietarioId) {
        this.id = id;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.capacidad = capacidad;
        this.propietarioId = propietarioId;
    }

    public Habitacion(String ciudad, String direccion, int capacidad, int propietarioId) {
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.capacidad = capacidad;
        this.propietarioId = propietarioId;
    }

    public boolean isVerificada() {
        return verificada;
    }

    public void setVerificada(boolean verificada) {
        this.verificada = verificada;
    }


    public LinkedList<Resena> getResenas() {
        return resenas;
    }

    public void setResenas(LinkedList<Resena> resenas) {
        this.resenas = resenas;
    }

    public LinkedList<Servicio> getServicios() {
        return servicios;
    }

    public void setServicios(LinkedList<Servicio> servicios) {
        this.servicios = servicios;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPropietarioId() {
        return propietarioId;
    }

    public void setPropietarioId(int propietarioId) {
        this.propietarioId = propietarioId;
    }

    
 
}


