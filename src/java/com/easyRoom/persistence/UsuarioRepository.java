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
public class UsuarioRepository {
    private static final Logger LOGGER = Logger.getLogger(UsuarioRepository.class.getName());

    public int saveUsuario(Usuario usuario) {
        String sql = "INSERT INTO Usuario (nombre, apellido, correo, contrasena, rol) VALUES (?, ?, ?, ?, ?)";
        int generatedId = -1;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getApellido());
            stmt.setString(3, usuario.getCorreo());
            stmt.setString(4, usuario.getContrasena());
            stmt.setString(5, usuario.getRol());

            int filasInsertadas = stmt.executeUpdate();
            if (filasInsertadas > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        generatedId = generatedKeys.getInt(1);
                    }
                }
                LOGGER.info("Usuario guardado exitosamente con ID: " + generatedId);
            }
            if ("Propietario".equals(usuario.getRol())) {
                String sqlPropietario = "INSERT INTO propietario (usuario_id) VALUES (?)";
                try (PreparedStatement stmtProp = conn.prepareStatement(sqlPropietario)) {
                    stmtProp.setInt(1, generatedId);
                    stmtProp.executeUpdate();
                }
            }

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al guardar el usuario: " + e.getMessage(), e);
        }
        return generatedId;
    }

    public List<Usuario> findAllUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM Usuarios";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Usuario usuario = new Usuario(
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("correo"),
                        rs.getString("contrasena"),
                        rs.getString("rol")
                );
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al obtener los usuarios: " + e.getMessage(), e);
        }

        return usuarios;
    }
}
