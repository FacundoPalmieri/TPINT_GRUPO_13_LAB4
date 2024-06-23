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
            <h2>Clientes</h2>
            <ul class="options hidden">
                <li><a href="gestionClientes?action=list">Listar Clientes</a></li>
                <li><a href="gestionClientes?action=add">Agregar Cliente</a></li>
                <li><a href="gestionClientes?action=edit">Editar Cliente</a></li>
                <li><a href="gestionClientes?action=delete">Eliminar Cliente</a></li>
            </ul>
        </div>
        
        <div class="section">
            <h2>Cuentas</h2>
            <ul class="options hidden">
                <li><a href="gestionCuentas?action=list">Listar Cuentas</a></li>
                <li><a href="gestionCuentas?action=add">Agregar Cuenta</a></li>
                <li><a href="gestionCuentas?action=edit">Editar Cuenta</a></li>
                <li><a href="gestionCuentas?action=delete">Eliminar Cuenta</a></li>
            </ul>
        </div>
        
        <div class="section">
            <h2>Pr�stamos</h2>
            <ul class="options hidden">
                <li><a href="gestionPrestamos?action=list">Listar Pr�stamos</a></li>
                <li><a href="gestionPrestamos?action=add">Agregar Pr�stamo</a></li>
                <li><a href="gestionPrestamos?action=edit">Editar Pr�stamo</a></li>
                <li><a href="gestionPrestamos?action=delete">Eliminar Pr�stamo</a></li>
            </ul>
        </div>
        
        <div class="section">
            <h2>Informes</h2>
            <ul class="options hidden">
                <li><a href="generarInforme?type=clientes">Informe de Clientes</a></li>
                <li><a href="generarInforme?type=cuentas">Informe de Cuentas</a></li>
                <li><a href="generarInforme?type=prestamos">Informe de Pr�stamos</a></li>
            </ul>
        </div>
        
        
        
    </div>
 <%}else{%>
 	<h1>No tiene permisos para trabajar en esta URL, presione <a href="Login.jsp">aqu�</a> para volver al Login</h1>
 <%}%>
 
 
<script src="js/scripts.js"></script>
</body>
</html>