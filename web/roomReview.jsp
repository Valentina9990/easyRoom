<%@page import="com.easyRoom.persistence.Resena"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Crear Reseña</title>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;700&display=swap" rel="stylesheet">
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        body {
            font-family: 'Roboto', sans-serif;
            background-color: #f4f4f4;
            line-height: 1.6;
            color: #333;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            padding: 20px;
        }
        .review-container {
            background-color: white;
            border-radius: 10px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            padding: 30px;
            width: 100%;
            max-width: 500px;
        }
        .review-header {
            text-align: center;
            margin-bottom: 25px;
            color: #2c3e50;
        }
        .form-group {
            margin-bottom: 15px;
        }
        .form-group label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
            color: #34495e;
        }
        .form-group input, 
        .form-group textarea {
            width: 100%;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
            transition: border-color 0.3s ease;
        }
        .form-group input:focus, 
        .form-group textarea:focus {
            outline: none;
            border-color: #3498db;
        }
        .submit-btn {
            width: 100%;
            padding: 12px;
            background-color: #3498db;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
        .submit-btn:hover {
            background-color: #2980b9;
        }
        .message {
            text-align: center;
            margin-top: 15px;
            padding: 10px;
            border-radius: 5px;
        }
        .message-success {
            background-color: #dff0d8;
            color: #3c763d;
            border: 1px solid #d6e9c6;
        }
        .message-error {
            background-color: #f2dede;
            color: #a94442;
            border: 1px solid #ebccd1;
        }
        .back-link {
            display: block;
            text-align: center;
            margin-top: 15px;
            color: #3498db;
            text-decoration: none;
            transition: color 0.3s ease;
        }
        .back-link:hover {
            color: #2980b9;
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="review-container">
        <h2 class="review-header">Crear Reseña</h2>
        
        <%
            String habitacionIdParam = request.getParameter("habitacionId");
            boolean tieneParametro = (habitacionIdParam != null && !habitacionIdParam.isEmpty());
            int habitacionId = tieneParametro ? Integer.parseInt(habitacionIdParam) : 0;
        %>
        
        <form action="ResenaController" method="post">
            <div class="form-group">
                <label for="usuarioId">Usuario ID:</label>
                <input type="number" id="usuarioId" name="usuarioId" required>
            </div>
            
            <div class="form-group">
                <label for="habitacionId">Habitación ID:</label>
                <% if (tieneParametro) { %>
                    <input type="number" id="habitacionId" name="habitacionId" value="<%= habitacionId %>" readonly>
                <% } else { %>
                    <input type="number" id="habitacionId" name="habitacionId" required>
                <% } %>
            </div>
            
            <div class="form-group">
                <label for="comentario">Comentario:</label>
                <textarea id="comentario" name="comentario" rows="4" required></textarea>
            </div>
            
            <div class="form-group">
                <label for="calificacion">Calificación (1-5):</label>
                <input type="number" id="calificacion" name="calificacion" min="1" max="5" required>
            </div>
            
            <button type="submit" class="submit-btn">Guardar Reseña</button>
        </form>
        
        <% 
            String mensaje = (String) request.getAttribute("mensaje");
            if (mensaje != null) { 
        %>
            <div class="message <%= mensaje.contains("Error") ? "message-error" : "message-success" %>">
                <%= mensaje %>
            </div>
        <% } %>
        
        <a href="index.html" class="back-link">Volver al inicio</a>
    </div>
</body>
</html>