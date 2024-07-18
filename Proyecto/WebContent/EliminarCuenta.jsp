<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="entidad.Cuenta"%>
<%@page import="entidad.Persona"%>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
    <jsp:include page="css\Style.css"></jsp:include>
</style>
</head>
<body>

    <div class="banner">
        <div class="logo_encabezado_izquierda">
            <img src="img/Grupo 13_encabezado.png" alt="Logo" class="logo_encabezado">
            <h3>ABML Cuentas</h3>
        </div>
        <div class="logo_encabezado_derecha">
            <%= (String) session.getAttribute("usuario") %>
            <a href="ServletCerrarSesion" class="logout">
                <img src="img/logout.png" alt="Logout" class="logo_encabezado">
            </a>
        </div>
    </div>
    
    
	<%
	   Persona persona = new Persona();
	   persona = (Persona)request.getAttribute("persona");

	%>
    
    
    <form id="ServletCuentas" action="ServletCuentas" method="get">	
	    <div id="BusquedaCliente" class="form">
	        <input type="text" id="DNICliente" name="DNICliente" placeholder="Ingrese DNI" value="<%= (request.getParameter("DNICliente") != null) ? request.getParameter("DNICliente") : "" %>" required>
	        <input type="submit" value="Buscar" name="btnBuscarCuentas" style="background-color: #78AD89">
	    </div>
	    <div id="ResultadoBusqueda">
	    	<div class="form-item" style="margin-top: 10px;">
	        	<label for="Cliente">Apellido y Nombre: </label>
	            <input type="text" id="Cliente" name="Cliente"  value="<%= (persona != null && persona.getNombre() != null ? persona.getNombre() : "") + " " + (persona != null && persona.getApellido() != null ? persona.getApellido() : "") %>">
	        </div>
	     
	 </form>
	 
	    <form id="ServletCuentas" action="ServletCuentas" method="post">	
	        <div class="form-item" style="margin-top: 40px; ">
	        	<label for="cuenta">Cuenta a eliminar:</label>
				<select name="cuenta" id="cuenta" class="styled-select">
				<%  
					ArrayList<Cuenta> listaCuentas = null;
		            listaCuentas = (ArrayList<Cuenta>) request.getAttribute("listaCuentas");
				    	if (listaCuentas != null) {
				        	for (Cuenta cuenta : listaCuentas) {
				%>
				<option value="<%= cuenta.getNumeroCuenta()%>-<%=cuenta.getClienteDni() %>">Cuenta: <%= cuenta.getIdTipoCuenta().getDescripcion() %> - <%= cuenta.getCbu() %>, Saldo: $<%= cuenta.getSaldo() %></option>
				<% 
				}
				} else {
				%>
					<option value="">No tiene cuentas disponibles</option>
				<% 
				}
				%>
				</select>
			</div>
                
            <div class="center-container">
            	<input type="submit" name="btnEliminarCuentas" value="Eliminar" style="margin-right: 2%; margin-left: 0px !important; background-color: #dc3545">
                <input type="button" value="Volver" name="btnVolver" onclick="window.location.href='ABMLcuentas.jsp';">
            </div>
    </form>
    
    
    <div id="popup" class="popup">
	<span class="close-btn" onclick="closePopup()">&times;</span>
	<p id="popupMessage"></p>
	</div>
    
<script>

document.addEventListener('DOMContentLoaded', function() {
    <% 
        String mensaje = (String) request.getAttribute("Mensaje");
  		  if (mensaje != null) { 
    %>
  	
     	showPopup("<%= mensaje%>");
     <% 
          } 
     %>
  });




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