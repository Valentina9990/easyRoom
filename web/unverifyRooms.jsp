<%@page import="java.util.List"%>
<%@page import="com.easyRoom.persistence.Habitacion"%>
<%@page import="com.easyRoom.service.HabitacionService"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <title>Habitaciones por verificar</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600&display=swap" rel="stylesheet">
    <style>
        :root {
            --primary-color: #3498db;
            --secondary-color: #2ecc71;
            --background-color: #f8f9fa;
            --text-color: #2c3e50;
            --table-border-color: #e0e4e8;
        }

        * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
        }

        body {
            font-family: 'Inter', Arial, sans-serif;
            background-color: var(--background-color);
            color: var(--text-color);
            line-height: 1.6;
            padding: 20px;
        }

        .container {
            width: 100%;
            max-width: 1200px;
            margin: 0 auto;
            background-color: white;
            border-radius: 12px;
            box-shadow: 0 10px 25px rgba(0, 0, 0, 0.1);
            padding: 30px;
            overflow-x: auto;
        }

        h1 {
            color: var(--primary-color);
            text-align: center;
            margin-bottom: 25px;
            font-weight: 600;
        }

        table {
            width: 100%;
            border-collapse: separate;
            border-spacing: 0;
            background: white;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.05);
            border-radius: 8px;
            overflow: hidden;
        }

        th, td {
            border: 1px solid var(--table-border-color);
            padding: 12px 15px;
            text-align: left;
            transition: background-color 0.3s ease;
        }

        th {
            background-color: var(--primary-color);
            color: white;
            font-weight: 600;
            text-transform: uppercase;
            font-size: 14px;
            position: sticky;
            top: 0;
            z-index: 10;
        }

        tr:nth-child(even) {
            background-color: #f9fafb;
        }

        tr:hover {
            background-color: #f1f3f5;
        }

        .btn-resena {
            display: inline-block;
            background-color: var(--secondary-color);
            color: white;
            padding: 8px 15px;
            text-decoration: none;
            border-radius: 6px;
            font-size: 14px;
            transition: background-color 0.3s ease, transform 0.1s ease;
        }

        .btn-resena:hover {
            background-color: #27ae60;
            transform: translateY(-2px);
        }

        .back-link {
            display: block;
            text-align: center;
            margin-top: 20px;
            color: var(--primary-color);
            text-decoration: none;
            transition: color 0.3s ease;
        }

        .back-link:hover {
            color: #2980b9;
            text-decoration: underline;
        }

        @media (max-width: 768px) {
            .container {
                padding: 15px;
            }

            table {
                font-size: 14px;
            }

            th, td {
                padding: 10px;
            }
        }

        @media (max-width: 480px) {
            body {
                padding: 10px;
            }

            table {
                font-size: 12px;
            }
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
                    <th>Acción</th>
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
                    <td>
                        <a href="roomReview.jsp?habitacionId=<%= habitacion.getId() %>" class="btn-resena">Añadir Reseña</a>
                    </td>
                </tr>
                <% } %>
            </tbody>
        </table>
        <a href="index.html" class="back-link">Volver al inicio</a>
    </div>
</body>
</html>