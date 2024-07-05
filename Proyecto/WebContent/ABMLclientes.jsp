<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
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
	    <h3>ABML Clientes</h3>
	</div>
	<div class="logo_encabezado_derecha">
	    <%= (String) session.getAttribute("usuario") %>
	    <a href="ServletCerrarSesion" class="logout">
	        <img src="img/logout.png" alt="Logout" class="logo_encabezado">
	    </a>
	</div>

	</div>

 	<div class="button-container">
 		<form method="get" action="AltaCliente">
   		 <input type="submit" value="Agregar Clientes" name="btnAgregarCliente" class="botonera">
		</form>
		  <input type="submit" value="Modificar Cliente" name="btnModificarCliente" onclick="window.location.href='ModificarCliente.jsp';" class="botonera">
          <input type="submit" value="Eliminar Cliente" name="btnEliminarCliente" onclick="window.location.href='EliminarCliente.jsp';" class="botonera">
          
		<form method="get" action="ServletUsuario">
        	<input type="submit" value="Listar Clientes" name="btnListarCliente" onclick="window.location.href='ListarClientes.jsp';" class="botonera">
        </form>

	</div>
		 <div style = "display: flex; justify-content: center;" >
 			 <input type="button" value="Volver" name="btnVolver" onclick="window.location.href='UsuarioAdministrador.jsp';">
		</div>
    
 <%}else{%>
 	<h1>No tiene permisos para trabajar en esta URL, presione <a href="Login.jsp">aquí</a> para volver al Login</h1>
 <%}%>
 
</div> 
<script src="js/scripts.js"></script>
</body>
</html>