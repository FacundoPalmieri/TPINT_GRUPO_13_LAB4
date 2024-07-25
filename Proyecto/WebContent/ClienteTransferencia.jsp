<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="entidad.Cuenta"%>
<%@page import="entidad.Persona"%>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
    <jsp:include page="css\Style.css"></jsp:include>
</style>
</head>
<body>

    <div class="banner">
        <div class="logo_encabezado_izquierda">
            <img src="img/Grupo 13_encabezado.png" alt="Logo" class="logo_encabezado">
            <h3>Transferir</h3>
        </div>
        <div class="logo_encabezado_derecha">
            <%= (String) session.getAttribute("usuario") %>
            <a href="ServletCerrarSesion" class="logout">
                <img src="img/logout.png" alt="Logout" class="logo_encabezado">
            </a>
        </div>
    </div>
    
    
	<%
	   Persona persona = new Persona();
	   Cuenta cuenta = new Cuenta();

	  
	   persona = (Persona)request.getAttribute("persona");
	   cuenta  = (Cuenta)request.getAttribute("cuenta");

	%>
    
    
    <form id="ServletTransferencia" action="ServletTransferencia" method="get">	
	    <div id="BusquedaCBU" class="form">
	        <input type="text" id=cbuCliente name="cbuCliente" placeholder="Ingrese CBU" value="<%= (request.getParameter("cbuCliente") != null) ? request.getParameter("cbuCliente") : "" %>" required>
	        <input type="submit" value="Buscar" name="btnBuscarCBU" style="background-color: #78AD89">
	    </div>
	    <div id="DestinoTransferencia">
	    <div class="form-group">
	    	<div class="form-item" style="margin-top: 10px;">
	        	<label for="ClienteDestino">Apellido y Nombre: </label>
	            <input type="text" id="ClienteDestino" name="ClienteDestino"  value="<%= (persona != null && persona.getNombre() != null ? persona.getNombre() : "") + " " + (persona != null && persona.getApellido() != null ? persona.getApellido() : "") %>">
	        </div>
	        <div class="form-item" style="margin-top: 10px;">
	        	<label for="DNIDestino">DNI: </label>
	            <input type="text" id="DNIDestino" name="DNIDestino"  value="<%= (cuenta != null && cuenta.getClienteDni() != 0 ? cuenta.getClienteDni() : "") %>">
	        </div>
	        <div class="form-item" style="margin-top: 10px;">
	        	<label for="nCuentaDestino">Número de cuenta: </label>
	            <input type="text" id="nCuentaDestino" name="nCuentaDestino"  value ="<%= (cuenta != null && cuenta.getNumeroCuenta() != 0 ? cuenta.getNumeroCuenta() : "") %>">
	        </div>
	    </div>
	</div>
    </form>
	<form id="formTransferencia" action="ServletTransferencia" method="post">
        <div id="ResultadoBusquedaCBU">
            <div class="form-group">
                <div class="form-group flex-item" style="margin-top: 10px;">
	                <label for="monto">Monto $</label>
	                <input type="number" style="width:450px;" id="monto" name="monto" required>
	            </div>
	            <div class="form-item" style="margin-top: 10px;">
	                <label for="cuentaOrigen">Cuenta origen:</label>
				    <select name="cuentaOrigen" id="cuentaOrigen" class="styled-select">
				    <%  
						ArrayList<Cuenta> listaCuentas = null;
		                listaCuentas = (ArrayList<Cuenta>) request.getAttribute("listaCuentas");
				        	if (listaCuentas != null) {
				            	for (Cuenta cuentaOrigen : listaCuentas) {
				    %>
				    <option value="<%= cuentaOrigen.getNumeroCuenta()%>-<%=cuentaOrigen.getSaldo()%>">Cuenta: <%= cuentaOrigen.getIdTipoCuenta().getDescripcion() %> - <%= cuentaOrigen.getCbu() %>, Saldo: $<%= cuentaOrigen.getSaldo() %></option>
				    <% 
				    }
				    } else {
				    %>
					    <option value="">No tiene cuentas disponibles</option>
				    <% 
				    }
				    %>
				    </select>
			    </div>
                
                <div class="center-container">
					<input type="button" name="btnTransferir" value="Transferir" onclick="confirmarEliminacion()" style="margin-right: 5px; margin-left: 0px !important; background-color:  #5E9C6D;"> 
                    <input type="button" value="Volver" name="btnVolver" onclick="window.location.href='InicioCliente.jsp';">
                </div>
                
                 <!-- COLUMNA OCULTA PARA MANEJAR EL SI/NO DE LOS MENSAJES -->
                   <input type="hidden" id="confirmacion" name="confirmacionTransferencia" value= null>
            </div>
        </div>
        
 


 
 <!-- POPUP CON MENSAJES -->
 
      
    <div id="popup" class="popup">
		<span class="close-btn" onclick="closePopup()">&times;</span>
		<p id="popupMessage"></p>
		 <% 
		 String mensaje = (String) request.getAttribute("Mensaje");
		 if (mensaje == null || mensaje.isEmpty()) {
	     %>
	      <button type="button" onclick="enviarFormulario(true)">Sí</button>
          <button type="button" onclick="enviarFormulario(false)">No</button>
		 <% 
		    }
		 %>
		
	 
	</div>
  
  </form>  
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
	 showPopup("¿Estás seguro de que deseas realizar la transferencia?");
}

function enviarFormulario(confirmado) {
	  if (confirmado == true) {
	     document.getElementById("confirmacion").value = "confirmado";
	     
	 	 // Especificar método POST y acción del formulario
	    var form = document.getElementById("formTransferencia");
	    form.method = "post";
	    form.action = "ServletTransferencia";

   		 // Enviar el formulario
   		  form.submit();
 	   }else {
        	  closePopup();
	 		 }
}



document.addEventListener('DOMContentLoaded', function() {
    <% 
        String mensaje2 = (String) request.getAttribute("Mensaje");
  		  if (mensaje2 != null) { 
    %>
  	
     	showPopup("<%= mensaje2%>");
     <% 
          } 
     %>
  });


</script>
</body>
</html>