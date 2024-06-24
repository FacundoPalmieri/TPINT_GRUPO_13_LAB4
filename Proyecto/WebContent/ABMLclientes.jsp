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
  <h2> ABML Clientes</h2>
</div>
<form method="get" action="ServletUsuario">
    <div class="button-container">
        <input type="submit" value="Listar Clientes" name="btnListarCliente" onclick="window.location.href='ListarClientes.jsp';">
        <input type="submit" value="Buscar Cliente" name="btnBuscarCliente" >
    </div>
</form>
 <div style = "display: flex; justify-content: center;" >
  <input type="button" value="Volver" name="btnVolver" onclick="window.location.href='UsuarioAdministrador.jsp';">
</div>
    
 <%}else{%>
 	<h1>No tiene permisos para trabajar en esta URL, presione <a href="Login.jsp">aqu�</a> para volver al Login</h1>
 <%}%>
 
</div> 
<script src="js/scripts.js"></script>
</body>
</html>