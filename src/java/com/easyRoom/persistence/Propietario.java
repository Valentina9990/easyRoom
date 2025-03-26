/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.easyRoom.persistence;

import java.util.List;


public class Propietario extends Usuario {
    
    List<Habitacion> habitaciones;
    
    public Propietario(String nombre, String apellido, String correo, String contraseña, String rol) {
        super(nombre, apellido, correo, contraseña, rol);
    }

    public List<Habitacion> getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(List<Habitacion> habitaciones) {
        this.habitaciones = habitaciones;
    }
    
}
