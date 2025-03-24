package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import database.DatabaseConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/guardarUsuario")
public class GuardarUsuarioServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    String nombre = request.getParameter("nombre");
    String apellido = request.getParameter("apellido");
    String correo = request.getParameter("correo");
    String contraseña = request.getParameter("contraseña");
    String rol = request.getParameter("rol");

    // Imprimir los datos recibidos
    System.out.println("Datos recibidos:");
    System.out.println("Nombre: " + nombre);
    System.out.println("Apellido: " + apellido);
    System.out.println("Correo: " + correo);
    System.out.println("Contraseña: " + contraseña);
    System.out.println("Rol: " + rol);

    Connection conn = null;
    PreparedStatement pstmt = null;

    try {
        conn = DatabaseConnection.getConnection();
        String sql = "INSERT INTO usuario (nombre, apellido, correo, contrasena, rol) VALUES (?, ?, ?, ?, ?)";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, nombre);
        pstmt.setString(2, apellido);
        pstmt.setString(3, correo);
        pstmt.setString(4, contraseña);
        pstmt.setString(5, rol);
        pstmt.executeUpdate();
        System.out.println("Usuario guardado correctamente.");
    } catch (Exception e) {
        System.out.println("Error al guardar el usuario:");
        e.printStackTrace();
    } finally {
        try {
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    response.sendRedirect("index.html");
}
}