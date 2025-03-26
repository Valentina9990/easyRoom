/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.easyRoom.presentation;

import com.easyRoom.persistence.Habitacion;
import com.easyRoom.service.HabitacionService;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Valentina Sarmiento
 */
public class HabitacionController extends HttpServlet {

    private final HabitacionService habitacionService = new HabitacionService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String ciudad = request.getParameter("ciudad");
        String direccion = request.getParameter("direccion");
        int capacidad = Integer.parseInt(request.getParameter("capacidad"));
        int propietarioId = Integer.parseInt(request.getParameter("propietario_id"));

        Habitacion habitacion = new Habitacion(ciudad, direccion, capacidad, propietarioId);

        try {
            habitacionService.saveHabitacion(habitacion);
            request.setAttribute("mensaje", "Habitación creada exitosamente.");
        } catch (Exception e) {
            request.setAttribute("mensaje", "Error al crear la habitación: " + e.getMessage());
        }

        request.getRequestDispatcher("/createRoom.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/createRoom.jsp").forward(request, response);
        
    }

}
