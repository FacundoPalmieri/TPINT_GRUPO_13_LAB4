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
    table {
        width: 100%;
        border-collapse: collapse;
    }
    table, th, td {
        border: 1px solid black;
    }
    th, td {
        padding: 15px;
        text-align: left;
    }
    th {
        background-color: #f2f2f2;
    }
</style>
</head>
<body>
	<% if(session.getAttribute("tipoUsuario")!=null){%>
    <h1>Listado de Clientes</h1>
    <a href="UsuarioAdministrador.jsp">Volver</a>
    <table>
        <tr>
            <th>ID</th>
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
            <td><%= cliente.getId() %></td>
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
    <%}else{%>
 	<h1>No tiene permisos para trabajar en esta URL, presione <a href="Login.jsp">aquí</a> para volver al Login</h1>
 <%}%>
</body>
</html>