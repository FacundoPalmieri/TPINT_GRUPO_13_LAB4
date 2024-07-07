<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="entidad.Usuario" %>
<%@ page import="entidad.Persona" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Eliminar Usuario</title>
<style type="text/css">
    <jsp:include page="css\Style.css"></jsp:include>
</style>
</head>
<body>

    <div class="banner">
        <div class="logo_encabezado_izquierda">
            <img src="img/Grupo 13_encabezado.png" alt="Logo" class="logo_encabezado">
            <h3>Eliminar Usuario</h3>
        </div>
        <div class="logo_encabezado_derecha">
            <%= (String) session.getAttribute("usuario") %>
            <a href="ServletCerrarSesion" class="logout">
                <img src="img/logout.png" alt="Logout" class="logo_encabezado">
            </a>
        </div>
    </div>
    
    <form id="eliminarUsuarioForm" action="ServletEliminarCliente" method="post">
        <div id="BusquedaCliente">
            <input type="text" id="dniCliente" name="dniCliente" placeholder="Ingrese el DNI del cliente" value="<%= (request.getParameter("dniCliente") != null) ? request.getParameter("dniCliente") : "" %>" required>
            <input type="submit" value="&#128269;" name="btnBuscarEliminar" style="background-color: #78AD89">
        </div>
        
        <div id="ResultadoBusqueda">
            <div class="form-group">
                <div class="form-item">
                    <label for="usuario">Usuario:</label>
                    <input type="text" id="usuario" name="usuario" value="<%= (request.getAttribute("Usuario") != null) ?  request.getAttribute("Usuario") : "" %>" readonly>
                </div>
                <div class="form-item">
                    <label for="nombre">Nombre:</label>
                    <input type="text" id="nombre" name="nombre" value="<%= (request.getAttribute("Nombre") != null) ? request.getAttribute("Nombre") : "" %>" readonly>
                </div>
                <div class="form-item">
                    <label for="apellido">Apellido:</label>
                    <input type="text" id="apellido" name="apellido" value="<%= (request.getAttribute("Apellido") != null) ?  request.getAttribute("Apellido") : "" %>" readonly>
                </div>
                <div class="center-container">
                    <input type="button" value="Eliminar" name="btnEliminar" style="background-color: #dc3545; margin-right: 2%;" onclick="confirmarEliminacion()">
                    <input type="button" value="Volver" name="btnVolver" onclick="window.location.href='ABMLclientes.jsp';">
                </div>
            </div>
        </div>
        <input type="hidden" id="btnConfirmacion" name="btnConfirmacion">
    </form>

    <!-- Popup para confirmación de eliminación -->
    <div id="popupEliminar" class="popup">
        <span class="close-btn" onclick="closePopup('popupEliminar')">&times;</span>
        <p>¿Estás seguro de que deseas eliminar este usuario?</p>
        <div class="popup-buttons">
            <button type="button" onclick="confirmarEliminacionFinal()">Sí</button>
            <button type="button" onclick="closePopup('popupEliminar')">No</button>
        </div>
    </div>

    <!-- Popup para confirmación de transacción -->
    <div id="popupTransaccion" class="popup">
        <span class="close-btn" onclick="closePopup('popupTransaccion')">&times;</span>
        <p id="popupMessageTransaccion"></p>
    </div>

    <script>
        function confirmarEliminacion() {
            showPopup('popupEliminar');
        }

        function confirmarEliminacionFinal() {
            document.getElementById('btnConfirmacion').value = 'true';
            submitForm();
        }

        function submitForm() {
            closePopup('popupEliminar');
            document.getElementById('eliminarUsuarioForm').submit();
        }

        function showPopup(popupId, message) {
            if (message === undefined) {
                message = '';
            }
            var popup = document.getElementById(popupId);
            if (message) {
                document.getElementById('popupMessageTransaccion').innerText = message;
            }
            popup.classList.add("active");
        }

        function closePopup(popupId) {
            var popup = document.getElementById(popupId);
            popup.classList.remove("active");
        }

        document.addEventListener('DOMContentLoaded', function() {
            <% Boolean filas = (Boolean) request.getAttribute("filas"); %>
            <% String mensaje = (String) request.getAttribute("Mensaje"); %>
            <% if (filas != null) { %>
                <% if (filas == true) { %>
                    showPopup('popupTransaccion', "Usuario dado de baja");
                <% } else { %>
                    showPopup('popupTransaccion', "Error, vuelva a intentarlo");
                <% } %>
            <% } else if (mensaje != null) { %>
                showPopup('popupTransaccion', "<%= mensaje %>");
            <% } %>
        });
    </script>
</body>
</html>
