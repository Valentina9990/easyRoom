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

public class ResenaRepository {
    private static final Logger LOGGER = Logger.getLogger(ResenaRepository.class.getName());

    public int saveResena(Resena resena) {
        String sql = "INSERT INTO Resena (usuario_id, habitacion_id, comentario, calificacion) VALUES (?, ?, ?, ?)";
        int generatedId = -1;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, resena.getUsuarioId());
            stmt.setInt(2, resena.getHabitacionId());
            stmt.setString(3, resena.getComentario());
            stmt.setInt(4, resena.getCalificacion());
            int filasInsertadas = stmt.executeUpdate();
            if (filasInsertadas > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        generatedId = generatedKeys.getInt(1);
                    }
                }
                LOGGER.info("Reseña guardada exitosamente con ID: " + generatedId);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al guardar la reseña: " + e.getMessage(), e);
        }
        return generatedId;
    }

    public List<Resena> findResenasByHabitacion(int habitacionId) {
        List<Resena> resenas = new ArrayList<>();
        String sql = "SELECT * FROM Resena WHERE habitacion_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, habitacionId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Resena resena = new Resena(
                        rs.getInt("usuario_id"),
                        rs.getInt("habitacion_id"),
                        rs.getString("comentario"),
                        rs.getInt("calificacion")
                    );
                    resenas.add(resena);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al obtener las reseñas: " + e.getMessage(), e);
        }
        return resenas;
    }

    public List<Resena> findResenasByUsuario(int usuarioId) {
        List<Resena> resenas = new ArrayList<>();
        String sql = "SELECT * FROM Resena WHERE usuario_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, usuarioId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Resena resena = new Resena(
                        rs.getInt("usuario_id"),
                        rs.getInt("habitacion_id"),
                        rs.getString("comentario"),
                        rs.getInt("calificacion")
                    );
                    resenas.add(resena);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al obtener las reseñas: " + e.getMessage(), e);
        }
        return resenas;
    }
}