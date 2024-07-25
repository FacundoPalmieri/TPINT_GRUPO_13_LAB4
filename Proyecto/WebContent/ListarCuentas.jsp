<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="entidad.Cuenta"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.List"%>
<%@page import="entidad.Persona"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<style>
	<jsp:include page="css\Style.css"></jsp:include>
</style>
 <link rel="stylesheet" type="text/css" href="path_to_your_css_file.css">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.min.js"></script>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.min.css">
    
     <script type="text/javascript">
     $(document).ready(function() {
    	    $('#tablaCuentas').DataTable({
    	    	order: [[0, 'desc']],
    	        language: {
    	            lengthMenu: "Mostrar _MENU_ registros",
    	            info: "Mostrando _START_ a _END_ de _TOTAL_ registros",
    	            infoEmpty: "Mostrando 0 a 0 de 0 registros",
    	            infoFiltered: "(filtrado de _MAX_ registros en total)",
    	            loadingRecords: "Cargando...",
    	            zeroRecords: "No se encontraron registros coincidentes",
    	            emptyTable: "No hay datos disponibles en la tabla",
    	            paginate: {
    	                first: "Primero",
    	                previous: "Anterior",
    	                next: "Siguiente",
    	                last: "Último"
    	            },
    	            aria: {
    	                sortAscending: ": activar para ordenar columna ascendente",
    	                sortDescending: ": activar para ordenar columna descendente"
    	            },
    	            lengthMenu: "Cantidad registros _MENU_",
    	            search: "Buscar:"
    	        },
    	        dom: 'lfrtip'
    	    });
    	});
    		
    
    function toggleCuentas() {
        var checkbox = document.getElementById('toggleHabilitados');
        var habilitadas = document.getElementsByClassName('habilitada');
        var noHabilitadas = document.getElementsByClassName('no-habilitada');

        if (checkbox.checked) {
            for (var i = 0; i < noHabilitadas.length; i++) {
                noHabilitadas[i].style.display = '';
            }
            for (var j = 0; j < habilitadas.length; j++) {
                habilitadas[j].style.display = '';
            }
        } else {
            for (var i = 0; i < noHabilitadas.length; i++) {
                noHabilitadas[i].style.display = 'none';
            }
            for (var j = 0; j < habilitadas.length; j++) {
                habilitadas[j].style.display = '';
            }
        }
    }

    </script>
    
<title>Listado de Cuentas</title>
</head>
<body>
	<div class="banner">
    <div class="logo_encabezado_izquierda">
        <img src="img/Grupo 13_encabezado.png" alt="Logo" class="logo_encabezado">
        <h3>Listado de Cuentas</h3>
    </div>
    <div class="logo_encabezado_derecha">
        <%= (String) session.getAttribute("usuario") %>
        <a href="ServletCerrarSesion" class="logout">
            <img src="img/logout.png" alt="Logout" class="logo_encabezado">
        </a>
    </div>
</div>




 <div class="table-container" >
 	<div class="toggle-container filtro-container" style="margin-top:2%;" >
        <input type="checkbox" id="toggleHabilitados" onchange="toggleCuentas()">
        <label for="toggleHabilitados">Mostrar todas las cuentas</label>
    </div>
        <table id="tablaCuentas" class="display">
            <thead>
                <tr>
                    <th>Número de Cuenta</th>
                    <th>DNI del Cliente</th>
                    <th>Fecha de Creación</th>
                    <th>Tipo de Cuenta</th>
                    <th>CBU</th>
                    <th>Saldo</th>
                    <th>Habilitado</th>
                </tr>
            </thead>
            <tbody>
                <%

                ArrayList<Cuenta> listaCuentas = null;
                listaCuentas = (ArrayList<Cuenta>) request.getAttribute("listaCuentas");  
                if (listaCuentas != null) {
                    for (Cuenta cuenta : listaCuentas) {
                %>
                <tr class="<%= cuenta.getHabilitado() == 1 ? "habilitada" : "no-habilitada" %>">
                    <td><%= cuenta.getNumeroCuenta() %></td>
                    <td><%= cuenta.getClienteDni() %></td>
                    <td><%= cuenta.getFechaCreacion() %></td>
                    <td><%= cuenta.getIdTipoCuenta().getDescripcion() %></td>
                    <td><%= cuenta.getCbu() %></td>
                    <td><%= cuenta.getSaldo() %></td>
                    <td><%= cuenta.getHabilitado() == 1 ? "Sí" : "No" %></td>
                </tr>
                <%
                    }
                } else {
                %>
                <tr>
    				<td colspan="7">No se encontraron cuentas.</td>
				</tr>
				<%
				    }
				%>
            </tbody>
        </table>
    </div>
<div style="display:flex; justify-content: end; margin: 2%;" >
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