<%@page import="java.util.List"%>
<%@page import="com.easyRoom.persistence.Habitacion"%>
<%@page import="com.easyRoom.service.HabitacionService"%>
<!DOCTYPE html>
<html>
<head>
    <title>Habitaciones No Verificadas</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
        }
        h1 {
            color: #333;
            text-align: center;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            background: white;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #007bff;
            color: white;
        }
        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
        .container {
            width: 80%;
            margin: auto;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Lista de Habitaciones No Verificadas</h1>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Ciudad</th>
                    <th>Dirección</th>
                    <th>Capacidad</th>
                    <th>Propietario</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    HabitacionService habitacionService = new HabitacionService();
                    List<Habitacion> habitaciones = habitacionService.getHabitacionesNoVerificadas();
                    for (Habitacion habitacion : habitaciones) {
                %>
                <tr>
                    <td><%= habitacion.getId() %></td>
                    <td><%= habitacion.getCiudad() %></td>
                    <td><%= habitacion.getDireccion() %></td>
                    <td><%= habitacion.getCapacidad() %></td>
                    <td><%= habitacion.getPropietarioId() %></td>
                </tr>
                <% } %>
            </tbody>
        </table>
        <a href="index.html">Volver al inicio</a>
    </div>
</body>
</html>
