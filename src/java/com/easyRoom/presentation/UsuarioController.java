/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.easyRoom.presentation;

import com.easyRoom.persistence.Usuario;
import com.easyRoom.service.UsuarioService;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Valentina Sarmiento
 */
public class UsuarioController extends HttpServlet {

    private final UsuarioService usuarioService = new UsuarioService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String correo = request.getParameter("correo");
        String contrasena = request.getParameter("contraseña");
        String rol = request.getParameter("rol");

        Usuario usuario = new Usuario(nombre, apellido, correo, contrasena, rol);

        try {
            usuarioService.saveUsuario(usuario);
            request.setAttribute("mensaje", "Usuario creado exitosamente.");
            request.getRequestDispatcher("/successMessage.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("mensaje", "Error al crear el usuario: " + e.getMessage());
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/register.jsp").forward(request, response);
    }
}