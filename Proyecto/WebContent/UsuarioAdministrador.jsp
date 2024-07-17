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

	
	 <div class="formulario-container">
            <form id="formulario1" action="ServletReportes" method="post">
                <h4>Reporte Prestamos</h4>
                <div class="form-group">
                    <label for="dniCliente">DNI:</label>
                    <input type="text" id="dniCliente" name="dniCliente" required>
                </div>
	            <div class="form-group">
		            <label>Estado Prestamos:</label><br>
		            <input type="checkbox" id="opcion1" name="opciones" value="1">
		            <label for="opcion1">Solicitado</label><br>
		            <input type="checkbox" id="opcion2" name="opciones" value="2">
		            <label for="opcion2">En Analisis</label><br>
		            <input type="checkbox" id="opcion3" name="opciones" value="3">
		            <label for="opcion3">Aprobado</label><br>
		            <input type="checkbox" id="opcion4" name="opciones" value="4">
		            <label for="opcion4">Rechazado</label><br>
		            <input type="checkbox" id="opcion5" name="opciones" value="5">
		            <label for="opcion5">Abonado</label><br>
	        	</div>
                <div class="form-group">
                    <label for="fecha1">Desde:</label>
                    <input type="month" id="fecha1" name="fecha1" required>
                </div>
                <div class="form-group">
                    <label for="fecha2">Hasta:</label>
                    <input type="month" id="fecha2" name="fecha2" required>
                </div>
                <div class="submit-container">
                    <input type="submit" value="Generar Reporte" name="btnReporte" >
                </div>
            </form>
        </div>
    </div>
    
<%}else{%>
	 	<h1>No tiene permisos para trabajar en esta URL, presione <a href="Login.jsp">aquí</a> para volver al Login</h1>
	 <%}%>
<script src="js/scripts.js"></script>
</body>
</html>