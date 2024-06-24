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
	
<div class="container">
        <h1>Bienvenido Administrador</h1>
        
        <div class="section">
            <h1>Listar Clientes <a href="ServletUsuario?Param=1">aqu�</a></h1>
        </div>
        
        <div class="section">
            <h1>Agregar Clientes <a href="AgregarCliente.jsp">aqu�</a></h1>
        </div>      
    </div>
 <%}else{%>
 	<h1>No tiene permisos para trabajar en esta URL, presione <a href="Login.jsp">aqu�</a> para volver al Login</h1>
 <%}%>
 
 
<script src="js/scripts.js"></script>
</body>
</html>