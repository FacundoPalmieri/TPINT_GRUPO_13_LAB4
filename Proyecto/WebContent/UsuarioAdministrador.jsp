<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<style type="text/css">
	<jsp:include page="css\Style.css"></jsp:include>
</style>
</head>
<body>

<% if(session.getAttribute("tipoUsuario")!=null){%>
<div id="General">
        <div class="banner">
            <div class="logo_encabezado_izquierda">
                <img src="img/Grupo 13_encabezado.png" alt="Logo" class="logo_encabezado">
                <h3>Gestión Admin</h3>
            </div>
            <div class="logo_encabezado_derecha">
                <%= (String) session.getAttribute("usuario") %>
                <a href="ServletCerrarSesion" class="logout">
                    <img src="img/logout.png" alt="Logout" class="logo_encabezado">
                </a>
            </div>
        </div>

        <div class="button-container">
            <a href="ABMLclientes.jsp" class="botonera boton-link">ABML Clientes</a>
            <a href="ABMLcuentas.jsp" class="botonera boton-link">ABML Cuentas</a>
            <a href="ServletPrestamo?PrestamoAdmin=1" class="botonera boton-link">Préstamos</a>
        </div>


<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Formulario de Reporte de Préstamos</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            padding: 20px;
        }

        .formulario-container {
            max-width: 600px;
            margin: 0 auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }

        .formulario-container h4 {
            text-align: center;
            margin-bottom: 20px;
        }

        .form-group {
            margin-bottom: 15px;
        }

        .form-group label {
            display: block;
            font-weight: bold;
            margin-bottom: 5px;
        }

        .form-group input[type="text"],
        .form-group input[type="month"] {
            width: calc(100% - 12px);
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
            font-size: 14px;
        }

        .form-group input[type="checkbox"] {
            margin-right: 5px;
            vertical-align: middle;
        }

        .submit-container {
            text-align: center;
        }

        .submit-container input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }

        .submit-container input[type="submit"]:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <div class="formulario-container">
        <form id="formulario1" action="ServletReportes" method="post">
            <h4>Reporte Préstamos</h4>
            <div class="form-group">
                <label for="dniCliente">DNI:</label>
                <input type="text" id="dniCliente" name="dniCliente" required>
                <% if(request.getAttribute("errorDni")!=null){ %>
                	<p><%= request.getAttribute("errorDni") %></p>
               <%} %>
            </div>
            <div class="form-group">
                <label>Estado Préstamos:</label><br>
                <input type="checkbox" id="opcion1" name="opciones" value="1">
                <label for="opcion1">Solicitado</label>
                <input type="checkbox" id="opcion2" name="opciones" value="2">
                <label for="opcion2">En Análisis</label>
                <input type="checkbox" id="opcion3" name="opciones" value="3">
                <label for="opcion3">Aprobado</label>
                <input type="checkbox" id="opcion4" name="opciones" value="4">
                <label for="opcion4">Rechazado</label>
                <input type="checkbox" id="opcion5" name="opciones" value="5">
                <label for="opcion5">Abonado</label>
            </div>
            <div class="form-group">
                <label for="fecha1">Desde:</label>
                <input type="date" id="fecha1" name="fecha1" required>
            </div>
            <div class="form-group">
                <label for="fecha2">Hasta:</label>
                <input type="date" id="fecha2" name="fecha2" required>
                <% if(request.getAttribute("errorFecha")!=null){ %>
                	<p><%= request.getAttribute("errorFecha") %></p>
               <%} %>
            </div>
            <div class="submit-container">
                <input type="submit" value="Generar Reporte" name="btnReporte">
            </div>
        </form>
    </div>
</body>
</html>








    
<%}else{%>
	 	<h1>No tiene permisos para trabajar en esta URL, presione <a href="Login.jsp">aquí</a> para volver al Login</h1>
	 <%}%>
<script src="js/scripts.js"></script>
</body>
</html>