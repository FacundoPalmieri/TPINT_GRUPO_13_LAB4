<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.List"%>
<%@page import="entidad.Cuenta"%>
<%@page import="java.util.ArrayList" %>
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

	<div style= "margin: 0.5%;">
	   <table id="table_id" class="display">
	        <tr>
	            <th>Número de cuenta</th>
	            <th>Tipo de cuenta</th>
	            <th>Saldo</th>
	            <th>Movimientos</th>
	        </tr>
	        <%
	        	ArrayList<Cuenta> listaCuentas = null;
	            listaCuentas = (ArrayList<Cuenta>)request.getAttribute("listaCuentas");
	            if (listaCuentas != null) {
	                for (Cuenta cuenta : listaCuentas) {

	                	    System.out.println(cuenta);
	            
	        %>
	        <tr>
	            <td><%= cuenta.getNumeroCuenta() %></td>
	            <td><%= cuenta.getIdTipoCuenta().getDescripcion()%></td>
	            <td><%= cuenta.getSaldo() %></td>
 			<td><a href="ServletMovimientos?cuentaId=<%= cuenta.getNumeroCuenta() %>&saldo=<%= cuenta.getSaldo() %>" class="btnEspecial">Ver</a></tr>
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
            <input type="button" value="Volver" name="btnVolver" onclick="window.location.href='InicioCliente.jsp';"> 
        </div>
    </div>
</body>
</html>