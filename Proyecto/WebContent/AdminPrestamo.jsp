<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="entidad.Prestamo"%>
<%@ page import="entidad.EstadoPrestamo"%>
<%@ page import="entidad.Persona"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Actualizar Estado de Préstamos</title>
    <style type="text/css">
        <%@ include file="css/Style.css" %>
    </style>
    <script type="text/javascript">
    function mostrarMensajeCambio(idPrestamo, dniCliente, importeSolicitado, nuevoEstado) {
        var select = document.getElementById('estadoPrestamo_' + idPrestamo);
        var selectedOption = select.options[select.selectedIndex].text;

        var mensaje = '¿Estás seguro de cambiar el estado del préstamo a "' + selectedOption + '"?';
        showPopup('popupMensaje', mensaje, idPrestamo, dniCliente, importeSolicitado, nuevoEstado);
    }

    function confirmarCambio(idPrestamo, dniCliente, importeSolicitado, nuevoEstado) {
        // Aquí puedes realizar validaciones adicionales antes de confirmar el cambio si es necesario
        actualizarEstado(idPrestamo, dniCliente, importeSolicitado, nuevoEstado);
    }

    function actualizarEstado(idPrestamo, dniCliente, importeSolicitado, nuevoEstado) {
        // Aquí puedes realizar la actualización del estado del préstamo en el servidor
        var url = "ServletPrestamo?idPrestamo=" + idPrestamo + "&estadoPrestamo=" + nuevoEstado + "&dniCliente=" + dniCliente + "&importeSolicitado=" + importeSolicitado;

        // Redirigir al servlet para actualizar el estado
        window.location.href = url;
    }

    function showPopup(popupId, message, idPrestamo, dniCliente, importeSolicitado, nuevoEstado) {
        var popup = document.getElementById(popupId);
        if (message) {
            document.getElementById('popupMessage').innerText = message;
        }
        var aceptarButton = document.getElementById('AceptarPrestamo');
        aceptarButton.setAttribute('onclick', 'confirmarCambio(' + idPrestamo + ', "' + dniCliente + '", "' + importeSolicitado + '", ' + nuevoEstado + ')');
        popup.classList.add("active");
    }

    function closePopup(popupId) {
        var popup = document.getElementById(popupId);
        popup.classList.remove("active");
    }


    </script>
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
                   ArrayList<EstadoPrestamo> listaEstadosPrestamo = (ArrayList<EstadoPrestamo>) request.getAttribute("listaEstadosPrestamo");
                    if (listaPrestamos != null) {
                        for (Prestamo prestamo : listaPrestamos) { 
                            %>
                            <tr>
                                <td><%= prestamo.getFecha() %></td>
                                <td><%= prestamo.getClienteDni().getDni() %></td>
                                <td><%= prestamo.getImporteSolicitado() %></td>
                                <td><%= prestamo.getImporteAPagar() %></td>
                                <td><%= prestamo.getCuotas() %></td>
                                <td><%= prestamo.getImporteCuota() %></td>
                                <td>
 								 <select id="estadoPrestamo_<%= prestamo.getId() %>" onchange="mostrarMensajeCambio('<%= prestamo.getId() %>', '<%= prestamo.getClienteDni().getDni() %>','<%= prestamo.getImporteSolicitado() %>', this.value)">            
 								    <% for (EstadoPrestamo estado : listaEstadosPrestamo) { %>
                                            <option value="<%= estado.getId() %>" <%= prestamo.getEstado().getId() == estado.getId() ? "selected" : "" %>>
                                                <%= estado.getDescripcion() %>
                                            </option>
                                        <% } %>
                                    </select>
                                </td>
                            </tr>
                        <% 
                        }
                    } else {
                %>
                <tr>
                    <td colspan="7">No tiene préstamos actuales</td>
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
                <button id="AceptarPrestamo" name="AceptarPrestamo">Aceptar</button>
            </div>
        </div>
    </div>
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

    window.onload = function() {
        // Obtenemos el mensaje de error desde el servidor
        var errorMensaje = "<%= (request.getAttribute("Mensaje") != null) ? request.getAttribute("Mensaje") : "" %>";
        if (errorMensaje) {
            showPopup(errorMensaje);
           
        }|
    };
</script>

</body>
</html>