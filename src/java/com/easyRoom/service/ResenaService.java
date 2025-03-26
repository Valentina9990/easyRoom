/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.easyRoom.service;
import com.easyRoom.persistence.Resena;
import com.easyRoom.persistence.ResenaRepository;
import java.util.List;

/**
 *
 * @author Valentina Sarmiento
 */
public class ResenaService {
    private final ResenaRepository resenaRepository;

    public ResenaService() {
        this.resenaRepository = new ResenaRepository();
    }

    public List<Resena> getResenasByHabitacion(int habitacionId) {
        return resenaRepository.findResenasByHabitacion(habitacionId);
    }

    public List<Resena> getResenasByUsuario(int usuarioId) {
        return resenaRepository.findResenasByUsuario(usuarioId);
    }

    public int saveResena(Resena resena) {
        return resenaRepository.saveResena(resena);
    }
}
