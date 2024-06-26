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
        <jsp:include page="Encabezado.jsp"></jsp:include>
       
            <h2>Bienvenido, <%= (String) session.getAttribute("nombre") %></h2>
            
            
        </div>
           <div class="button-container">
            <form action="EditarCliente" method="get">
				<input type="submit" value="Mis Datos" name="btnEditar"  onclick="window.location.href='DatosCliente.jsp';"> 
			</form> 
			     <input type="submit" value="Prestamos" name="btnClientePrestamos" onclick="window.location.href='ClientePrestamos.jsp';">
		 </div>  
       
    </div>
</body>
</html>