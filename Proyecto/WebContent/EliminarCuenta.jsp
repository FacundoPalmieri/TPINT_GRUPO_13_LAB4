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
	   Cuenta cuenta = new Cuenta();

	  
	   persona = (Persona)request.getAttribute("persona");
	   cuenta  = (Cuenta)request.getAttribute("cuenta");

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
	        <div class="form-item" style="margin-top: 10px;">
	        	<label for="DNI">DNI: </label>
	            <input type="text" id="DNI" name="DNI"  value="">
	        </div>
	        <div class="form-item" style="margin-top: 10px;">
	        	<label for="cuentaOrigen">Cuenta origen:</label>
				<select name="cuentaOrigen" id="cuentaOrigen" class="styled-select">
				<%  
					ArrayList<Cuenta> listaCuentas = null;
		            listaCuentas = (ArrayList<Cuenta>) request.getAttribute("listaCuentas");
				    	if (listaCuentas != null) {
				        	for (Cuenta cuentaOrigen : listaCuentas) {
				%>
				<option value="<%= cuentaOrigen.getNumeroCuenta()%>-<%=cuentaOrigen.getSaldo()%>">Cuenta: <%= cuentaOrigen.getIdTipoCuenta().getDescripcion() %> - <%= cuentaOrigen.getCbu() %>, Saldo: $<%= cuentaOrigen.getSaldo() %></option>
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
            	<input type="submit" name="btnEliminarCuenta" value="Transferir" style="margin-right: 5px; margin-left: 0px !important;">
                <input type="button" value="Volver" name="btnVolver" onclick="window.location.href='ABMLcuentas.jsp';">
            </div>
    	</div>
    </form>

</body>
</html>