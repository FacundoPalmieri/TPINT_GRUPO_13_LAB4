<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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

<title>Insert title here</title>
</head>
<body>
    <div id="General">
	<div class="banner">
	<div class="logo_encabezado_izquierda">
	    <img src="img/Grupo 13_encabezado.png" alt="Logo" class="logo_encabezado">
	    <h3>Bienvenido</h3>
	</div>
	<div class="logo_encabezado_derecha">
	    <%= (String) session.getAttribute("nombre") %>
	    <a href="ServletCerrarSesion" class="logout">
	        <img src="img/logout.png" alt="Logout" class="logo_encabezado">
	    </a>
	</div>

	</div>

           <div class="button-container">
            <form action="EditarCliente" method="get">
				<input type="submit" value="Mis Datos" name="btnEditar"  onclick="window.location.href='DatosCliente.jsp';" class="botonera"> 
			</form> 
			
			     <input type="submit" value="Prestamos" name="btnClientePrestamos" onclick="window.location.href='ClientePrestamo.jsp';" class="botonera">
	
		 </div>  
		  <a href="ServletCuentas?Param=1" class="botonera">Cuentas</a> 
		 <div class="button-container"> <input type="button" value="Volver" name="btnVolver" onclick="window.location.href='Login.jsp';"> </div>
		 
      
    </div>
</body>
</html>