<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.List"%>
<%@page import="entidad.Cuenta"%>
<%@page import="entidad.Movimientos"%>
<%@page import="entidad.TipoMovimiento"%>
<%@page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
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
<link rel="stylesheet" type="text/css" href="path_to_your_css_file.css">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.min.js"></script>
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.min.css">
<script type="text/javascript">
    $(document).ready(function() {
		$('#table_id').DataTable({
            language: {
                lengthMenu: "Mostrar _MENU_ registros",
                info: "Mostrando _START_ a _END_ de _TOTAL_ registros",
                infoEmpty: "Mostrando 0 a 0 de 0 registros",
                infoFiltered: "(filtrado de _MAX_ registros en total)",
                infoPostFix: "",
                loadingRecords: "Cargando...",
                zeroRecords: "No se encontraron registros coincidentes",
                emptyTable: "No hay datos disponibles en la tabla",
                paginate: {
                    first: "Primero",
                    previous: "Anterior",
                    next: "Siguiente",
                    last: "�ltimo"
                },
                aria: {
                    sortAscending:  ": activar para ordenar columna ascendente",
                    sortDescending: ": activar para ordenar columna descendente"
                },
                lengthMenu: "Cantidad registros _MENU_"
            },
            dom: 'ltipr' // Controla los elementos que se muestran (l: longitud del men , t: tabla, i: informaci n, p: paginaci n, r: procesamiento)
        });
	});
</script>
</head>
<body>
<div id="General">
        <div class="banner">
            <div class="logo_encabezado_izquierda">
                <img src="img/Grupo 13_encabezado.png" alt="Logo" class="logo_encabezado">
                <h3>Movimientos</h3>
            </div>
            <div class="logo_encabezado_derecha">
                <%= (String) session.getAttribute("usuario") %>
                <a href="ServletCerrarSesion" class="logout">
                    <img src="img/logout.png" alt="Logout" class="logo_encabezado">
                </a>
            </div>
        </div>
        
<div class="cuenta">
    <div class="cuenta-item">
        <span class="label">N�mero de Cuenta:</span>
        <span class="value"><%= request.getAttribute("cuentaId") %></span>
    </div>
    <div class="cuenta-item">
        <span class="label">CBU:</span>
        <span class="value"><%= request.getAttribute("cbu") %></span>
    </div>
    <div class="cuenta-item">
        <span class="label">Saldo:</span>
        <span class="value">$ <%= request.getAttribute("saldo") %></span>
    </div>
</div>

        
        

	<div style= "margin: 0.5%;">
		<h5>Detalle movimientos</h5>
	   <table id="table_id" class="display">
	   <thead>
	        <tr>
	            <th>Fecha</th>
	            <th>Detalle</th>
	            <th>Importe</th>
	            <th>Tipo movimiento</th>
	        </tr>
	   </thead>
	   <tbody>
	        <%
	        	ArrayList<Movimientos> listaMovimientos = null;
	            listaMovimientos = (ArrayList<Movimientos>)request.getAttribute("listaMovimientos");
	            if (listaMovimientos != null) {
	                for (Movimientos movimiento : listaMovimientos) {
	        %>
	        <tr>
	            <td>  <%= movimiento.getFecha() %></td>
	            <td>  <%= movimiento.getDetalle() %></td>
	            <td>$ <%= movimiento.getImporte() %></td>
	            <td>  <%= movimiento.getTipo_Movimiento_id().getDescripcion() %></td>
	           
	        </tr>
	        <%
	                }
	            } else {
	        %>
	   
	        <%
	            }
	        %>
	    </tbody>
	    </table>
	 </div>

        <div style="display:flex; justify-content: end; margin: 2%;">
			<input type="button" value="Volver" name="btnVolver" onclick="window.location.href='ServletCuentas?Param=1';">
        </div>
    </div>
</body>
</html>