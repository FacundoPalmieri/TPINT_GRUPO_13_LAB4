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
	<form id="ServletTransferencia" action="ServletTransferencia" method="post">
        <div id="ResultadoBusquedaCBU">
            <div class="form-group">
                <div class="form-item" style="margin-top: 10px;">
	                <label for="monto">Monto $</label>
	                <input type="text" id="monto" name="monto" required>
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
                	<input type="submit" name="btnTransferir" value="Transferir" style="margin-right: 5px; margin-left: 0px !important;">
                    <input type="button" value="Volver" name="btnVolver" onclick="window.location.href='InicioCliente.jsp';">
                </div>
            </div>
        </div>
        
    </form>

    <!-- Popup para confirmaciï¿½n la transferencia -->
    <div id="popupTransferir" class="popup">
        <span class="close-btn" onclick="closePopup('popupTransferir')">&times;</span>
        <p> ¿Estas seguro de que deseas realizar la transferencia?</p>
        <div class="popup-buttons">
            <button type="button" name="btnConfirmacion" value="true" onclick="confirmarTransferirFinal()">Si</button>
            <button type="button" onclick="closePopup('popupTransferir')">No</button>
        </div>
    </div>
    

    <!-- Popup confirmaciï¿½n de transacciï¿½n -->
    <div id="popupTransaccionConfirmada" class="popup">
        <p id="popupMessageTransaccionConfirmada"></p>
    </div>

<script>
document.addEventListener('DOMContentLoaded', function() {
	   let botones = document.querySelectorAll('#ServletTransferencia input[type="button"]');
	   
	   botones.forEach(function(btn){
		   btn.addEventListener("click",function(e){
			   if(e.target.value==="Transferir"){
				 showPopup('popupTransferir');
			   }
		   })
	   })
	 });
	 
	 
	function confirmarTransaccion(boton){
	//Si confirma la decision, se envia una solicitud asincrona al Servlet
	//segun la respuesta del servlet se muestra el mensaje y en caso de ser Ok
	//se reenvia a el jsp ClienteTransferencia para ver los datos actualizados
		var Mensaje = "<%= (request.getAttribute("Mensaje") != null) ? request.getAttribute("Mensaje") : "" %>";
			
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

	
	function confirmarTransferirFinal() {
		closePopup("popupTransaccionConfirmada");
		confirmar("btnTransferir"); 
 }
	

</script>
</body>
</html>