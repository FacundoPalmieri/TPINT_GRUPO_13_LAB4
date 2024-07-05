<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Administrador</title>
<style type="text/css">
	<jsp:include page="css\Style.css"></jsp:include>
</style>
</head>
<body>

<% if(session.getAttribute("tipoUsuario")!=null){%>
	

	
<div id="General">
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
        <input type="submit" value="ABML Clientes" name="btnABMLcliente" onclick="window.location.href='ABMLclientes.jsp';" class="botonera">
        <input type="submit" value="ABML Cuentas" name="btnABMLcuenta" onclick="window.location.href='ABMLcuentas.jsp';" class="botonera">
        <input type="submit" value="Prestamos" name="btnAdminPrestamos" onclick="window.location.href='AdminPrestmos.jsp';" class="botonera">
    </div>
    <div class="button-container"> <input type="button" value="Volver" name="btnVolver" onclick="window.location.href='Login.jsp';"> </div>
    


	 <%}else{%>
	 	<h1>No tiene permisos para trabajar en esta URL, presione <a href="Login.jsp">aquí</a> para volver al Login</h1>
	 <%}%>
	 
	</div>
</div>
<script src="js/scripts.js"></script>
</body>
</html>