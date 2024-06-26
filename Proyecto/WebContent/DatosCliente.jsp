<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
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
<jsp:include page="Encabezado.jsp"></jsp:include>
  <h2>Mis datos</h2>
</div>
 

	<form action="EditarCliente" method="get">
    <button type="button" class="accordion">Información Personal &#x1F4DD;</button>
    <div class="panel">
     <div class="flex-container">
        <div class="form-group flex-item" style= "margin-top: 10px;">
            <label for="dni">DNI:</label>
               <input type="text" id="dni" name="dni" value="<%= request.getAttribute("dni") %>"   readonly required>
        </div>
           <div class="form-group flex-item" style= "margin-top: 10px;">
            <label for="cuil">CUIL:</label>
            <input type="text" id="cuil" name="cuil" value="<%= request.getAttribute("cuil") %>"  readonly  required>
        </div>
        <div class="form-group flex-item">
            <label for="nombre">Nombre:</label>
            <input type="text" id="nombre" name="nombre" value="<%= request.getAttribute("nombre") %>"  readonly  required>
        </div>
       <div class="form-group flex-item">
            <label for="apellido">Apellido:</label>
            <input type="text" id="apellido" name="apellido" value="<%= request.getAttribute("apellido") %>"  readonly  required>
        </div>
       <div class="form-group flex-item">
            <label for="sexo">Sexo:</label>
            <input type="text" id="sexo" name="sexo" value="<%= request.getAttribute("sexo") %>"  readonly required>
        </div>
        <div class="form-group flex-item">
            <label for="fechaNacimiento">Fecha de Nacimiento:</label>
            <input type="date" id="fechaNacimiento" name="fechaNacimiento" value="<%= request.getAttribute("fechaNacimiento") %>"  readonly required>
        </div>
        <div class="form-group flex-item">
            <label for="nacionalidad">Nacionalidad:</label>
            <input type="text" id="nacionalidad" name="nacionalidad" value="<%= request.getAttribute("nacionalidad") %>"   readonly required>
        </div>
        </div>
    </div>

    <button type="button" class="accordion">Domicilio  &#x1F3E0;</button>
    <div class="panel">
  	  <div class="flex-container">
        <div class="form-group flex-item" style= "margin-top: 10px;">
         <label for="localidad">Localidad:</label>
            <input type="text" id="localidad" name="localidad" value="<%= request.getAttribute("localidad") %>"  readonly required> 
        </div>
        <div class="form-group flex-item" style= "margin-top: 10px;">
        <label for="provincia">Provincia:</label>
            <input type="text" id="provincia" name="provincia" value="<%= request.getAttribute("provincia") %>"  readonly required>
        </div>
        <div class="form-group flex-item">
           <label for="direccion">Dirección:</label>
            <input type="text" id="direccion" name="direccion" value="<%= request.getAttribute("direccion") %>"  readonly required>
        </div>
          </div>
            <p style="font-size: smaller; font-weight: bold;">* Aclarar piso y departamento en caso de corresponder</p>

    </div>
    
    
    <button type="button" class="accordion">Información de Contacto &#x1F4F1;</button>
    <div class="panel">
      <div class="flex-container">
         <div class="form-group flex-item" style= "margin-top: 10px;">
           <label for="Celular">Celular:</label>
            <input type="text" id="celular" name="celular" value="<%= request.getAttribute("celular") %>"  readonly required>
        </div>
       <div class="form-group flex-item" style= "margin-top: 10px;">
            <label for="telefonos">Telófono:</label>
            <input type="text" id="telefonos" name="telefonos" value="<%= request.getAttribute("telefonos") %>"  readonly required>
        </div>
        <div class="form-group  flex-item">
         <label for="correoElectronico">Correo Electrónico:</label>
            <input type="email" id="correoElectronico" name="correoElectronico" value="<%= request.getAttribute("correoElectronico") %>"  readonly required>
        </div>
      </div>
    </div>


    <button type="button" class="accordion">Datos de Usuario &#x1F511;</button>
    <div class="panel">
         <div class="form-group" style= "margin-top:10px;">
            <label for="usuario">Usuario:</label>
            <input type="text" id="usuario" name="usuario" value="<%= request.getAttribute("usuario") %>" readonly style="background-color: #e9ecef;">
        </div>
        <div class="form-group flex-item">
            <label for="contrasena">Contraseña:</label>
            <input type="password" id="contrasena" name="contrasena" value="<%= request.getAttribute("contrasena") %>" readonly style="background-color: #e9ecef;">
        </div>
      
    </div>

    <input type="submit" value="Aceptar" name="btnAceptar">
    <input type="button" value="Volver" name="btnVolver" onclick="window.location.href='InicioCliente.jsp';">

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