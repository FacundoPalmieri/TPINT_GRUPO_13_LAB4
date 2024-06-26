<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
	<jsp:include page="css\Style.css"></jsp:include>
</style>
</head>
<body>
	<% if (request.getParameter("usuario")!=null) {
		String usuario = (String)request.getParameter("usuario");
		request.setAttribute("usuario",usuario);
	}
	%>


	<div class="banner">
	<div class="logo_encabezado_izquierda">
	    <img src="img/Grupo 13_encabezado.png" alt="Logo" class="logo_encabezado">
	    <h3>Modificar Usuario</h3>
	</div>
	<div class="logo_encabezado_derecha">
	    <%= (String) session.getAttribute("nombre") %>
	    <a href="ServletCerrarSesion" class="logout">
	        <img src="img/logout.png" alt="Logout" class="logo_encabezado">
	    </a>
	</div>

	</div>
 <form action="EditarCliente" method="post">
    <div id="BusquedaCliente">
        <input type="text" id="dniCliente" name="dniCliente" placeholder="Ingrese el DNI del cliente" value="<%= (request.getParameter("dniCliente") != null) ? request.getParameter("dniCliente") : "" %>" required>
        <input type="submit" value="&#128269;" name="btnBuscar" style="background-color: #78AD89">
    </div>

   
        <div id="ResultadoBusqueda">
            <div class="form-group flex-item">
                <div style="margin-top: 10px;">
                    <label for="usuario">Usuario:</label>
                  <input type="text" id="usuario" name="usuario" value="<%= (request.getAttribute("usuario") != null) ? request.getAttribute("usuario") : "" %>" readonly style="background-color: #e9ecef;">

                </div>
                <div class="form-group flex-item" style="margin-top: 10px;">
                    <label for="contrasena">Contrasenia:</label>
                    <input type="password" id="contrasena" name="contrasena"value="<%= (request.getAttribute("contrasena") != null) ? request.getAttribute("contrasena") : "" %>" style="background-color: #e9ecef;">
                </div>
                <div class="center-container">
                    <input type="submit" value="Actualizar" name="btnActualizar">
                    <input type="button" value="Volver" name="btnVolver" onclick="window.location.href='ABMLclientes.jsp';" style="margin-left: 2%;">
                </div>
            </div>
        </div>
    </form>
    
	    	
    <div id="popup" class="popup">
        <span class="close-btn" onclick="closePopup()">&times;</span>
        <p id="popupMessage"></p>
   </div>
	<%
	    Boolean filas= (Boolean) request.getAttribute("filas");
	



	    if (filas != null) {
	%>
	    <script>
	        document.addEventListener('DOMContentLoaded', function() {
	            <% if (filas == true) { %>
	            showPopup("Contrasenia Actualizada" );
	            <% } else { %>
	            showPopup( "No se pudo actualizar la contrasenia");
	            <% } %>
	        });
	    </script>
	<%
	    }
	%>
   
 <script>
 
    
    //funcionalidad pop up
    
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
</script>   
</body>
</html>