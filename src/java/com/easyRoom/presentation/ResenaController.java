/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.easyRoom.presentation;

import com.easyRoom.persistence.Resena;
import com.easyRoom.service.HabitacionService;
import com.easyRoom.service.ResenaService;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author Valentina Sarmiento
 */
@WebServlet(name = "ResenaController", urlPatterns = {"/ResenaController"})
public class ResenaController extends HttpServlet {

    private final ResenaService resenaService = new ResenaService();
    private final HabitacionService habitacionService = new HabitacionService();
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int usuarioId = Integer.parseInt(request.getParameter("usuarioId"));
        int habitacionId = Integer.parseInt(request.getParameter("habitacionId"));
        String comentario = request.getParameter("comentario");
        int calificacion = Integer.parseInt(request.getParameter("calificacion"));
        
        Resena resena = new Resena(usuarioId, habitacionId, comentario, calificacion);
        
        try {
            resenaService.saveResena(resena);
            habitacionService.markAsVerified(habitacionId);
            request.setAttribute("mensaje", "Reseña creada exitosamente.");
        } catch (Exception e) {
            request.setAttribute("mensaje", "Error al crear la reseña: " + e.getMessage());
        }
        
        request.getRequestDispatcher("/roomReview.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String habitacionIdParam = request.getParameter("habitacionId");
        if (habitacionIdParam != null) {
            int habitacionId = Integer.parseInt(habitacionIdParam);
            List<Resena> resenas = resenaService.getResenasByHabitacion(habitacionId);
            request.setAttribute("resenas", resenas);
        }
        request.getRequestDispatcher("/roomReview.jsp").forward(request, response);
    }
}
