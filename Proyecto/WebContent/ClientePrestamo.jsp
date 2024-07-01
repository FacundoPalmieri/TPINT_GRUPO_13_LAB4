<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Date"%> 
<%@ page import="java.text.SimpleDateFormat"%>   
<%@page import="java.util.List"%>
<%@page import="entidad.Cuenta"%>
<%@page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Solicitar Prestamo</title>
<style type="text/css">
    .error {
            color: red;
        font-weight: bold;
        font-size: 20px; 
        position: absolute;
        bottom: 40px;
        right: 40px;
    }
    <jsp:include page="css/Style.css"></jsp:include>
</style>
<script type="text/javascript">
    function calcularImporteTotal() {
        var importe = parseFloat(document.getElementById("importe").value);
        var cuotas = parseInt(document.getElementById("cuotas").value);
        if (!isNaN(importe) && !isNaN(cuotas) && cuotas > 0) {
            var importeTotal = importe * (1 + (0.05 * cuotas)); // Supongamos una tasa de interés del 5% por cuota
            var importeCuota = importeTotal / cuotas;
            document.getElementById("importeTotal").value = importeTotal.toFixed(2);
            document.getElementById("importeCuota").value = importeCuota.toFixed(2);
        }
    }
</script>
</head>
<body>
 <div id="General">
        <div class="banner">
            <div class="logo_encabezado_izquierda">
                <img src="img/Grupo 13_encabezado.png" alt="Logo" class="logo_encabezado">
                <h3>Solicitar Préstamo</h3>
            </div>
            <div class="logo_encabezado_derecha">
                <%= (String) session.getAttribute("nombre") %>
                <a href="ServletCerrarSesion" class="logout">
                    <img src="img/logout.png" alt="Logout" class="logo_encabezado">
                </a>
            </div>
        </div>
        <div style="margin: 0.5%;">
            <form action="ServletSolicitarPrestamo" method="post">
                <table class="custom-table">
                    <tr>
                        <td>Cliente:</td>
                        <td><%= (String) session.getAttribute("nombre") %></td>
                    </tr>
                    <tr>
                        <td>Fecha:</td>
                        <td><input type="text" value="<%= new SimpleDateFormat("dd/MM/yyyy").format(new Date()) %>" readonly></td>
                    </tr>
                    <tr>
                        <td>Importe:</td>
                        <td><input type="text" id="importe" name="importe" oninput="calcularImporteTotal()"></td>
                    </tr>
                    <tr>
                        <td>Cuotas:</td>
                        <td><input type="text" id="cuotas" name="cuotas" oninput="calcularImporteTotal()"></td>
                    </tr>
                    <tr>
                        <td>Importe Total:</td>
                        <td><input type="text" id="importeTotal" readonly></td>
                    </tr>
                    <tr>
                        <td>Importe por Cuota:</td>
                        <td><input type="text" id="importeCuota" readonly></td>
                    </tr>
                    <tr>
                        <td>Cuenta para recibir el préstamo:</td>
                        <td>
                            <select name="cuentaDestino" required>
                                <%
                                    // Aquí se debe cargar la lista de cuentas del cliente desde la sesión o la base de datos
                                    List<Cuenta> cuentas = (List<Cuenta>) session.getAttribute("cuentas");
                                    if (cuentas != null) {
                                        for (Cuenta cuenta : cuentas) {
                                %>
                                    <option value="<%= cuenta.getNumeroCuenta() %>"><%= cuenta.getNumeroCuenta() %></option>
                                <%
                                        }
                                    } else {
                                %>
                                    <option value="">No tiene cuentas disponibles</option>
                                <%
                                    }
                                %>
                            </select>
                        </td>
                    </tr>
                    <tr id="cuotasAbonadasRow" style="display:none;">
                        <td>Cuotas Abonadas:</td>
                        <td><input type="text" id="cuotasAbonadas" name="cuotasAbonadas" readonly value="0"></td>
                    </tr>
                    <tr id="saldoRestanteRow" style="display:none;">
                        <td>Saldo Restante:</td>
                        <td><input type="text" id="saldoRestante" name="saldoRestante" readonly></td>
                    </tr>
                </table>
                <div class="button-container">
                    <input type="submit" value="Solicitar Préstamo" class="botonera">
                </div>
            </form>
        </div>
        <div class="button-container">
            <input type="button" value="Volver" name="btnVolver" onclick="window.location.href='InicioCliente.jsp';"> 
        </div>
    </div>
</body>
</html>