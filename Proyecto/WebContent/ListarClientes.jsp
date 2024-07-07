<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.List"%>
<%@page import="entidad.Usuario"%>
<%@page import="entidad.Persona"%>
<%@page import="entidad.Direccion"%>
<%@page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<style>
	<jsp:include page="css\Style.css"></jsp:include>
	
    .filtro-container {
        display: flex;
        align-items: center;
        margin-bottom: 10px;
        margin-top: 10px;
        margin-left:35px;
    }

    #txtFiltro {
        width: 250px;
        margin-left: 10px;
    }
    
</style>

<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">
	
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
	
<script type="text/javascript" charset="utf8"
	src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>



<script type="text/javascript">
	$(document).ready(function() {
		$('#table_id').DataTable();
	});
	
	document.addEventListener("DOMContentLoaded",function(){
		let botonesEliminar = document.getElementsByName("btnEliminar");
		let botonesModificar = document.getElementsByName("btnModificar");
		botonesEliminar.forEach(function(boton){
			boton.addEventListener("click",function(){
				let fila= boton.parentNode.parentNode;
				let dni = fila.cells[1].textContent;
				let estado = fila.cells[2].textContent;
				let nombre = fila.cells[4].textContent;
				let apellido = fila.cells[5].textContent;
				let usuario = fila.cells[13].textContent;
				enviarDatosEliminar(dni,estado,usuario,nombre,apellido);
			})
		})
		
		botonesModificar.forEach(function(boton){
			boton.addEventListener("click",function(){
				let fila= boton.parentNode.parentNode;
				let dni = fila.cells[1].textContent;
				let usuario = fila.cells[13].textContent;
				enviarDatosModificar(dni,usuario);
			})
		})
		
		  const inputFiltro = document.querySelector('#txtFiltro');
        inputFiltro.addEventListener('keyup', function() {
            let filterValue = inputFiltro.value.toLowerCase();
            let table = document.querySelector('#table_id');
            let rows = table.getElementsByTagName('tr');

            for (let i = 1; i < rows.length; i++) {
                let cells = rows[i].getElementsByTagName('td');
                let match = false;
                for (let j = 0; j < cells.length; j++) {
                    if (cells[j].textContent.toLowerCase().includes(filterValue)) {
                        match = true;
                        break;
                    }
                }
                rows[i].style.display = match ? '' : 'none';
            }
        });
    });
	
	
	function enviarDatosEliminar(dni,estado,usuario,nombre,apellido){
		let xhr = new XMLHttpRequest();
		xhr.open("POST","EliminarUsuario.jsp","true");
		xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		let params="dniCliente="+encodeURIComponent(dni)+"&estado="+encodeURIComponent(estado)+"&usuario="+encodeURIComponent(usuario)
					+"&nombre="+encodeURIComponent(nombre)+"&apellido="+encodeURIComponent(apellido);
		
		xhr.send(params);
		xhr.onreadystatechange = function(){
			if(xhr.readyState===4 && xhr.status===200){
				window.location.href='EliminarUsuario.jsp?'+params;
			}
			else if(xhr.readyState===4){
				console.log("Error al enviar los datos");
			}
		}
	}
	
	
	function enviarDatosModificar(dni,usuario){
		let xhr = new XMLHttpRequest();
		xhr.open("POST","ModificarUsuario.jsp","true");
		xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		let params="dniCliente="+encodeURIComponent(dni)+"&usuario="+encodeURIComponent(usuario)
		
		xhr.send(params);
		xhr.onreadystatechange = function(){
			if(xhr.readyState===4 && xhr.status===200){
				window.location.href='ModificarUsuario.jsp?'+params;
			}
			else if(xhr.readyState===4){
				console.log("Error al enviar los datos");
			}
		}
	}
</script>



</head>
<body>
    <% if(session.getAttribute("tipoUsuario") != null) { %>
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
        <h3 style="display:flex; justify-content: center;">Clientes activos</h3>

        <div class="filtro-container">
            <label for="txtFiltro">Filtrar</label>
            <input type="text" id="txtFiltro">
        </div>
        
        <div class="toggle-container filtro-container">
        	<input type="checkbox" id="toggleHabilitados" onchange="toggleClientes()">
            <label for="toggleHabilitados">Mostrar todos</label>
        </div>
        
        <div style="margin: 0.5%;">
            <table id="table_id" class="display">
                <thead>
                    <tr>
                    	<th>Cliente</th>
                        <th>DNI</th>
                        <th>Dirección</th>
                        <th>Email</th>
                        <th>Usuario</th>
                        <th>Opciones</th>
                    </tr>
                </thead>
                <tbody id="clientesTableBody">
                    <%
                    ArrayList<Persona> listaPersona = (ArrayList<Persona>) request.getAttribute("listaPersonas");
                    ArrayList<Direccion> listaDireccion = (ArrayList<Direccion>) request.getAttribute("listaDirecciones");
                    ArrayList<Usuario> listaUsuario = (ArrayList<Usuario>) request.getAttribute("listaUsuarios");
                    if (listaPersona != null && listaDireccion != null && listaUsuario != null) {
                        for (int i = 0; i < listaPersona.size(); i++) {
                            Persona persona = listaPersona.get(i);
                            Direccion direccion = listaDireccion.get(i);
                            Usuario usuario = listaUsuario.get(i);
                    %>
                    <tr class="<%= usuario.getHabilitado() == 1 ? "habilitado" : "no-habilitado" %>">
                    	<td><%= persona.getApellido() %>, <%= persona.getNombre() %></td>
                        <td><%= persona.getDni() %></td>
                        <td>
                            <%= direccion.getCalle() %> <%= direccion.getAltura() %>
                            <% if (direccion.getPiso() != null && !direccion.getPiso().isEmpty()) { %>
                                , Piso: <%= direccion.getPiso() %>
                            <% } %>
                            <% if (direccion.getDepartamento() != null && !direccion.getDepartamento().isEmpty()) { %>
                                , Depto: <%= direccion.getDepartamento() %>
                            <% } %>
                        </td>
                        <td><%= persona.getEmail() %></td>
                        <td><%= usuario.getUsuario() %></td>
                        <td style="display:flex;">
                            <input type="button" value="<%= usuario.getHabilitado() == 1 ? "Eliminar" : "Habilitar" %>" name="btnEliminar" class="btnEspecial" style="margin: 1px; padding: 8px 8px;">
                            <% if (usuario.getHabilitado() == 1) { %>
                                <input type="button" value="Modificar Contraseña" name="btnModificar" class="btnEspecial" style="margin: 1px; padding: 8px 8px;">
                            <% } %>
                        </td>
                    </tr>
                    <%
                        }
                    } else {
                    %>
                    <tr>
                        <td colspan="7">No hay datos disponibles</td>
                    </tr>
                    <%
                    }
                    %>
                </tbody>
            </table>
        </div>
        <input type="button" value="Volver" name="btnVolver" onclick="window.location.href='ABMLclientes.jsp';">
    <% } else { %>
        <h1>No tiene permisos para trabajar en esta URL, presione <a href="Login.jsp">aquí</a> para volver al Login</h1>
    <% } %>
    
    <script>
        function toggleClientes() {
            var checkbox = document.getElementById('toggleHabilitados');
            var habilitados = document.getElementsByClassName('habilitado');
            var noHabilitados = document.getElementsByClassName('no-habilitado');

            if (checkbox.checked) {
                for (var i = 0; i < noHabilitados.length; i++) {
                    noHabilitados[i].style.display = '';
                }
                for (var j = 0; j < habilitados.length; j++) {
                    habilitados[j].style.display = '';
                }
            } else {
                for (var i = 0; i < noHabilitados.length; i++) {
                    noHabilitados[i].style.display = 'none';
                }
                for (var j = 0; j < habilitados.length; j++) {
                    habilitados[j].style.display = '';
                }
            }
        }

        document.addEventListener('DOMContentLoaded', function() {
            toggleClientes();
        });
    </script>
</body>

</html>