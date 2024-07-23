<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="entidad.Persona"%>
<%@page import="entidad.Direccion"%>
<%@page import="entidad.Provincia"%>
<%@page import="entidad.Localidad"%>
<%@page import="entidad.Usuario"%>
<%@page import="entidad.Pais" %>
<%@page import="java.util.ArrayList" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
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
	Persona persona = (Persona) request.getAttribute("persona");
    Direccion direccion = (Direccion) request.getAttribute("direccion");
    Provincia provinciaSeleccionada = (Provincia) request.getAttribute("provincia");
    Localidad localidadSeleccionada = (Localidad) request.getAttribute("localidad");
    Usuario usuario = (Usuario) request.getAttribute("usuario");
	
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
                    <select id="sexo" name="sexo" required>
       			 	<option value="F" <%= (persona != null && "F".equals(persona.getSexo())) ? "selected" : "" %>>F</option>
        			<option value="M" <%= (persona != null && "M".equals(persona.getSexo())) ? "selected" : "" %>>M</option>
        			<option value="X" <%= (persona != null && "X".equals(persona.getSexo())) ? "selected" : "" %>>X</option>
   					</select>
                </div>
                <div class="form-group flex-item">
                    <label for="fechaNacimiento">Fecha de Nacimiento:</label>
                    <input type="date" id="fechaNacimiento" name="fechaNacimiento" value="<%= persona != null ? persona.getFechaNacimiento().toString() : "" %>" onblur="validarFechaNacimiento()"  required>
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
                    <select name="pais" id="pais">
					    <option value="">Selecciona un país</option>
					    <% 
					        ArrayList<Pais> listaPaises = (ArrayList<Pais>) request.getAttribute("paises");
					        if (listaPaises != null) {
					            for (Pais pais : listaPaises) {
					                boolean selected = (direccion != null && direccion.getLocalidad() != null && direccion.getLocalidad().getProvincia() != null && direccion.getLocalidad().getProvincia().getPais() != null && direccion.getLocalidad().getProvincia().getPais().getId() == pais.getId());
					    %>
					                <option value="<%= pais.getId() %>" <%= selected ? "selected" : "" %>><%= pais.getNombre() %></option>
					    <% 
					            }
					        } else {
					            out.println("No se encontraron países.");
					        }
					    %>
					</select>
                </div>
                <div class="form-group flex-item" style="margin-top: 10px;">
                    <label for="provincia">Provincia:</label>
                     <select name="provincia" id="provincia">
                        <option value="">Selecciona una provincia</option>
                        <% 
                            ArrayList<Provincia> listaProvincias = (ArrayList<Provincia>) request.getAttribute("provincias");
                            if (listaProvincias != null) {
                                for (Provincia provincia : listaProvincias) {
                                    boolean selected = (direccion != null && direccion.getLocalidad() != null && direccion.getLocalidad().getProvincia() != null && direccion.getLocalidad().getProvincia().getId() == provincia.getId());
                        %>
                                    <option value="<%= provincia.getId() %>" <%= selected ? "selected" : "" %>><%= provincia.getNombre() %></option>
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
                            ArrayList<Localidad> listaLocalidades = (ArrayList<Localidad>) request.getAttribute("localidades");
                            if (listaLocalidades != null) {
                                for (Localidad localidad : listaLocalidades) {
                                    boolean selected = (direccion != null && direccion.getLocalidad() != null && direccion.getLocalidad().getId() == localidad.getId());
                        %>
                                    <option value="<%= localidad.getId() %>" <%= selected ? "selected" : "" %>><%= localidad.getNombre() %></option>
                        <% 
                                }
                            }
                        %>
                    </select>
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
    // VALIDAR QUE LA PERSONA SEA MAYOR DE 18 AÑOS 
    
    function validarFechaNacimiento() {
        const fechaNacimientoInput = document.getElementById('fechaNacimiento');
        const fechaNacimiento = new Date(fechaNacimientoInput.value);
        const fechaActual = new Date();
        fechaActual.setFullYear(fechaActual.getFullYear() - 18);

        if (fechaNacimiento > fechaActual) {
            Swal.fire({
                icon: 'error',
                title: 'Fecha no válida',
                text: 'Debe ser mayor de 18 años.',
            }).then(() => {
                fechaNacimientoInput.value = '';
            });
        }
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