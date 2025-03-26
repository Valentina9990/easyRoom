<%-- 
    Document   : createRoom
    Created on : 25/03/2025, 7:32:07 p. m.
    Author     : Valentina Sarmiento
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Crear Habitación</title>
    <style>
    </style>
</head>
<body>
    <h2>Crear Habitación</h2>
    
    <form action="HabitacionController" method="post">
        <label for="ciudad">Ciudad:</label>
        <input type="text" id="ciudad" name="ciudad" required>
        <br>
        
        <label for="direccion">Dirección:</label>
        <input type="text" id="direccion" name="direccion" required>
        <br>
        
        <label for="capacidad">Capacidad:</label>
        <input type="number" id="capacidad" name="capacidad" required min="1">
        <br>
        
        <label for="propietario_id">ID del Propietario:</label>
        <input type="number" id="propietario_id" name="propietario_id" required>
        <br>
        
        <button type="submit">Guardar Habitación</button>
    </form>
    
    <% String mensaje = (String) request.getAttribute("mensaje"); %>
    <% if (mensaje != null) { %>
        <p style="color: green;"><%= mensaje %></p>
    <% } %>
    <a href="index.html">Volver al inicio</a>
    
</body>
</html>

