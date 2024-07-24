<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="entidad.Prestamo"%>
<%@ page import="entidad.EstadoPrestamo"%>
<%@ page import="entidad.Persona"%>
<%@ page import="entidad.PagosPrestamos"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chartjs-plugin-datalabels@2.0.0"></script>
<meta charset="UTF-8">
<title>Reporte de Prestamos</title>
<style type="text/css">
	<%@ include file="css/Style.css" %>
    #estadoChart {
        width: 300px !important;
        height: 300px !important;
    }
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
    <div style="margin:2%;">
        <h3 style="display:flex; justify-content: center;">Reporte</h3>
        <div class="cuenta">
        	<% Persona persona = (Persona)request.getAttribute("persona"); %>
        	<div class="cuenta-item">
        		<span class="label">Cliente:</span>
        		<span class="value"><%= persona.getApellido() %>, <%= persona.getNombre() %></span>
        	</div>
        	<div class="cuenta-item">
        		<span class="label">DNI:</span>
        		<span class="value"><%= persona.getDni() %></span>
        	</div>
        </div>
        <table id="table_id" class="display">
            <thead>
                <tr>
                    <th>Fecha</th>
                    <th>Importe Solicitado</th>
                    <th>Importe a Pagar</th>
                    <th>Cuotas</th>
                    <th>Importe por Cuota</th>
                    <th>Estado Prestamo</th>
                    <th>Porcentaje Pagado</th>
                    <th>Monto Adeudado a la fecha</th>
                    <th>Monto Abonado a la fecha</th>

                </tr>
                
                 
            </thead>
            <tbody>
                <%  
                ArrayList<Prestamo> listaPrestamos = (ArrayList<Prestamo>) request.getAttribute("listaPrestamos");
                int cuotasPagas=0;
               	int cuotasImpagas=0;
                float peligro = 0;
                if (listaPrestamos != null) {
                        for (Prestamo prestamo : listaPrestamos) { 
                        	float porcentajePagado = 0;
                        	float montoTotalAdeudado = prestamo.getImporteAPagar();
                        	float montoAbonado = 0;
                        	int cantCuotasTotales = prestamo.getCuotas();
                %>
                            <tr>
                                <td><%= prestamo.getFecha() %></td>
                                <td>$<%= prestamo.getImporteSolicitado() %></td>
                                <td>$<%= prestamo.getImporteAPagar() %></td>
                                <td><%= prestamo.getCuotas() %></td>
                                <td>$<%= prestamo.getImporteCuota() %></td>
                                <td><%= prestamo.getEstado().getDescripcion() %> </td>
                                <td class="invisible"><%= prestamo.getId() %></td>
          
                                <% 
                                	if(prestamo.getEstado().getId()==3 || prestamo.getEstado().getId()==5){ 
                                		for(PagosPrestamos p : prestamo.getPagosPrestamos()){
                                			if(p.getEstado()==3){
                                				cuotasPagas++;
                                				montoAbonado+=p.getImportePago();
                                			}
                                		}
                                		if(cuotasPagas!=0){
                                			porcentajePagado= ((float)cuotasPagas/(float)cantCuotasTotales)*100;
                                			montoTotalAdeudado -= montoAbonado;
                                		}
                                	}%>
                                <td><%= String.format("%.2f",porcentajePagado) %>%</td>	
                                
                                             <% 
                              	if(prestamo.getEstado().getId()==3 || prestamo.getEstado().getId()==5){ 
                                		for(PagosPrestamos p : prestamo.getPagosPrestamos()){
                                			if(p.getEstado()==3){
                                				cuotasImpagas++;
                                			}
                            		  }
                                	}%>
                                <td>$<%= montoTotalAdeudado %></td>
                                <td>$<%= montoAbonado %></td>			
                               	
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
		
		<canvas id="estadoChart"></canvas>

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
    
    // Grafico js
        var estadoNombres = {
            1: 'Solicitado',
            2: 'En Analisis',
            3: 'Aprobado',
            4: 'Rechazado',
            5: 'Abonado'
        };

        // Recuperar la cadena JSON desde el JSP
        var prestamosJson = '<%= request.getAttribute("prestamosJson") %>';
        var prestamos = JSON.parse(prestamosJson); // Parsear la cadena JSON a un objeto JavaScript

        // Contar préstamos por estado
        var conteoPorEstado = {};
        prestamos.forEach(function(prestamo) {
            var estadoId = prestamo.estado; // Ajusta según tu estructura de datos
            if (!conteoPorEstado[estadoId]) {
                conteoPorEstado[estadoId] = 0;
            }
            conteoPorEstado[estadoId]++;
        });

        // Convertir IDs a nombres y preparar datos para Chart.js
        var labels = Object.keys(conteoPorEstado).map(function(id) {
            return estadoNombres[id] || 'Desconocido'; // Asegura que el nombre esté definido
        });
        var data = Object.values(conteoPorEstado);

        var ctx = document.getElementById('estadoChart').getContext('2d');
        var estadoChart = new Chart(ctx, {
            type: 'pie', // Cambiar a 'pie' para gráfico de torta
            data: {
                labels: labels,
                datasets: [{
                    label: 'Número de Préstamos',
                    data: data,
                    backgroundColor: [
                        'rgba(255, 99, 132, 0.2)',
                        'rgba(54, 162, 235, 0.2)',
                        'rgba(255, 206, 86, 0.2)',
                        'rgba(75, 192, 192, 0.2)',
                        'rgba(153, 102, 255, 0.2)',
                        'rgba(255, 159, 64, 0.2)'
                    ],
                    borderColor: [
                        'rgba(255, 99, 132, 1)',
                        'rgba(54, 162, 235, 1)',
                        'rgba(255, 206, 86, 1)',
                        'rgba(75, 192, 192, 1)',
                        'rgba(153, 102, 255, 1)',
                        'rgba(255, 159, 64, 1)'
                    ],
                    borderWidth: 1
                }]
            },
            options: {
                responsive: true,
                plugins: {
                    legend: {
                        position: 'top',
                        labels: {
                            font: {
                                size: 24 // Cambia el tamaño de la fuente en la leyenda
                            }
                        }
                    },
                    tooltip: {
                        callbacks: {
                            label: function(tooltipItem) {
                                return tooltipItem.label + ': ' + tooltipItem.raw;
                            }
                        },
                        bodyFont: {
                            size: 24 // Cambia el tamaño de la fuente en el tooltip
                        }
                    },
                    datalabels: {
                        formatter: (value, context) => {
                            return context.label + ': ' + value;
                        },
                        color: '#fff',
                        font: {
                            weight: 'bold',
                            size: 16
                        }
                    }
                }
            }
        });
</script>
</body>
</html>