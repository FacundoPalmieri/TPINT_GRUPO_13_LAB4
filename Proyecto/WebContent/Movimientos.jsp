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
</head>
<body>
<div id="General">
        <div class="banner">
            <div class="logo_encabezado_izquierda">
                <img src="img/Grupo 13_encabezado.png" alt="Logo" class="logo_encabezado">
                <h3>Mis Cuentas</h3>
            </div>
            <div class="logo_encabezado_derecha">
                <%= (String) session.getAttribute("usuario") %>
                <a href="ServletCerrarSesion" class="logout">
                    <img src="img/logout.png" alt="Logout" class="logo_encabezado">
                </a>
            </div>
        </div>
        
   <div class="cuenta">
   	<td> Número de Cuenta <%= request.getAttribute("cuentaId")  %> - </td>
   	<td> Saldo $ <%= request.getAttribute("saldo") %></td>
   </div>
        
        

	<div style= "margin: 0.5%;">
		<h5>Detalle movimientos</h5>
	   <table id="table_id" class="display">
	        <tr>
	            <th>Número de cuenta</th>
	            <th>Fecha</th>
	            <th>Detalle</th>
	            <th>Importe</th>
	            <th>Tipo movimiento</th>
	        </tr>
	        <%
	        	ArrayList<Movimientos> listaMovimientos = null;
	            listaMovimientos = (ArrayList<Movimientos>)request.getAttribute("listaMovimientos");
	            if (listaMovimientos != null) {
	                for (Movimientos movimiento : listaMovimientos) {
	        %>
	        <tr>
	            <td>  <%= movimiento.getCuenta_destino().getNumeroCuenta() %></td>
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
	       
	    </table>
	 </div>

        <div class="button-container">
			<input type="button" value="Volver" name="btnVolver" onclick="window.location.href='ServletCuentas?Param=1';">
        </div>
    </div>
</body>
</html>