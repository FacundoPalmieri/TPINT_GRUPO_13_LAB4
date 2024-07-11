<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="entidad.Usuario" %>
<%@ page import="entidad.Persona" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Eliminar Usuario</title>
<style type="text/css">
    <jsp:include page="css\Style.css"></jsp:include>
</style>
</head>
<body>

    <div class="banner">
        <div class="logo_encabezado_izquierda">
            <img src="img/Grupo 13_encabezado.png" alt="Logo" class="logo_encabezado">
            <h3>Eliminar Usuario</h3>
        </div>
        <div class="logo_encabezado_derecha">
            <%= (String) session.getAttribute("usuario") %>
            <a href="ServletCerrarSesion" class="logout">
                <img src="img/logout.png" alt="Logout" class="logo_encabezado">
            </a>
        </div>
    </div>
    
    <form id="eliminarUsuarioForm" action="ServletEliminarCliente" method="post">
    <% if (request.getParameter("usuario1")!=null && request.getParameter("nombre1")!=null && request.getParameter("apellido1")!=null){%>
        <div id="BusquedaCliente">
            <input type="text" id="dniCliente" name="dniCliente" value="<%= (request.getParameter("dniCliente1") != null) ? request.getParameter("dniCliente1") : "" %>" readonly>
        </div>
        
        <div id="ResultadoBusqueda">
            <div class="form-group">
                <div class="form-item">
                    <label for="usuario">Usuario:</label>
                    <input type="text" id="usuario" name="usuario" value="<%=request.getParameter("usuario1") %>" readonly>
                </div>
                <div class="form-item">
                    <label for="nombre">Nombre:</label>
                    <input type="text" id="nombre" name="nombre" value="<%= request.getParameter("nombre1") %>" readonly>
                </div>
                <div class="form-item">
                    <label for="apellido">Apellido:</label>
                    <input type="text" id="apellido" name="apellido" value="<%= request.getParameter("apellido1")  %>" readonly>
                </div>
                <div class="center-container">
                <%if(request.getParameter("estadoCliente1")!=null){%>
            		<input type="button" value="<%= Integer.parseInt(request.getParameter("estadoCliente1").toString())==1 ? "Eliminar" : "Habilitar" %>" name="<%= Integer.parseInt(request.getParameter("estadoCliente1").toString())==1 ? "btnEliminar" : "btnHabilitar" %>" style="background-color: #dc3545; margin-right: 2%;">
            	<%}%>
                    <input type="button" value="Volver" name="btnVolver" onclick="window.location.href='ListarClientes.jsp';">
                </div>
            </div>
        </div>
        <%}else{%> 
        	<div id="BusquedaCliente">
        <input type="text" id="dniCliente" name="dniCliente" placeholder="Ingrese el DNI del cliente" value="<%= (request.getParameter("dniCliente") != null) ? request.getParameter("dniCliente") : "" %>" required>
        <input type="submit" value="Buscar" name="btnBuscarEliminar" style="background-color: #78AD89">
    </div>
    <div id="ResultadoBusqueda">
        <div class="form-group">
            <div class="form-item">
                <label for="usuario">Usuario:</label>
                <input type="text" id="usuario" name="usuario" value="<%=(request.getAttribute("usuario") != null) ? request.getAttribute("usuario") : "" %>" readonly>
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
            	<%if(request.getAttribute("estadoCliente")!=null && request.getAttribute("usuario")!=null){%>
            		<input type="button" value="<%= Integer.parseInt(request.getAttribute("estadoCliente").toString())==1 ? "Eliminar" : "Habilitar" %>" name="<%= Integer.parseInt(request.getAttribute("estadoCliente").toString())==1 ? "btnEliminar" : "btnHabilitar" %>" style="background-color: #dc3545; margin-right: 2%;">
            	<%} else if(request.getAttribute("encontrado")!=null){%>
            		<p> Ingrese usuario valido </p>
            	<%} %>	
                <input type="button" value="Volver" name="btnVolver" onclick="window.location.href='ABMLclientes.jsp';">
            </div>
        </div>
    </div>
    <%} %> 
    </form>

    <!-- Popup para confirmación de eliminación -->
    <div id="popupEliminar" class="popup">
        <span class="close-btn" onclick="closePopup('popupEliminar')">&times;</span>
        <p>¿Estás seguro de que deseas eliminar el usuario?</p>
        <div class="popup-buttons">
            <button type="button" name="btnConfirmacion" value="true" onclick="confirmarEliminacionFinal()">Si</button>
            <button type="button" onclick="closePopup('popupEliminar')">No</button>
        </div>
    </div>
    
    <!-- Popup para confirmación de habilitacion -->
    <div id="popupHabilitar" class="popup">
        <span class="close-btn" onclick="closePopup('popupHabilitar')">&times;</span>
        <p>¿Estás seguro de que deseas habilitar el usuario?</p>
        <div class="popup-buttons">
            <button type="button" name="btnConfirmacion" value="true" onclick="confirmarHabilitacionFinal()">Si</button>
            <button type="button" onclick="closePopup('popupHabilitar')">No</button>
        </div>
    </div>

    <!-- Popup para confirmación de transacción -->
    <div id="popupTransaccion" class="popup">
        <p id="popupMessageTransaccion"></p>
    </div>

<script>
document.addEventListener('DOMContentLoaded', function() {
	   let botones = document.querySelectorAll('#eliminarUsuarioForm input[type="button"]');
	   
	   botones.forEach(function(btn){
		   btn.addEventListener("click",function(e){
			   if(e.target.value==="Eliminar"){
				 showPopup('popupEliminar');
			   }
			   else if(e.target.value==="Habilitar"){
				   showPopup('popupHabilitar');
			   }
		   })
	   })
	 });
	 
	 
		function confirmar(boton){
			//Si confirma la decision, se envia una solicitud asincrona al Servlet
			//segun la respuesta del servlet se muestra el mensaje y en caso de ser Ok
			//se reenvia a el jsp ListasClientes para ver los datos actualizados
			let xhr = new XMLHttpRequest();
			xhr.open("POST","ServletEliminarCliente","true");
			xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
			let dni = document.getElementsByName("dniCliente")[0].value;
			let params=boton+"=1"+"&dniCliente="+encodeURIComponent(dni);
			console.log(params);
			xhr.onreadystatechange = function(){
				let formulario = document.getElementById('eliminarUsuarioForm');
				let elementos = formulario.elements;
				for(let i=0; i<elementos.length; i++) {
			        elementos[i].disabled = true;
			    }
				if(xhr.readyState===4 && xhr.status===200){
					if(xhr.responseText=="2"){
						console.log("Usuario Habilitado")
						showPopup('popupTransaccion', "Usuario Habilitado, se redireccionara a el listado");
						setTimeout(function(){
							window.location.href='ListarClientes.jsp';
						},3000)
					}
					else if(xhr.responseText=="1"){
						console.log("Usuario Eliminado")
						showPopup('popupTransaccion', "Usuario Eliminado, se redireccionara a el listado");
						setTimeout(function(){
							window.location.href='ListarClientes.jsp';
						},3000)

					}
					else{
						console.log("Error, no se pudo completar la operacion")
						showPopup('popupTransaccion', "Error, no se pudo completar la operacion");
					}
				}
				else if(xhr.readyState===4){
					console.log("Error al enviar los datos");
				}
			}
			xhr.send(params);
	}
	 
		
	function showPopup(popupId, message) {
	    if (message === undefined) {
	        message = '';
	    }
	    let popup = document.getElementById(popupId);
	    console.log(popup)
	    if (message) {
	        document.getElementById('popupMessageTransaccion').innerText = message;
	    }
	    popup.classList.add("active");
	}
	
	function closePopup(popupId) {
     let popup = document.getElementById(popupId);
     popup.classList.remove("active");
 }

	
	function confirmarEliminacionFinal() {
		closePopup("popupEliminar");
		confirmar("btnEliminar"); 
 }
	
	function confirmarHabilitacionFinal(){
		closePopup("popupHabilitar");
		confirmar("btnHabilitar");
	}
</script>
</body>
</html>
