<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
	<jsp:include page="css\Style.css"></jsp:include>
</style>
</head>
<body>
	<div class="banner">
    <div class="logo_encabezado_izquierda">
        <img src="img/Grupo 13_encabezado.png" alt="Logo" class="logo_encabezado">
        <h3>AMBL Cuentas</h3>
    </div>
    <div class="logo_encabezado_derecha">
        <%= (String) session.getAttribute("nombre") %>
        <a href="ServletCerrarSesion" class="logout">
            <img src="img/logout.png" alt="Logout" class="logo_encabezado">
        </a>
    </div>
</div>

<form action="ServletCuentas" method="post">
    <div id="BusquedaCliente">
        <input type="text" id="dniCliente" name="dniCliente" placeholder="Ingrese el DNI del cliente" value="<%= (request.getParameter("dniCliente") != null) ? request.getParameter("dniCliente") : "" %>" required>
        <input type="submit" value="&#128269;" name="btnBuscarClienteCrearCuenta" style="background-color: #78AD89">
    </div>

    <div id="ResultadoBusqueda">
        <div class="form-group form-item">
            <div style="margin-top: 10px;">
                <label for="usuario">Usuario:</label>
                <input type="text" id="usuario" name="usuario" value="<%= (request.getAttribute("usuario") != null) ? request.getAttribute("usuario") : "" %>" readonly>
            </div>
            <div class="form-group flex-item">
                <div style="margin-top: 10px;">
                    <label for="nombre">Nombre:</label>
                    <input type="text" id="nombre" name="nombre" value="<%= (request.getAttribute("nombre") != null) ? request.getAttribute("nombre") : "" %>" readonly style="background-color: #e9ecef;">
                </div>
                <div class="form-group flex-item" style="margin-top: 10px;">
                    <label for="Apellido">Apellido:</label>
                    <input type="text" id="apellido" name="apellido" value="<%= (request.getAttribute("apellido") != null) ? request.getAttribute("apellido") : "" %>" style="background-color: #e9ecef;">
                </div>
                <div class="form-group flex-item" style="margin-top: 10px;">
                    <label for="tipoCuenta">Tipo de cuenta:</label>
                    <select id="tipoCuenta" name="tipoCuenta" style="width: 49%;">
                        <option value="1">Caja de ahorro</option>
                        <option value="2">Cuenta corriente</option>
                    </select>
                </div>
            </div>
            <div class="center-container">
                <input type="submit" value="Crear cuenta" name="btnCrearCuenta">
                <input type="button" value="Volver" name="btnVolver" onclick="window.location.href='ABMLcuentas.jsp';" style="margin-left: 2%;">
            </div>
        </div>
    </div>
</form>

<div id="popup" class="popup">
    <span class="close-btn" onclick="closePopup()">&times;</span>
    <p id="popupMessage"></p>
</div>

<script>
    // funcionalidad pop up
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

    window.onload = function() {
        // Obtenemos el mensaje de error desde el servidor
        var errorMensaje = "<%= (request.getAttribute("Mensaje") != null) ? request.getAttribute("Mensaje") : "" %>";
        if (errorMensaje) {
            showPopup(errorMensaje);
        }
    };
</script>
</body>
</html>