<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="entidad.Prestamo"%>
<%@ page import="entidad.EstadoPrestamo"%>
<%@ page import="entidad.Persona"%>
<%@ page import="entidad.PagosPrestamos"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Reporte de Prestamos</title>
    <style type="text/css">
        <%@ include file="css/Style.css" %>
    </style>
</head>
<body>
<% if (session.getAttribute("tipoUsuario") != null) { %>
<div id="General">
    <div class="banner">
        <div class="logo_encabezado_izquierda">
            <img src="img/Grupo 13_encabezado.png" alt="Logo" class="logo_encabezado">
            <h3>Prestamos</h3>
        </div>
        <div class="logo_encabezado_derecha">
            <%= (String) session.getAttribute("usuario") %>
            <a href="ServletCerrarSesion" class="logout">
                <img src="img/logout.png" alt="Logout" class="logo_encabezado">
            </a>
        </div>
    </div>
    <div>
        <h3 style="display:flex; justify-content: center;">Reporte</h3>
        <table id="table_id" class="display">
            <thead>
                <tr>
                    <th>Fecha</th>
                    <th>Cliente</th>
                    <th>DNI</th>
                    <th>Importe Solicitado</th>
                    <th>Importe a Pagar</th>
                    <th>Cuotas</th>
                    <th>Importe por Cuota</th>
                    <th>Estado Prestamo</th>
                    <th>Cuotas Pagas</th>
                </tr>
            </thead>
            <tbody>
                <%  
                ArrayList<Prestamo> listaPrestamos = (ArrayList<Prestamo>) request.getAttribute("listaPrestamos");
                int cuotasPagas=0;
                if (listaPrestamos != null) {
                        for (Prestamo prestamo : listaPrestamos) { 
                %>
                            <tr>
                                <td><%= prestamo.getFecha() %></td>
                                <td><%= prestamo.getClienteDni().getApellido() %>, <%= prestamo.getClienteDni().getNombre() %></td>
                                <td><%= prestamo.getClienteDni().getDni() %></td>
                                <td><%= prestamo.getImporteSolicitado() %></td>
                                <td><%= prestamo.getImporteAPagar() %></td>
                                <td><%= prestamo.getCuotas() %></td>
                                <td><%= prestamo.getImporteCuota() %></td>
                                <td><%= prestamo.getEstado().getDescripcion() %> </td>
                                <td class="invisible"><%= prestamo.getId() %></td>
          
                                <% 
                                	if(prestamo.getEstado().getId()==3 || prestamo.getEstado().getId()==5){ 
                                		for(PagosPrestamos p : prestamo.getPagosPrestamos()){
                                			if(p.getEstado()!=1){
                                				cuotasPagas++;
                                			}
                                		}
                                	}%>
                                <td><%= cuotasPagas %></td>	
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
    <div class="button-container">
        <input type="button" value="Volver" name="btnVolver" onclick="window.location.href='UsuarioAdministrador.jsp';">
    </div>
</div>
<% } else { %>
    <div class="fullscreen-gif">
        <img src="img/No tiene permiso.gif" id="gift-ingreso-prohibido">
    </div>
<% } %>
<script type="text/javascript">
    document.addEventListener("DOMContentLoaded", function() {
        var selectElements = document.querySelectorAll("select[id^='estadoPrestamo_']");
        selectElements.forEach(function(selectElement) {
            var estadoActual = parseInt(selectElement.getAttribute("data-estado-actual"));
            if (estadoActual === 3 || estadoActual === 4 || estadoActual === 5) {
                selectElement.setAttribute("disabled", "true");
            }
        });
    });

    function submitForm() {
        closeConfirmPopup();
        document.forms[0].submit();
    }

    window.onload = function() {
        var errorMensaje = '<%= (request.getAttribute("Mensaje") != null) ? request.getAttribute("Mensaje") : "" %>';
        if (errorMensaje) {
            showResultPopup(errorMensaje);
        }
    };
</script>
</body>
</html>