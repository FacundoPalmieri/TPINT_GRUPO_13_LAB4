<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="entidad.Persona"%>
<%@page import="entidad.Direccion"%>
<%@page import="entidad.Provincia"%>
<%@page import="entidad.Localidad"%>
<%@page import="entidad.Usuario"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
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
</head>
<body>



	<div class="banner">
	<div class="logo_encabezado_izquierda">
	    <img src="img/Grupo 13_encabezado.png" alt="Logo" class="logo_encabezado">
	    <h3>Modificar Cliente</h3>
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
	Usuario usuario = new Usuario();
	
 	
	persona   = (Persona)request.getAttribute("persona");
	direccion = (Direccion)request.getAttribute("direccion");
	provincia = (Provincia)request.getAttribute("provincia");
	localidad = (Localidad)request.getAttribute("localidad");
	usuario = (Usuario)request.getAttribute("usuario");
	
 %>
	
 <form action="EditarCliente" method="post">
    <div id="BusquedaCliente">
    <% if (request.getParameter("usuario1")!=null && request.getParameter("dniCliente1")!=null){%>
        <input type="text" id="dniCliente" name="dniCliente" placeholder="Ingrese el DNI del cliente" value="<%=request.getParameter("dniCliente1")%>" readonly style="background-color: #e9ecef;">
        <input type="submit" value="Buscar" name="btnBuscar" style="background-color: #78AD89">
    <%}else{ %>
    	<input type="text" id="dniCliente" name="dniCliente" placeholder="Ingrese el DNI del cliente" value="<%=(request.getParameter("dniCliente") != null) ? request.getParameter("dniCliente") : "" %>" required>
        <input type="submit" value="Buscar" name="btnBuscar" style="background-color: #78AD89">
        <%} %>
    </div>

 </form>
 
 <div style="display: flex; justify-content: center; align-items: center; margin: 0 auto;">
    <form action="EditarCliente" method="post" style="max-width: 800px; width: 100%;">
        <button type="button" class="accordion">Información Personal &#x1F4DD;</button>
        <div class="panel">
            <div class="flex-container">
                <div class="form-group flex-item" style="margin-top: 10px;">
                    <label for="dni">DNI:</label>
                    <input type="text" id="dni" name="dni" value="<%= persona != null ? persona.getDni() : "" %>" required>
                </div>
                <div class="form-group flex-item" style="margin-top: 10px;">
                    <label for="cuil">CUIL:</label>
                    <input type="text" id="cuil" name="cuil" value="<%= persona != null ? persona.getCuil() : "" %>" required>
                </div>
                <div class="form-group flex-item">
                    <label for="nombre">Nombre:</label>
                    <input type="text" id="nombre" name="nombre" value="<%= persona != null ? persona.getNombre() : "" %>" required>
                </div>
                <div class="form-group flex-item">
                    <label for="apellido">Apellido:</label>
                    <input type="text" id="apellido" name="apellido" value="<%= persona != null ? persona.getApellido() : "" %>" required>
                </div>
                <div class="form-group flex-item">
                    <label for="sexo">Sexo:</label>
                    <input type="text" id="sexo" name="sexo" value="<%= persona != null ? persona.getSexo() : "" %>"required>
                </div>
                <div class="form-group flex-item">
                    <label for="fechaNacimiento">Fecha de Nacimiento:</label>
                    <input type="date" id="fechaNacimiento" name="fechaNacimiento" value="<%= persona != null ? persona.getFechaNacimiento() : "" %>" required>
                </div>
                <div class="form-group flex-item">
                    <label for="nacionalidad">Nacionalidad:</label>
                    <input type="text" id="nacionalidad" name="nacionalidad" value="<%= persona != null ? persona.getNacionalidad() : "" %>" required>
                </div>
            </div>
        </div>

        <button type="button" class="accordion">Domicilio &#x1F3E0;</button>
        <div class="panel">
            <div class="flex-container">
                <div class="form-group flex-item" style="margin-top: 10px;">
                    <label for="pais">País:</label>
                    <input type="text" id="pais" name="pais" value="Argentina" required>
                </div>
                <div class="form-group flex-item" style="margin-top: 10px;">
                    <label for="provincia">Provincia:</label>
                    <input type="text" id="provincia" name="provincia" value="<%= provincia != null ? provincia.getNombre() : "" %>" required>
                    <input type="hidden" id="provincia_id" name="provincia_id" value="<%= provincia != null ? provincia.getId() : "" %>">
                </div>
                <div class="form-group flex-item" style="margin-top: 10px;">
                    <label for="localidad">Localidad:</label>
                    <input type="text" id="localidad" name="localidad" value="<%= localidad != null ? localidad.getNombre() : "" %>" required>
                  	 <input type="hidden" id="localidad_id" name="localidad_id" value="<%= localidad != null ? localidad.getId() : "" %>">
				</div>
                </div>
                <div class="form-group-domicilio">
                    <div class="group">
                        <label for="calle">Calle:</label>
                        <input type="text" id="calle" name="calle" value="<%= direccion != null ? direccion.getCalle() : "" %>"  required>
                    </div>
                    <div class="group">
                        <label for="numero">Número:</label>
                        <input type="text" id="numero" name="numero" value="<%= direccion != null ? direccion.getAltura() : "" %>"   required>
                    </div>
                    <div class="group">
                        <label for="piso">Piso:</label>
                        <input type="text" id="piso" name="piso" value="<%= direccion != null ? direccion.getPiso() : "" %>"   required>
                    </div>
                    <div class="group">
                        <label for="depto">Depto:</label>
                        <input type="text" id="depto" name="depto" value="<%= direccion != null ? direccion.getDepartamento() : "" %>"  required>
                    </div>
                </div>
            </div>


        <button type="button" class="accordion">Información de Contacto &#x1F4F1;</button>
        <div class="panel">
            <div class="flex-container">
                <div class="form-group flex-item" style="margin-top: 10px;">
                    <label for="celular">Celular:</label>
                    <input type="text" id="celular" name="celular" value="<%= persona != null ? persona.getCelular() : "" %>" required>
                </div>
                <div class="form-group flex-item" style="margin-top: 10px;">
                    <label for="telefonos">Teléfono:</label>
                    <input type="text" id="telefonos" name="telefonos" value="<%= persona != null ? persona.getTelefono(): "" %>" required>
                </div>
                <div class="form-group flex-item">
                    <label for="correoElectronico">Correo Electrónico:</label>
                    <input type="email" id="correoElectronico" name="correoElectronico" value="<%= persona != null ? persona.getEmail() : "" %>" required>
                </div>
            </div>
        </div>

        <button type="button" class="accordion">Datos de Usuario &#x1F511;</button>
        <div class="panel">
            <div class="form-group" style="margin-top:10px;">
                <label for="usuario">Usuario:</label>
                <input type="text" id="usuario" name="usuario" value="<%= usuario != null ? usuario.getUsuario() : "" %>" readonly style="background-color: #e9ecef;">
            </div>
            <div class="form-group" style="margin-top:10px;">
                <label for="pass">Contraseña:</label>
                <input type="text" id="pass" name="pass" value="<%= usuario != null ? usuario.getPass() : "" %>" >
            </div>
        </div>

        <div class="center-container" style="display: flex; justify-content: center; margin-top: 10px;">
            <input type="submit" value="Actualizar" name="btnActualizar">
            <input type="button" value="Volver" name="btnVolver" onclick="window.location.href='ABMLclientes.jsp';" style="margin-left: 10px;">
        </div>
    </form>
</div>

<div id="popup" class="popup">
    <span class="close-btn" onclick="closePopup()">&times;</span>
    <p id="popupMessage"></p>
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
    
    window.onload = function() {
        // Obtenemos el mensaje de error desde el servidor
        var errorMensaje = "<%= (request.getAttribute("mensaje") != null) ? request.getAttribute("mensaje") : "" %>";
        if (errorMensaje) {
            showPopup(errorMensaje);
        }
    };
    </script>   
</body>
</html>