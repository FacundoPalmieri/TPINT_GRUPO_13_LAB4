<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="entidad.Prestamo"%>
<%@ page import="entidad.Persona"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<style type="text/css">
	<jsp:include page="css/Style.css"></jsp:include>
</style>
</head>
<body>

<% if(session.getAttribute("tipoUsuario") != null){ %>

<div id="General">
	<div class="banner">
		<div class="logo_encabezado_izquierda">
			<img src="img/Grupo 13_encabezado.png" alt="Logo" class="logo_encabezado">
			<h3>Préstamos</h3>
		</div>
		<div class="logo_encabezado_derecha">
			<%= (String) session.getAttribute("usuario") %>
			<a href="ServletCerrarSesion" class="logout">
				<img src="img/logout.png" alt="Logout" class="logo_encabezado">
			</a>
		</div>
	</div>
	<form action="ServletPrestamo" method="post" >
	<div>
	<h3 style="display:flex; justify-content: center;">Préstamos solicitados</h3>
		<table id="table_id" class="display">
			<thead>
            	<tr>
                	<th>Fecha</th>
                	<th>Cliente</th>
                    <th>Importe Solicitado</th>
                    <th>Importe a Pagar</th>
                    <th>Cuotas</th>
                    <th>Importe por Cuota</th>
                    <th>Actualizar Estado</th>
                    
                </tr>
            </thead>
            <tbody>
                <%  	
                    ArrayList<Prestamo> listaPrestamos = (ArrayList<Prestamo>) request.getAttribute("listaPrestamos");
                    if (listaPrestamos != null) {
                        for (Prestamo prestamo : listaPrestamos) { 
                        	System.out.println(prestamo);
                    
                    %>
                        
                        
                        
                    <tr>
                        <td><%= prestamo.getFecha() %></td>
                        <td><%= prestamo.getClienteDni().getDni() %></td>
                        <td><%= prestamo.getImporteSolicitado() %></td>
                        <td><%= prestamo.getImporteAPagar() %></td>
                        <td><%= prestamo.getCuotas() %></td>
                        <td><%= prestamo.getImporteCuota() %></td>
                        <td>  
							<select id="estadoPrestamo" name="estadoPrestamo" onchange="mostrarMensajeCambio()" required>
							    <option value="<%= prestamo.getEstado().getId() %>" selected><%= prestamo.getEstado().getDescripcion() %></option>
							    <option value="1">Aceptar</option>
							    <option value="2">Rechazar</option>
							</select>

						</td>                 
                    </tr>
                <% 
                        }
                    } else {
                %>
                <tr>
                    <td colspan="8">No tiene préstamos actuales</td>
                </tr>
                <% 
                    }
                %>
            </tbody>
        </table>
	</div> 
		<div id="popupMensaje" class="popup">
	    <div class="popup-content">
	        <span id="popupMessage"></span>
	        <div class="popup-buttons">
	            <button onclick="closePopup('popupMensaje')">Cancelar</button>
	            <button onclick="submitForm()">Aceptar</button>
	        </div>
	    </div>
	</div>
	
	</form>
    <div class="button-container">
        <input type="button" value="Volver" name="btnVolver" onclick="window.location.href='UsuarioAdministrador.jsp';">
    </div>
</div>

<% } else { %>
	<h1>No tiene permisos para trabajar en esta URL, presione <a href="Login.jsp">aquí</a> para volver al Login</h1>
<% } %>

<script type="text/javascript">

	function submitForm() {
	    closePopup('popupMensaje');
	    document.forms[0].submit(); // Envía el formulario actual
	}
    function mostrarMensajeCambio() {
        var select = document.getElementById('estadoPrestamo');
        var selectedOption = select.options[select.selectedIndex].text;

        var mensaje = '¿Estás seguro de cambiar el estado del préstamo a "' + selectedOption + '"?';
        showPopup('popupMensaje', mensaje);
    }

    function showPopup(popupId, message) {
        var popup = document.getElementById(popupId);
        if (message) {
            document.getElementById('popupMessage').innerText = message;
        }
        popup.classList.add("active");
    }

    function closePopup(popupId) {
        var popup = document.getElementById(popupId);
        popup.classList.remove("active");
    }
</script>

</body>
</html>
