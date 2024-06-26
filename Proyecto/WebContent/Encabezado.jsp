<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>

<div style="position: fixed; top: 10px; right: 10px;">




    <h2>Hola, <%= request.getAttribute("nombre") %>!</h2>
 

<a href="ServletCerrarSesion">Cerrar sesión</a>
</div>
</html>