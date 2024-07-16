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
function mostrarMensajeCambio(idPrestamo, dniCliente, importeSolicitado, numeroCuenta, importeCuota, cuotas, nuevoEstado) {
    var select = document.getElementById('estadoPrestamo_' + idPrestamo);
    var selectedOption = select.options[select.selectedIndex].text;

    var mensaje = 'Esta seguro de cambiar el estado del prestamo a "' + selectedOption + '"?';
    showConfirmPopup(mensaje, idPrestamo, dniCliente, importeSolicitado, numeroCuenta, importeCuota, cuotas, nuevoEstado);
}

function confirmarCambio(idPrestamo, dniCliente, importeSolicitado, numeroCuenta, importeCuota, cuotas, nuevoEstado) {
    // Aqu� puedes realizar validaciones adicionales antes de confirmar el cambio si es necesario
    actualizarEstado(idPrestamo, dniCliente, importeSolicitado, numeroCuenta, importeCuota, cuotas, nuevoEstado);
}

function actualizarEstado(idPrestamo, dniCliente, importeSolicitado, numeroCuenta, importeCuota, cuotas, nuevoEstado) {
    // Aqu� puedes realizar la actualizaci�n del estado del pr�stamo en el servidor
    var url = "ServletPrestamo?idPrestamo=" + idPrestamo + 
              "&estadoPrestamo=" + nuevoEstado + 
              "&dniCliente=" + dniCliente + 
              "&importeSolicitado=" + importeSolicitado + 
              "&numeroCuenta=" + numeroCuenta +
              "&importeCuota=" + importeCuota +
              "&cuotas=" + cuotas;

    // Redirigir al servlet para actualizar el estado
    window.location.href = url;
}

function showConfirmPopup(message, idPrestamo, dniCliente, importeSolicitado, numeroCuenta, importeCuota, cuotas, nuevoEstado) {
    var popup = document.getElementById('popupConfirm');
    document.getElementById('popupConfirmMessage').innerText = message;
    var aceptarButton = document.getElementById('AceptarPrestamo');
    aceptarButton.setAttribute('onclick', 'confirmarCambio(' + idPrestamo + ', "' + dniCliente + '", "' + importeSolicitado + '", "' + numeroCuenta + '", "' + importeCuota + '", "' + cuotas + '", "' + nuevoEstado + '")');
    popup.classList.add("active"); // Solo activar cuando se muestra el popup
}

function closeConfirmPopup() {
    var popup = document.getElementById('popupConfirm');
    popup.classList.remove("active");
}

function showResultPopup(message) {
    var popup = document.getElementById("popupResult");
    var popupMessage = document.getElementById("popupResultMessage");
    popupMessage.innerText = message;
    popup.classList.add("active");
}

function closeResultPopup() {
    var popup = document.getElementById("popupResult");
    popup.classList.remove("active");
}

document.addEventListener('DOMContentLoaded', function() {
    var mensaje = '<%= request.getAttribute("Mensaje") %>';
    if (mensaje != null && mensaje !== "") {
        showResultPopup(mensaje);
    }
    
    closeConfirmPopup();
    closeResultPopup();
});

</script>

</head>
<body>
<% if(session.getAttribute("tipoUsuario") != null){ %>

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
        <h3 style="display:flex; justify-content: center;">Prestamos solicitados</h3>
        <table id="table_id" class="display">
            <thead>
                <tr>
                    <th>Fecha</th>
                    <th>Cliente</th>
                    <th>DNI</th>
                    <th>Cuenta destino</th>
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
                                <td><%= prestamo.getClienteDni().getApellido()%>,<%= prestamo.getClienteDni().getNombre() %></td>
                                <td><%= prestamo.getClienteDni().getDni() %></td>
                                <td><%= prestamo.getCuentaDestino().getNumeroCuenta() %></td>
                                <td><%= prestamo.getImporteSolicitado() %></td>
                                <td><%= prestamo.getImporteAPagar() %></td>
                                <td><%= prestamo.getCuotas() %></td>
                                <td><%= prestamo.getImporteCuota() %></td>
                                <th class="invisible">ID Pr�stamo</th> <!-- Columna invisible -->
                                <td>                     
								<select id="estadoPrestamo_<%= prestamo.getId() %>" data-estado-actual="<%= prestamo.getEstado().getId() %>" onchange="mostrarMensajeCambio('<%= prestamo.getId() %>', '<%= prestamo.getClienteDni().getDni() %>', '<%= prestamo.getImporteSolicitado() %>', '<%= prestamo.getCuentaDestino().getNumeroCuenta() %>', '<%= prestamo.getImporteCuota() %>', '<%= prestamo.getCuotas() %>', this.value)">
                                    <% for (EstadoPrestamo estado : listaEstadosPrestamo) { %>
                                        <option value="<%= estado.getId() %>" <%= prestamo.getEstado().getId() == estado.getId() ? "selected" : "" %>>
                                            <%= estado.getDescripcion() %>
                                        </option>
                                    <% } %>
                                </select>
                                </td>
                                <td class="invisible"><%= prestamo.getId() %></td> <!-- ID del pr�stamo en columna invisible -->
                            </tr>
                        <% 
                        }
                    } else {
                %>
                <tr>
                    <td colspan="7">No tiene pr�stamos actuales</td>
                </tr>
                <% 
                    }
                %>
            </tbody>
        </table>
    </div>
	<div id="popupConfirm" class="popup">
	    <div class="popup-content">
	        <span id="popupConfirmMessage"></span>
	        <div class="popup-buttons">
	            <button id="AceptarPrestamo" name="AceptarPrestamo">Aceptar</button>
	            <button onclick="closeConfirmPopup()">Cancelar</button>
	        </div>
	    </div>
	</div>

    <div id="popupResult" class="popup">
        <span class="close-btn" onclick="closeResultPopup()">&times;</span>
        <p id="popupResultMessage"></p>
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
                // Aprobado, Rechazado, Abonado no pueden cambiar, deshabilita el select
                selectElement.setAttribute("disabled", "true");
            }
        });
    });

    function submitForm() {
        closeConfirmPopup();
        document.forms[0].submit(); // Env�a el formulario actual
    }

    window.onload = function() {
        // Obtenemos el mensaje de error desde el servidor
        var errorMensaje = "<%= (request.getAttribute("Mensaje") != null) ? request.getAttribute("Mensaje") : "" %>";
        if (errorMensaje) {
            showResultPopup(errorMensaje);
        }
    };
</script>

</body>
</html>