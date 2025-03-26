/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.easyRoom.persistence;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.easyRoom.dataAccess.DatabaseConnection;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Valentina Sarmiento
 */
public class HabitacionRepository {
    private static final Logger LOGGER = Logger.getLogger(HabitacionRepository.class.getName());

    public int saveHabitacion(Habitacion habitacion) {
        String sql = "INSERT INTO Habitacion (ciudad, direccion, capacidad, propietario_id) VALUES (?, ?, ?, ?)";
        int generatedId = -1;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, habitacion.getCiudad());
            stmt.setString(2, habitacion.getDireccion());
            stmt.setInt(3, habitacion.getCapacidad());
            stmt.setInt(4, habitacion.getPropietarioId());

            int filasInsertadas = stmt.executeUpdate();
            if (filasInsertadas > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        generatedId = generatedKeys.getInt(1);
                    }
                }
                LOGGER.info("Habitación guardada exitosamente con ID: " + generatedId);
            }

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al guardar la habitación: " + e.getMessage(), e);
        }
        return generatedId;
    }

    public List<Habitacion> findHabitacionesByPropietario(int propietarioId) {
        List<Habitacion> habitaciones = new ArrayList<>();
        String sql = "SELECT * FROM Habitacion WHERE propietario_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, propietarioId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Habitacion habitacion = new Habitacion(
                            rs.getInt("id"),
                            rs.getString("ciudad"),
                            rs.getString("direccion"),
                            rs.getInt("capacidad"),
                            rs.getInt("propietario_id")
                    );
                    habitaciones.add(habitacion);
                }
            }

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al obtener habitaciones: " + e.getMessage(), e);
        }
        return habitaciones;
    }
    
    public boolean updateVerificada(int habitacionId, boolean verificada) {
        String sql = "UPDATE Habitacion SET verificada = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setBoolean(1, verificada);
            stmt.setInt(2, habitacionId);

            int filasActualizadas = stmt.executeUpdate();
            return filasActualizadas > 0;

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al actualizar estado de verificación: " + e.getMessage(), e);
            return false;
        }
    }
    
    public List<Habitacion> findHabitacionesNoVerificadas() {
        List<Habitacion> habitaciones = new ArrayList<>();
        String sql = "SELECT * FROM Habitacion WHERE verificada = false"; 

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Habitacion habitacion = new Habitacion(
                        rs.getInt("id"),
                        rs.getString("ciudad"),
                        rs.getString("direccion"),
                        rs.getInt("capacidad"),
                        rs.getInt("propietario_id")
                );
                habitacion.setVerificada(rs.getBoolean("verificada"));
                habitaciones.add(habitacion);
            }

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al obtener habitaciones no verificadas: " + e.getMessage(), e);
        }
        return habitaciones;
    }
}
