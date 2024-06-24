<%@page import="java.util.ArrayList" %>
<%@page import="entidad.Usuario" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<style type="text/css">
    .error {
            color: red;
        font-weight: bold;
        font-size: 20px; 
        position: absolute;
        bottom: 40px;
        right: 40px;
    }
    <jsp:include page="css/Style.css"></jsp:include>
</style>

<title>Agregar Cliente</title>
</head>
<body>

<div id="General">
<div class="banner">
  <h2>�Cre� tu usuario!</h2>
</div>
 

	<form action="AltaCliente" method="post">
    <button type="button" class="accordion">Informaci�n Personal &#x1F4DD;</button>
    <div class="panel">
     <div class="flex-container">
        <div class="form-group flex-item" style= "margin-top: 10px;">
            <label for="dni">DNI:</label>
            <input type="text" id="dni" name="dni" required>
        </div>
           <div class="form-group flex-item" style= "margin-top: 10px;">
            <label for="cuil">CUIL:</label>
            <input type="text" id="cuil" name="cuil" required>
        </div>
        <div class="form-group flex-item">
            <label for="nombre">Nombre:</label>
            <input type="text" id="nombre" name="nombre" required>
        </div>
       <div class="form-group flex-item">
            <label for="apellido">Apellido:</label>
            <input type="text" id="apellido" name="apellido" required>
        </div>
       <div class="form-group flex-item">
            <label for="sexo">Sexo:</label>
            <input type="text" id="sexo" name="sexo" required>
        </div>
        <div class="form-group flex-item">
            <label for="fechaNacimiento">Fecha de Nacimiento:</label>
            <input type="date" id="fechaNacimiento" name="fechaNacimiento" required>
        </div>
        <div class="form-group flex-item">
            <label for="nacionalidad">Nacionalidad:</label>
            <input type="text" id="nacionalidad" name="nacionalidad" required>
        </div>
        </div>
    </div>

    <button type="button" class="accordion">Domicilio  &#x1F3E0;</button>
    <div class="panel">
  	  <div class="flex-container">
        <div class="form-group flex-item" style= "margin-top: 10px;">
         <label for="localidad">Localidad:</label>
            <input type="text" id="localidad" name="localidad" required>   
        </div>
        <div class="form-group flex-item" style= "margin-top: 10px;">
        <label for="provincia">Provincia:</label>
            <input type="text" id="provincia" name="provincia" required>  
        </div>
        <div class="form-group flex-item">
           <label for="direccion">Direcci�n:</label>
            <input type="text" id="direccion" name="direccion" required> 
        </div>
          </div>
            <p style="font-size: smaller; font-weight: bold;">* Aclarar piso y departamento en caso de corresponder</p>

    </div>
    
    
    <button type="button" class="accordion">Informaci�n de Contacto &#x1F4F1;</button>
    <div class="panel">
      <div class="flex-container">
         <div class="form-group flex-item" style= "margin-top: 10px;">
           <label for="Celular">Celular:</label>
            <input type="text" id="celular" name="celular" required>
        </div>
       <div class="form-group flex-item" style= "margin-top: 10px;">
            <label for="telefonos">Tel�fono:</label>
            <input type="text" id="telefonos" name="telefonos" required>
        </div>
        <div class="form-group  flex-item">
         <label for="correoElectronico">Correo Electr�nico:</label>
            <input type="email" id="correoElectronico" name="correoElectronico" required>
        </div>
      </div>
    </div>


    <button type="button" class="accordion">Datos de Usuario &#x1F511;</button>
    <div class="panel">
         <div class="form-group" style= "margin-top:10px;">
            <label for="usuario">Usuario:</label>
            <input type="text" id="usuario" name="usuario" required>
        </div>
        <div class="form-group flex-item">
            <label for="contrasena">Contrase�a:</label>
            <input type="password" id="contrasena" name="contrasena" required>
        </div>
      
    </div>

    <input type="submit" value="Aceptar" name="btnAceptar">
    <input type="button" value="Volver" name="btnVolver" onclick="window.location.href='Login.jsp';">

	</form>

	    	
    <div id="popup" class="popup">
        <span class="close-btn" onclick="closePopup()">&times;</span>
        <p id="popupMessage"></p>
   </div>
	<%
	    Boolean estadoCliente = (Boolean) request.getAttribute("estadoCliente");
		Boolean validacion = (Boolean) request.getAttribute("validacionCliente");

	    System.out.println("Estado Cliente: " + estadoCliente);
	    System.out.println("Validaci�n Cliente: " + validacion);

	    if (validacion != null) {
	%>
	    <script>
	        document.addEventListener('DOMContentLoaded', function() {
	            <% if (validacion == false) { %>
	                showPopup("Usuario ya existente.");
	            <% } else { %>
	                showPopup("<%= estadoCliente != null && estadoCliente ? "Usuario agregado con �xito" : "Usuario no agregado, vuelva a intentarlo." %>");
	            <% } %>
	        });
	    </script>
	<%
	    }
	%>
</div>


<script>
    var acc = document.getElementsByClassName("accordion");
    var i;

    for (i = 0; i < acc.length; i++) {
        acc[i].addEventListener("click", function() {
            this.classList.toggle("active");
            var panel = this.nextElementSibling;
            if (panel.style.maxHeight) {
                panel.style.maxHeight = null;
            } else {
                panel.style.maxHeight = panel.scrollHeight + "px";
            } 
        });
    }
    
    
    //funcionalidad pop up
    
    function showPopup(message) {
        var popup = document.getElementById("popup");
        var popupMessage = document.getElementById("popupMessage");
        popupMessage.innerText = message;
        popup.classList.add("active");
    }

    function closePopup() {
        var popup = document.getElementById("popup");
        popup.classList.remove("active");
    }
</script>
</body>
</html>