<%@page import="java.util.ArrayList" %>
<%@page import="entidad.Usuario" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="entidad.Pais" %>
<%@page import="entidad.Provincia" %>
<%@page import="entidad.Localidad" %>
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

<title>Agregar Cliente</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>


<div id="General">
	<div class="banner">
	<div class="logo_encabezado_izquierda">
	    <img src="img/Grupo 13_encabezado.png" alt="Logo" class="logo_encabezado">
	    <h3>Crea tu usuario</h3>
	</div>
	<div class="logo_encabezado_derecha">
	    <%= (String) session.getAttribute("usuario") %>
	    <a href="ServletCerrarSesion" class="logout">
	        <img src="img/logout.png" alt="Logout" class="logo_encabezado">
	    </a>
	</div>

	</div>
 

	<form action="AltaCliente" method="post" >
    <button type="button" class="accordion">Información Personal &#x1F4DD;</button>
    <div class="panel">
     <div class="flex-container">
        <div class="form-group flex-item" style= "margin-top: 10px;">
            <label for="dni">DNI:</label>
            <input type="text" id="dni" name="dni" required>
        </div>
           <div class="form-group flex-item" style= "margin-top: 10px;">
            <label for="cuil">CUIL:</label>
            <input type="text" id="cuil" name="cuil" required>
        </div>
        <div class="form-group flex-item">
            <label for="nombre">Nombre:</label>
            <input type="text" id="nombre" name="nombre" required>
        </div>
       <div class="form-group flex-item">
            <label for="apellido">Apellido:</label>
            <input type="text" id="apellido" name="apellido" required>
        </div>
       <div class="form-group flex-item">
            <label for="sexo">Sexo:</label>
            	<select id="sexo" name="sexo" required>
                	<option value="1">F</option>
                	<option value="2">M</option>
                	<option value="3">X</option>
                </select>
        </div>
        <div class="form-group flex-item">
            <label for="fechaNacimiento">Fecha de Nacimiento:</label>
            <input type="date" id="fechaNacimiento" name="fechaNacimiento" required>
        </div>
        <div class="form-group flex-item">
            <label for="nacionalidad">Nacionalidad:</label>
            <input type="text" id="nacionalidad" name="nacionalidad" required>
        </div>
        </div>
    </div>

    <button type="button" class="accordion">Domicilio  &#x1F3E0;</button>
<div class="panel">
    <div class="flex-container">
        <div class="form-group flex-item" style="margin-top: 10px;">
            <label for="pais">País:</label>
            <select name="pais" id="pais">
                <option value="">Selecciona un país</option>
                <% 
                ArrayList<Pais> listaPaises = null;
                listaPaises = (ArrayList<Pais>) request.getAttribute("paises");
                if (listaPaises != null) {
                    for (Pais pais : listaPaises) {
                    	 System.out.println(pais);
                %>
                <option value="<%= pais.getId() %>"><%= pais.getNombre() %></option>
                <% 
                    }
                }
                %>
            </select>
        </div>
        <div class="form-group flex-item" style="margin-top: 10px;">
            <label for="provincia">Provincia:</label>
            <select name="provincia" id="provincia">
                <option value="">Selecciona una provincia</option>
                 <% 
                ArrayList<Provincia> listaProvincias = null;
                 listaProvincias = (ArrayList<Provincia>) request.getAttribute("provincias");
                if (listaProvincias != null) {
                    for (Provincia provincia : listaProvincias) {
                    	 System.out.println(provincia);
                %>
                <option value="<%= provincia.getId() %>"><%= provincia.getNombre() %></option>
                <% 
                    }
                }
                %>
            </select>
        </div>
        <div class="form-group flex-item" style="margin-top: 10px;">
            <label for="localidad">Localidad:</label>
             <select name="localidad" id="localidad">
        <option value="">Selecciona una localidad</option>
        <% 
        ArrayList<Localidad> listaLocalidades = null;
        listaLocalidades = (ArrayList<Localidad>) request.getAttribute("localidades");
        if (listaLocalidades != null) {
            for (Localidad localidad : listaLocalidades) {
        %>
        <option value="<%= localidad.getId() %>"><%= localidad.getNombre() %></option>
        <% 
            }
        }
        %>
    </select>
        </div>
        <div class="form-group-domicilio">
            <div class="group">
                <label for="calle">Calle:</label>
                <input type="text" id="calle" name="calle" required>
            </div>
            <div class="group">
                <label for="numero">Número:</label>
                <input type="text" id="numero" name="numero" required>
            </div>
            <div class="group">
                <label for="piso">Piso:</label>
                <input type="text" id="piso" name="piso">
            </div>
            <div class="group">
                <label for="depto">Depto:</label>
                <input type="text" id="depto" name="depto">
            </div>
        </div>
    </div>
</div>
    
    
    
    <button type="button" class="accordion">Información de Contacto &#x1F4F1;</button>
    <div class="panel">
      <div class="flex-container">
         <div class="form-group flex-item" style= "margin-top: 10px;">
           <label for="Celular">Celular:</label>
            <input type="text" id="celular" name="celular" required>
        </div>
       <div class="form-group flex-item" style= "margin-top: 10px;">
            <label for="telefonos">Teléfono:</label>
            <input type="text" id="telefonos" name="telefonos" required>
        </div>
        <div class="form-group  flex-item">
         <label for="correoElectronico">Correo Electrónico:</label>
            <input type="email" id="correoElectronico" name="correoElectronico" required>
        </div>
      </div>
    </div>


    <button type="button" class="accordion">Datos de Usuario &#x1F511;</button>
    <div class="panel">
         <div class="form-group" style= "margin-top:10px;">
            <label for="usuario">Usuario:</label>
            <input type="text" id="usuario" name="usuario" required>
        </div>
        
    
        <div class="form-group flex-item">
            <label for="contrasena">Contraseña:</label>
            <input type="password" id="contrasena" name="contrasena" required>
        </div>
         <div class="form-group flex-item">
            <label for="contrasena">Reingrese Contraseña:</label>
            <input type="password" id="contrasena2" name="contrasena2" required>
        </div>
    
      
    </div>

    <input type="submit" value="Aceptar" name="btnAceptar">
    <input type="button" value="Volver" name="btnVolver" onclick="window.location.href='ABMLclientes.jsp';">

	</form>

	    	
    <div id="popup" class="popup">
        <span class="close-btn" onclick="closePopup()">&times;</span>
        <p id="popupMessage"></p>
   </div>
 <script>
        document.addEventListener('DOMContentLoaded', function() {
            <% 
                Boolean estadoCliente = (Boolean) request.getAttribute("estadoCliente");
                Boolean validacion = (Boolean) request.getAttribute("validacionCliente");
                String errorMensaje = (String) request.getAttribute("errorMensaje");

                System.out.println("Estado Cliente: " + estadoCliente);
                System.out.println("Validación Cliente: " + validacion);
                System.out.println("Error Mensaje: " + errorMensaje);

                if (validacion != null) {
                    if (validacion == false) { 
            %>
                        showPopup("Usuario ya existente.");
            <% 
                    } else {
            %>
           			 showPopup("<%= estadoCliente != null && estadoCliente ? "Usuario agregado con éxito" : "Usuario no agregado, vuelva a intentarlo." %>");
            <% 
                    }
                } else if (errorMensaje != null) {
            %>
                    showPopup("<%= errorMensaje %>");
            <% 
                }
            %>
        });
    </script>
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
    
    
    
    document.addEventListener('DOMContentLoaded', function() {
        var provinciaSelect = document.getElementById('provincia');
        var localidadSelect = document.getElementById('localidad');

        provinciaSelect.addEventListener('change', function() {
            var provinciaId = this.value;

            // Hacer una llamada AJAX al servlet para obtener las localidades
            var xhr = new XMLHttpRequest();
            xhr.open('GET', 'AltaCliente?provincia=' + provinciaId, true);
            xhr.onload = function() {
                if (xhr.status === 200) {
                    var localidades = JSON.parse(xhr.responseText);

                    // Limpiar opciones actuales
                    localidadSelect.innerHTML = '<option value="">Selecciona una localidad</option>';

                    // Agregar las nuevas opciones de localidades
                    localidades.forEach(function(localidad) {
                        var option = document.createElement('option');
                        option.value = localidad.id;
                        option.textContent = localidad.nombre;
                        localidadSelect.appendChild(option);
                    });
                } else {
                    console.log('Error al obtener localidades');
                }
            };
            xhr.send();
        });
    });
    

</script>
</body>
</html>