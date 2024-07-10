<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Date"%> 
<%@ page import="java.text.SimpleDateFormat"%>   
<%@ page import="entidad.Cuenta"%>
<%@ page import="entidad.TipoCuenta"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import ="java.time.LocalDate" %> 
<%@ page import ="java.time.format.DateTimeFormatter" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
        var importe = parseFloat(document.getElementById("importeSolicitado").value);
        var cuotas = parseInt(document.getElementById("cuotas").value);
        if (!isNaN(importe) && !isNaN(cuotas) && cuotas > 0) {
            var importeTotal = importe * (1 + (0.05 * cuotas)); 
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
                <h3>Solicitar Pr�stamo</h3>
            </div>
            <div class="logo_encabezado_derecha">
                <%= (String) session.getAttribute("usuario") %>
                <a href="ServletCerrarSesion" class="logout">
                    <img src="img/logout.png" alt="Logout" class="logo_encabezado">
                </a>
            </div>
        </div>
       <form action="ServletPrestamo" method="post" >
        <div style="margin: 0.5%;">
                <table class="custom-table">
                    <tr>
                        <td>Cliente:</td>
                        <td><%= (String) session.getAttribute("usuario") %></td>
                    </tr>
                    <tr>
                    <%
						LocalDate currentDate = LocalDate.now();
					    String formattedDate = currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
					%>
                        <td>Fecha:</td>
                        <td><input type="date" id="fecha" name="fecha" value="<%= formattedDate %>" readonly></td>
                    </tr>
                    <tr>
                        <td>Importe:</td>
                        <td><input type="text" id="importeSolicitado" name="importeSolicitado" oninput="calcularImporteTotal()"></td>
                    </tr>
                  <tr>
					  <td>Cuotas:</td>
					    <td>
					        <select id="cuotas" name="cuotas" onchange="calcularImporteTotal()">
					            <option value="6">6 cuotas</option>
					            <option value="12">12 cuotas</option>
					            <option value="24">24 cuotas</option>
					            <option value="48">48 cuotas</option>
					            <option value="96">96 cuotas</option>
					        </select>
					    </td>
					</tr>
                    <tr>
                        <td>Importe Total:</td>
                        <td><input type="text" id="importeTotal" name="importeTotal" readonly></td>
                    </tr>
                    <tr>
                        <td>Importe por Cuota:</td>
                        <td><input type="text" id="importeCuota" name="importeCuotas" readonly></td>
                    </tr>
                    <tr>
                        <td>Cuenta para recibir el pr�stamo:</td>
                        <td>
                            <select name="cuentaDestino" required>
                                <% 
                                    ArrayList<Cuenta> listaCuentas = null;
                                    listaCuentas = (ArrayList<Cuenta>) request.getAttribute("listaCuentas");
                                    if (listaCuentas != null) {
                                        for (Cuenta cuenta : listaCuentas) {
                                        	System.out.println(cuenta); 	
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
                </table>
                <div class="btnPrestamo">
                    <input type="submit" value="Solicitar Pr�stamo"  name="btnSolicitarPrestamo" style="margin-top: 5px !important; margin-botton: 5px !important; margin-left: -52px;">
                    <input type="button" value="Volver" name="btnVolver" onclick="window.location.href='InicioCliente.jsp';" style="margin-left: 1%; margin-top: 5px !important; margin-botton: 5px !important;"> 
                </div>
        </div>
       </form>
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