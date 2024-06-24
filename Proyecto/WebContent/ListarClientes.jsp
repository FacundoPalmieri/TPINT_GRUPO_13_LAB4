<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.List"%>
<%@page import="entidad.Usuario"%>
<%@page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Listado de Clientes</title>
<style>
	<jsp:include page="css\Style.css"></jsp:include>
</style>

<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">
	
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript" charset="utf8"
	src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$('#table_id').DataTable();
	});
</script>
</head>
<body>
	<% if(session.getAttribute("tipoUsuario")!=null){%>
    <div class="banner">
  <h2> Listado de Clientes</h2>
</div>
<div style= "margin-top: 10px;">
   <table id="table_id" class="display">
        <tr>
             <%--   <th>ID</th>  --%>
            <th>DNI</th>
            <th>CUIL</th>
            <th>Nombre</th>
            <th>Apellido</th>
            <th>Sexo</th>
            <th>Nacionalidad</th>
            <th>Fecha de Nacimiento</th>
            <th>Dirección</th>
            <th>Provincia</th>
            <th>Localidad</th>
            <th>Email</th>
            <th>Usuario</th>
        </tr>
        <%
        	ArrayList<Usuario> listaUsuarios = null;
            listaUsuarios = (ArrayList<Usuario>)request.getAttribute("listaUsuarios");
            if (listaUsuarios != null) {
                for (Usuario cliente : listaUsuarios) {
        %>
        <tr>
           <%-- <td><%= cliente.getId() %></td>  --%>
            <td><%= cliente.getDni() %></td>
            <td><%= cliente.getCuil() %></td>
            <td><%= cliente.getNombre() %></td>
            <td><%= cliente.getApellido() %></td>
            <td><%= cliente.getSexo() %></td>
            <td><%= cliente.getNacionalidad() %></td>
            <td><%= cliente.getFechaNacimiento() %></td>
            <td><%= cliente.getDireccion() %></td>
            <td><%= cliente.getProvincia() %></td>
            <td><%= cliente.getLocalidad() %></td>
            <td><%= cliente.getEmail() %></td>
            <td><%= cliente.getUsuario() %></td>
        </tr>
        <%
                }
            } else {
        %>
   
        <%
            }
        %>
       
    </table>
    <input type="button" value="Volver" name="btnVolver" onclick="window.location.href='ABMLclientes.jsp';">
    <%}else{%>
 	<h1>No tiene permisos para trabajar en esta URL, presione <a href="Login.jsp">aquí</a> para volver al Login</h1>
 <%}%>
 
 </div>
</body>
</html>