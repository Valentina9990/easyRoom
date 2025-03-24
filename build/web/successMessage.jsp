<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Éxito</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .message-container {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            text-align: center;
        }
        .message-container h2 {
            color: #4CAF50;
        }
    </style>
    <script>
        setTimeout(function() {
            window.location.href = "index.html";
        }, 3000);
    </script>
</head>
<body>
    <div class="message-container">
        <h2>¡Usuario creado exitosamente!</h2>
        <p><%= request.getAttribute("mensaje") %></p>
        <p>Redirigiendo al inicio en 3 segundos...</p>
    </div>
</body>
</html>