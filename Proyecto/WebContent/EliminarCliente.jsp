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
		<div id="popup" class="popup">
		    <span class="close-btn" onclick="closePopup()">&times;</span>
		    <p id="popupMessage"></p>
		    <div class="popup-buttons">
		        <button name="btnConfirmacion" onclick="submitForm()">Sí</button>
		        <button onclick="closePopup()">No</button>
		    </div>
		</div>

        
    </form>
    
 
   
    <script>
        function confirmarEliminacion() {
            showPopup('¿Estás seguro de que deseas eliminar este usuario?');
        }

        function submitForm() {
            closePopup();
            document.getElementById('eliminarUsuarioForm').submit();
        }

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

    <%
        Boolean filas = (Boolean) request.getAttribute("filas");
        String mensaje = (String) request.getAttribute("Mensaje");
    %>
        document.addEventListener('DOMContentLoaded', function() {
            <% if (filas != null) { %>
                <% if (filas == true) { %>
                    showPopup("Usuario dado de baja");
                <% } else { %>
                    showPopup("Error, vuelva a intentarlo");
                <% } %>
            <% } else if (mensaje != null) { %>
                showPopup("<%= mensaje %>");
            <% } %>
        });
    </script>   
</body>
</html>
