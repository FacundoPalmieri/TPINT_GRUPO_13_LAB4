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
		<a href="ABMLclientes.jsp" class="botonera boton-link" style="margin: 5px;">ABML Clientes</a>
 		<a href="ABMLcuentas.jsp" class="botonera boton-link" style="margin: 5px;">ABML Cuentas</a>        
        <a href="ServletPrestamo?PrestamoAdmin=1" class="botonera boton-link" style="margin: 5px;">Préstamos</a>
    </div>
    
	 <%}else{%>
	 	<h1>No tiene permisos para trabajar en esta URL, presione <a href="Login.jsp">aquí</a> para volver al Login</h1>
	 <%}%>
	 
	</div>
</div>
<script src="js/scripts.js"></script>
</body>
</html>