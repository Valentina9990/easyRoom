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

        Usuario usuario = new Usuario(
            request.getParameter("nombre"),
            request.getParameter("apellido"),
            request.getParameter("correo"),
            request.getParameter("contrasena"),
            request.getParameter("rol")
        );

        if (usuarioService.registerUser(usuario)) {
            request.setAttribute("success", "Registro exitoso. Por favor inicie sesión.");
            response.sendRedirect("login.jsp");
        } else {
            request.setAttribute("error", "El correo ya está registrado");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/register.jsp").forward(request, response);
    }
}