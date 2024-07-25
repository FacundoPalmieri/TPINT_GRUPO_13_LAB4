<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.text.DecimalFormat" %>
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
        <%
		    DecimalFormat df = new DecimalFormat("#.#"); // Redondea a dos decimales
		%>
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
	            <th>Monto Adeudado</th>
	            <th>Monto Abonado</th>
	        </tr>
	    </thead>
	    <tbody>
	        <%  
	        ArrayList<Prestamo> listaPrestamos = (ArrayList<Prestamo>) request.getAttribute("listaPrestamos");
	        if (listaPrestamos != null) {
	            for (Prestamo prestamo : listaPrestamos) { 
	                float porcentajePagado = 0;
	                float montoTotalAdeudado = 0;
	                float montoAbonado = 0;
	                int cantCuotasTotales = prestamo.getCuotas();
	                int cuotasPagas = 0;
	        %>
	        <tr>
	            <td><%= prestamo.getFecha() %></td>
	            <td>$<%= prestamo.getImporteSolicitado() %></td>
	            <td>$<%= prestamo.getImporteAPagar() %></td>
	            <td><%= prestamo.getCuotas() %></td>
	            <td>$<%= prestamo.getImporteCuota() %></td>
	            <td><%= prestamo.getEstado().getDescripcion() %></td>
	            <td class="invisible"><%= prestamo.getId() %></td>
	            
	            <% 
	            if(prestamo.getEstado().getId() == 3 || prestamo.getEstado().getId() == 5) { 
	                montoTotalAdeudado = prestamo.getImporteAPagar();
	                for(PagosPrestamos p : prestamo.getPagosPrestamos()) {
	                    if(p.getEstado() != 1) {
	                        cuotasPagas++;
	                        montoAbonado += p.getImportePago();
	                    }
	                }
	                if(cuotasPagas != 0) {
	                    porcentajePagado = ((float)cuotasPagas / (float)cantCuotasTotales) * 100;
	                    montoTotalAdeudado -= montoAbonado;
	                }
	                String montoTotalAdeudadoFormateado = df.format(montoTotalAdeudado);
	                String montoAbonadoFormateado = df.format(montoAbonado);
	            %>
	            <td><%= String.format("%.0f", porcentajePagado) %>%</td>        
	            <td>$<%= montoTotalAdeudadoFormateado %></td>    
	            <td>$<%= montoAbonadoFormateado %></td>        
	            <% } else { %>
	            <td>-</td>
	            <td>-</td>
	            <td>-</td>
	            <% } %>
	        </tr>
	        <% 
	            }
	        } else {
	        %>
	        <tr>
	            <td colspan="9">No tiene préstamos actuales</td>
	        </tr>
	        <% 
	        }
	        %>
	    </tbody>
	</table>
		
		
		<%  
		    // Lógica de cálculo de totales
		    float totalImporteSolicitado = 0;
		    float montoAbonado = 0;
		
		    if (listaPrestamos != null) {
		    	
		        for (Prestamo prestamo : listaPrestamos) {
		            if (prestamo.getEstado().getId() == 3 || prestamo.getEstado().getId() == 5) {
		                
		
		                // Acumular importe solicitado
		                totalImporteSolicitado += prestamo.getImporteAPagar();
		
		                for (PagosPrestamos p : prestamo.getPagosPrestamos()) {
		                    if (p.getEstado() != 1) {
		                        montoAbonado += p.getImportePago();
		                    }
		                }
		                
		                
		            }
		        }
		        
		        System.out.println("totalImporteSolicitado"+totalImporteSolicitado);
		        System.out.println("totalMontoAbonado"+montoAbonado);
		    }
		%>
		
		<!-- Presentación de los totales en HTML -->
		<div id="data-container" 
		     data-total-importe-solicitado="<%= totalImporteSolicitado %>" 
		     data-total-monto-abonado="<%= montoAbonado %>">
		</div>
		
		<!-- Gráficos -->
		<div class="contenedorGraficos">
			<canvas id="estadoChart"></canvas>
			<canvas id="estadoFinanciero"></canvas>
		</div>
    </div>
    <div class="button-container" style="justify-content: end;">
        <input type="button" style="margin-bottom:2%; margin-right:2%;" value="Volver" name="btnVolver" onclick="window.location.href='UsuarioAdministrador.jsp';">
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
    
// Grafico js estados
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
            type: 'pie', 
            data: {
                labels: labels,
                datasets: [{
                    label: 'Número de Préstamos',
                    data: data,
                    backgroundColor: [
                        'rgba(206, 147, 216, 291)', // Opción 1
                        'rgba(251, 192, 45, 43)', // Opción 2
                        'rgba(39, 174, 96, 145)', // Opción 3
                        'rgba(182, 32, 32, 0.82)', // Opción 4
                        'rgba(79, 195, 247, 199)', // Opción 5

                    ],
                    borderColor: [
                        'rgba(214, 219, 223, 210)',
                        
                    ],
                    borderWidth: 1
                }]
            },
            options: {
                responsive: true,
                plugins: {
                    datalabels: {
                        formatter: (value, ctx) => {
                            let datasets = ctx.chart.data.datasets;
                            if (datasets.indexOf(ctx.dataset) === datasets.length - 1) {
                                let sum = datasets[0].data.reduce((a, b) => a + b, 0);
                                let percentage = Math.round((value / sum) * 100) + "%";
                                return percentage;
                            } else {
                                return percentage;
                            }
                        },
                        color: '#fff',
                    }
                }
            },
            plugins: [ChartDataLabels]
        });
        
// Grafico js total/abonado
document.addEventListener("DOMContentLoaded", function() {
    var ctx = document.getElementById('estadoFinanciero').getContext('2d');
    
    // Asegúrate de que los números estén formateados correctamente para JavaScript
    var dataContainer = document.getElementById('data-container');
    var totalImporteSolicitado = parseFloat(dataContainer.getAttribute('data-total-importe-solicitado'));
    var totalMontoAbonado = parseFloat(dataContainer.getAttribute('data-total-monto-abonado'));

    // Verifica si las variables están correctamente definidas
    if (!isNaN(totalImporteSolicitado) && !isNaN(totalMontoAbonado) && totalImporteSolicitado > 0) {
        // Calcular el porcentaje abonado
        var porcentajeAbonado = (totalMontoAbonado / totalImporteSolicitado) * 100;
        var porcentajeNoAbonado = 100 - porcentajeAbonado;

        // Datos para el gráfico
        var data = {
            labels: ['Total Abonado', 'Total Adeudado'],
            datasets: [{
                data: [porcentajeAbonado, porcentajeNoAbonado],
                backgroundColor: ['#AAB7B8', '#B62020'], // Cambia estos colores según tus preferencias
                borderWidth: 1
            }]
        };

        // Opciones del gráfico
        var options = {
            responsive: true,
            plugins: {
                datalabels: {
                    color: '#ffffff',
                    display: true,
                    formatter: function(value, context) {
                        var total = context.dataset.data.reduce((acc, val) => acc + val, 0);
                        var percentage = Math.round((value / total) * 100); 
                        return percentage + '%';
                    },
                    anchor: 'end',
                    align: 'start',
                    offset: 10
                }
            }
        };

        // Crear el gráfico
        new Chart(ctx, {
            type: 'doughnut',
            data: data,
            options: options,
            plugins: [ChartDataLabels] // Asegúrate de que el plugin ChartDataLabels esté incluido
        });
    } else {
        console.error('Datos inválidos para el gráfico.');
    }
});



</script>
</body>
</html>