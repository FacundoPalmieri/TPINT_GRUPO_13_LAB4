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

	<% if (request.getParameter("usuario")!=null && request.getParameter("nombre")!=null && request.getParameter("apellido")!=null){
		String usuario = (String)request.getParameter("usuario");
		String nombre = (String)request.getParameter("nombre");
		String apellido = (String)request.getParameter("apellido");
		
		request.setAttribute("usuario",usuario);
		request.setAttribute("nombre", nombre);
		request.setAttribute("apellido",apellido);
	}
	%>
	<div class="banner">
	<div class="logo_encabezado_izquierda">
	    <img src="img/Grupo 13_encabezado.png" alt="Logo" class="logo_encabezado">
	    <h3>Eliminar Usuario</h3>
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
        <input type="submit" value="&#128269;" name="btnBuscarEliminar" style="background-color: #78AD89">
    </div>

   
    <div id="ResultadoBusqueda">
        <div class="form-group">
            <div class="form-item">
                <label for="usuario">Usuario:</label>
                <input type="text" id="usuario" name="usuario" value="<%= (request.getAttribute("usuario") != null) ? request.getAttribute("usuario") : "" %>" readonly>
            </div>
            <div class="form-item">
                <label for="nombre">Nombre:</label>
                <input type="text" id="nombre" name="nombre" value="<%= (request.getAttribute("nombre") != null) ? request.getAttribute("nombre") : "" %>" readonly>
            </div>
            <div class="form-item">
                <label for="apellido">Apellido:</label>
                <input type="text" id="apellido" name="apellido" value="<%= (request.getAttribute("apellido") != null) ? request.getAttribute("apellido") : "" %>" readonly>
            </div>
            <div class="center-container">
                <input type="submit" value="Eliminar" name="btnEliminar" style="background-color: #dc3545; margin-right: 2%;"  onclick="confirmarEliminacion()">
                <input type="button" value="Volver" name="btnVolver" onclick="window.location.href='ABMLclientes.jsp';">
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
	            showPopup("Usuario dado de baja" );
	            <% } else { %>
	            showPopup( "Error, vuelva a intentarlo");
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
    
    
    function confirmarEliminacion() {
        var confirmacion = confirm("�Est� seguro que quiere eliminar el usuario?");
        if (confirmacion) {
            document.getElementById("eliminarForm").submit();
        }
    }
</script>   
</body>
</html>