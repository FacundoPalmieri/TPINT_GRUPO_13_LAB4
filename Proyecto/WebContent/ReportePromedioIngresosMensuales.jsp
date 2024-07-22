<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="entidad.Movimientos"%>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style>
	<jsp:include page="css\Style.css"></jsp:include>
</style>

</head>
<body>
<div class="banner">
    <div class="logo_encabezado_izquierda">
        <img src="img/Grupo 13_encabezado.png" alt="Logo" class="logo_encabezado">
        <h3>Reporte de ingresos Mensuales</h3>
    </div>
    <div class="logo_encabezado_derecha">
        <%= (String) session.getAttribute("usuario") %>
        <a href="ServletCerrarSesion" class="logout">
            <img src="img/logout.png" alt="Logout" class="logo_encabezado">
        </a>
    </div>
</div>  
   
        <div>
            
            <div>
	            <form action="ServletReportes" method="get">
	            	<input type="date" id="fechaInicio" name="fechaInicio"  style="width: 30%;" required>
	            	<input type="date" id="fechaFin" name="fechaFin" style="width: 30%;" required>
	     			<input type="submit" value="Buscar" name="btnBuscar" style="background-color: #78AD89">
	     		</form>
   			</div>
            <table id="table_id" class="display">
                <thead>
                    <tr>
                  		<th>Número cuenta</th>
                        <th>Importe</th>
               
                    </tr>
                </thead>
                <tbody>
                    <%
                        ArrayList<Movimientos> listaMovimientos = (ArrayList<Movimientos>) request.getAttribute("listaMovimientos");
                        if (listaMovimientos != null) {
                            for (Movimientos movimiento : listaMovimientos) {
                    %>
                    <tr>
                 		<td><%= movimiento.getCuenta_origen().getNumeroCuenta() %></td>
                        <td>$ <%= movimiento.getImporte() %></td>
      
                     
                    </tr>
                    <%
                            }
                        } else {
                    %>
                    <tr>
                        <td colspan="4">No se encontraron movimientos</td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
        </div>



</body>
</html>