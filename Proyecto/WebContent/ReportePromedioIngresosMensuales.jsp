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
	

   table {
        width: 50%;
        table-layout: fixed; /* Add this line */
        margin-left: auto; /* Center the table horizontally */
 	    margin-right: auto; /* Center the table horizontally */
    }
    th, td {
        width: 33%; /* Adjust the width percentage as needed */
        word-wrap: break-word; /* Add this line if you have long text */
    }
</style>

</head>
<body>
<div class="banner">
    <div class="logo_encabezado_izquierda">
        <img src="img/Grupo 13_encabezado.png" alt="Logo" class="logo_encabezado">
        <h3>Top 3 Promedio de ingreso por cuenta</h3>
    </div>
    <div class="logo_encabezado_derecha">
        <%= (String) session.getAttribute("usuario") %>
        <a href="ServletCerrarSesion" class="logout">
            <img src="img/logout.png" alt="Logout" class="logo_encabezado">
        </a>
    </div>
</div>  
   
        <div>
            
            <div style="width: 100%; margin: 20px auto; display: flex; justify-content: center;">

	            <form action="ServletReportes" method="get">
	            	<input type="date" id="fechaInicio" name="fechaInicio"   value="<%= request.getParameter("fechaInicio") != null ? request.getParameter("fechaInicio") : "" %>" style="width: 35%;" required>
	            	<input type="date" id="fechaFin" name="fechaFin" value="<%= request.getParameter("fechaFin") != null ? request.getParameter("fechaFin") : "" %>" style="width: 35%;" required>
	     			<input type="submit" value="Buscar" name="btnBuscar" style="background-color: #78AD89">
	     		</form>
   			</div>
            <table id="table_id" class="display"  style=" width: 70%; margin-top: 20px;">
                <thead>
                    <tr>
                  		<th>Número cuenta</th>
                  		<th>Cliente</th>
                        <th>Importe</th>
               
                    </tr>
                </thead>
                <tbody id="table_body">
                    <%
                        ArrayList<Movimientos> listaMovimientos = (ArrayList<Movimientos>) request.getAttribute("listaMovimientos");
                        if (listaMovimientos != null) {
                            for (Movimientos movimiento : listaMovimientos) {
                    %>
                    <tr>
                 		<td><%= movimiento.getCuenta_origen().getNumeroCuenta() %></td>
                 		<td><%= movimiento.getCuenta_origen().getCliente_Dni().getApellido() +", "+ movimiento.getCuenta_origen().getCliente_Dni().getNombre() %>   </td>
                        <td>$ <%= movimiento.getImporte() %></td>
      
                     
                    </tr>
                    <%
                            }
                        } else {
                    %>
                    <tr>
                        <td colspan="4">Selecciona un período de fechas </td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
    </div>
    
   <div class="center-container" style="width: 70%; margin: 20px auto; display: flex; justify-content: center;">
    <input type="button" value="Limpiar" onclick="limpiarCampos()" style="margin-right: 5px; background-color: #5E9C6D;">
    <input type="button" value="Volver" name="btnVolver" onclick="window.location.href='UsuarioAdministrador.jsp';">
</div>


        
        
<script type="text/javascript">
    function limpiarCampos() {
        document.getElementById("fechaInicio").value = "";
        document.getElementById("fechaFin").value = "";
        document.getElementById("table_body").innerHTML = "";
    }
</script>
</body>
</html>