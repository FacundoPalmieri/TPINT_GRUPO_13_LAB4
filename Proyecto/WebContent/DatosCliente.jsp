<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@page import="entidad.Persona"%>
<%@page import="entidad.Direccion"%>
<%@page import="entidad.Provincia"%>
<%@page import="entidad.Localidad"%>

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
<title>Editar Cliente</title>
</head>
<body>
    <div id="General">
	<div class="banner">
	<div class="logo_encabezado_izquierda">
	    <img src="img/Grupo 13_encabezado.png" alt="Logo" class="logo_encabezado">
	    <h3>Bienvenido</h3>
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
 	Direccion direccion = new Direccion();
	Provincia provincia = new Provincia();
	Localidad localidad = new Localidad();
	
 	
	persona   = (Persona)request.getAttribute("persona");
	direccion = (Direccion)request.getAttribute("direccion");
	provincia = (Provincia)request.getAttribute("provincia");
	localidad = (Localidad)request.getAttribute("localidad");
	
 %>

	<form action="EditarCliente" method="get">
    <button type="button" class="accordion">Información Personal &#x1F4DD;</button>
    <div class="panel">
     <div class="flex-container">
        <div class="form-group flex-item" style= "margin-top: 10px;">
            <label for="dni">DNI:</label>
               <input type="text" id="dni" name="dni" value="<%= persona.getDni() %>"   readonly required>
        </div>
           <div class="form-group flex-item" style= "margin-top: 10px;">
            <label for="cuil">CUIL:</label>
            <input type="text" id="cuil" name="cuil" value="<%= persona.getCuil() %>"  readonly  required>
        </div>
        <div class="form-group flex-item">
            <label for="nombre">Nombre:</label>
            <input type="text" id="nombre" name="nombre" value="<%= persona.getNombre() %>"  readonly  required>
        </div>
       <div class="form-group flex-item">
            <label for="apellido">Apellido:</label>
            <input type="text" id="apellido" name="apellido" value="<%= persona.getApellido() %>"  readonly  required>
        </div>
       <div class="form-group flex-item">
            <label for="sexo">Sexo:</label>
            <input type="text" id="sexo" name="sexo" value="<%= persona.getSexo() %>"  readonly required>
        </div>
        <div class="form-group flex-item">
            <label for="fechaNacimiento">Fecha de Nacimiento:</label>
            <input type="date" id="fechaNacimiento" name="fechaNacimiento" value="<%= persona.getFechaNacimiento() %>"  readonly required>
        </div>
        <div class="form-group flex-item">
            <label for="nacionalidad">Nacionalidad:</label>
            <input type="text" id="nacionalidad" name="nacionalidad" value="<%= persona.getNacionalidad() %>"   readonly required>
        </div>
        </div>
    </div>
 <button type="button" class="accordion">Domicilio  &#x1F3E0;</button>
<div class="panel">
    <div class="flex-container">
        <div class="form-group flex-item" style="margin-top: 10px;">
            <label for="pais">País:</label>
         	 <input type="text" id="pais" name="pais" value="Argentina"   readonly required>        
        </div>
        <div class="form-group flex-item" style="margin-top: 10px;">
            <label for="provincia">Provincia:</label>
            <input type="text" id="provincia" name="provincia" value="<%= provincia.getNombre() %>"   readonly required>   

        </div>
        <div class="form-group flex-item" style="margin-top: 10px;">
            <label for="localidad">Localidad:</label>
			 <input type="text" id="localidad" name="localidad" value="<%= localidad.getNombre() %>"   readonly required>   
        </div>
        <div class="form-group-domicilio">
            <div class="group">
                <label for="calle">Calle:</label>
                 <input type="text" id="calle" name="calle" value="<%= direccion.getCalle() %>"   readonly required>   
            </div>
            <div class="group">
                <label for="numero">Número:</label>
                    <input type="text" id="numero" name="numero" value="<%= direccion.getAltura() %>"   readonly required>   
            </div>
            <div class="group">
                <label for="piso">Piso:</label>
                 <input type="text" id="piso" name="piso" value="<%= direccion.getPiso() %>"   readonly required>   
            </div>
            <div class="group">
                <label for="depto">Depto:</label>
                 <input type="text" id="depto" name="depto" value="<%= direccion.getDepartamento() %>"   readonly required>  
            </div>
        </div>
    </div>
</div>
    
    
    
    <button type="button" class="accordion">Información de Contacto &#x1F4F1;</button>
    <div class="panel">
      <div class="flex-container">
         <div class="form-group flex-item" style= "margin-top: 10px;">
           <label for="Celular">Celular:</label>
            <input type="text" id="celular" name="celular" value="<%= persona.getCelular() %>"  readonly required>
        </div>
       <div class="form-group flex-item" style= "margin-top: 10px;">
            <label for="telefonos">Telófono:</label>
            <input type="text" id="telefonos" name="telefonos" value="<%= persona.getTelefono() %>"  readonly required>
        </div>
        <div class="form-group  flex-item">
         <label for="correoElectronico">Correo Electrónico:</label>
            <input type="email" id="correoElectronico" name="correoElectronico" value="<%=persona.getEmail() %>"  readonly required>
        </div>
      </div>
    </div>


    <button type="button" class="accordion">Datos de Usuario &#x1F511;</button>
    <div class="panel">
         <div class="form-group" style= "margin-top:10px;">
            <label for="usuario">Usuario:</label>
            <input type="text" id="usuario" name="usuario" value="<%= (String) session.getAttribute("usuario") %>" readonly style="background-color: #e9ecef;">
        </div>
      
    </div>
	<%if(session.getAttribute("tipoUsuario")!=null) {%>
    	<input type="button" value="Volver" name="btnVolver" onclick="window.location.href='ListarClientes.jsp';">
	<%} else{ %>
		<input type="submit" value="Aceptar" name="btnAceptar">
    	<input type="button" value="Volver" name="btnVolver" onclick="window.location.href='InicioCliente.jsp';">
	<%} %>
	</form>

</div>
    


<script>
    var acc = document.getElementsByClassName("accordion");
    var i;

    for (i = 0; i < acc.length; i++) {
        acc[i].addEventListener("click", function() {
            this.classList.toggle("active");
            var panel = this.nextElementSibling;
            if (panel.style.maxHeight) {
                panel.style.maxHeight = null;
            } else {
                panel.style.maxHeight = panel.scrollHeight + "px";
            } 
        });
    }
    
    
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