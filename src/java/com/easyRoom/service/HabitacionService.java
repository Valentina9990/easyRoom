package com.easyRoom.service;

import com.easyRoom.persistence.Habitacion;
import com.easyRoom.persistence.HabitacionRepository;
import java.util.List;

public class HabitacionService {
    private final HabitacionRepository habitacionRepository;

    public HabitacionService() {
        this.habitacionRepository = new HabitacionRepository();
    }

    public void saveHabitacion(Habitacion habitacion) {
        habitacionRepository.saveHabitacion(habitacion);
    }

    public List<Habitacion> getHabitacionesByPropietario(int propietarioId) {
        return habitacionRepository.findHabitacionesByPropietario(propietarioId);
    }
    
    public List<Habitacion> getHabitacionesNoVerificadas() {
        return habitacionRepository.findHabitacionesNoVerificadas();
    }
} 
