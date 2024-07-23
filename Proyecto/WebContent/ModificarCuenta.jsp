<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.List"%>
<%@page import="entidad.Cuenta"%>
<%@page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style>
	<jsp:include page="css\Style.css"></jsp:include>
</style>
<title>Modificar Cuentas</title>
</head>
<body>
	<div class="banner">
    <div class="logo_encabezado_izquierda">
        <img src="img/Grupo 13_encabezado.png" alt="Logo" class="logo_encabezado">
        <h3>Modificar Cuentas</h3>
    </div>
    <div class="logo_encabezado_derecha">
        <%= (String) session.getAttribute("usuario") %>
        <a href="ServletCerrarSesion" class="logout">
            <img src="img/logout.png" alt="Logout" class="logo_encabezado">
        </a>
    </div>
</div>

   <form action="ServletModificarCuenta" method="post">
        <div id="BusquedaCliente">
            <input type="text" id="dniCliente" name="dniCliente" placeholder="Ingrese el DNI del cliente" required>
            <input type="submit" value="Buscar Cuentas" name="btnBuscarC" style="background-color: #78AD89">
        </div>
    </form>

    <div style="margin: 0.5%;">
        <table id="table_id" class="display">
            <tr>
                <th>Número de cuenta</th>
                <th>CBU</th>
                <th>Tipo de cuenta</th>
                <th>Saldo</th>
                <th>Acción</th>
            </tr>
            <%
                ArrayList<Cuenta> listaCuentas = (ArrayList<Cuenta>) request.getAttribute("listaCuentas");
                if (listaCuentas != null) {
                    for (Cuenta cuenta : listaCuentas) {
            %>
            <tr>
                <form action="ServletModificarCuenta" method="post">
                    <td><%= cuenta.getNumeroCuenta() %></td>
                    <td><%= cuenta.getCbu() %></td>
                    <td>
                        <select name="tipoCuenta_<%= cuenta.getNumeroCuenta() %>">
                            <option value="1" <%= cuenta.getIdTipoCuenta().getId() == 1 ? "selected" : "" %>>Caja de Ahorro</option>
                            <option value="2" <%= cuenta.getIdTipoCuenta().getId() == 2 ? "selected" : "" %>>Cuenta Corriente</option>
                        </select>
                    </td>
                    <td><%= cuenta.getSaldo() %></td>
                    <td>
                        <input type="hidden" name="numeroCuenta" value="<%= cuenta.getNumeroCuenta() %>">
                        <input type="hidden" name="dniCliente" value="<%= request.getParameter("dniCliente") %>">
                        <input type="submit" value="Actualizar" name="btnActualizarCuenta">
                    </td>
                </form>
            </tr>
            <%
                    }
                } else {
            %>

            <%
                }
            %>
    </table>
	 </div>

        <div class="button-container">
            <input type="button" value="Volver" name="btnVolver" onclick="window.location.href='ABMLcuentas.jsp';"> 
        </div>


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