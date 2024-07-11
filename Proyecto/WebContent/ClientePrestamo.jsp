<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Date"%> 
<%@ page import="java.text.SimpleDateFormat"%>   
<%@ page import="entidad.Cuenta"%>
<%@ page import="entidad.Prestamo"%>
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
        <div style="margin: 0.5%;">
                <button type="button" class="accordion">Solicitar Préstamo &#x1F4B3;</button>
                <div class="panel">
                <table class="custom-table" style="margin-top: 1%;">
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
                        <td>Cuenta para recibir el préstamo:</td>
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
                    <input type="submit" value="Solicitar Préstamo"  name="btnSolicitarPrestamo" style="margin-top: 5px !important; margin-botton: 5px !important;">
                </div>
                
                  </div>
          <button type="button" class="accordion">Abonar préstamo &#x1F4B0;</button>
                <div class="panel">
               
                    <p>Aquí va tema pagos, etc.</p>
                </div>
        </div>
                
          <button type="button" class="accordion">Mis préstamos &#128193;</button>
             <div class="panel">
            <table id="table_id" class="display" style="margin-top: 1%;">
                <thead>
                    <tr>
                        <th>Fecha</th>
                        <th>Importe Solicitado</th>
                        <th>Importe a Pagar</th>
                        <th>Cuotas</th>
                        <th>Importe por Cuota</th>
                        <th>Estado</th>
                        <th>Cuotas Abonadas</th>
                        <th>Saldo Restante</th>
                    </tr>
                </thead>
                <tbody>
                    <%  	
                      ArrayList<Prestamo> listaPrestamos = null;
                      listaPrestamos = ( ArrayList<Prestamo> )  request.getAttribute("listaPrestamos"); 
                        if (listaPrestamos != null) {
                            for (Prestamo prestamo : listaPrestamos) {
                            	System.out.println(prestamo);                            
                    %>
                    <tr>
                        <td><%= prestamo.getFecha() %></td>
                        <td><%= prestamo.getImporteSolicitado() %></td>
                        <td><%= prestamo.getImporteAPagar() %></td>
                        <td><%= prestamo.getCuotas() %></td>
                        <td><%= prestamo.getImporteCuota() %></td>
                        <td><%= prestamo.getEstado().getDescripcion() %></td>
                        <td><%= prestamo.getCuotasAbonadas() %></td>
                        <td><%= prestamo.getSaldoRestante() %></td>                       
                    </tr>
                    <% 
                            }
                        } else {
                    %>
                    <tr>
                        <td colspan="10">No tiene préstamos actuales</td>
                    </tr>
                    <% 
                        }
                    %>
                </tbody>
            </table>
             </div> 
       </form>
      <div style="display: flex; justify-content: end;">
      	<input type="button" value="Volver" name="btnVolver" onclick="window.location.href='InicioCliente.jsp';" style="margin-left: 1%; margin-top: 5px !important; margin-right: 10px !important;"> 
      </div>
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
            setTimeout(function() {
                window.location.href = "ServletPrestamo?Param=1"; // Redirige a la nueva página JSP
            }, 2000); // 5000 milisegundos
        }
    };
    
    var acc = document.getElementsByClassName("accordion");
    var i;

    for (i = 0; i < acc.length; i++) {
        acc[i].addEventListener("click", function() {
            this.classList.toggle("active");
            var panel = this.nextElementSibling;
            if (panel.style.maxHeight) {
                panel.style.maxHeight = null;
            } else {
                panel.style.maxHeight = panel.scrollHeight + "px";
            } 
        });
    }
</script>
</body>
</html>