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
		$('#table_id').DataTable({
            language: {
                lengthMenu: "Mostrar _MENU_ registros",
                info: "Mostrando _START_ a _END_ de _TOTAL_ registros",
                infoEmpty: "Mostrando 0 a 0 de 0 registros",
                infoFiltered: "(filtrado de _MAX_ registros en total)",
                infoPostFix: "",
                loadingRecords: "Cargando...",
                zeroRecords: "No se encontraron registros coincidentes",
                emptyTable: "No hay datos disponibles en la tabla",
                paginate: {
                    first: "Primero",
                    previous: "Anterior",
                    next: "Siguiente",
                    last: "Último"
                },
                aria: {
                    sortAscending:  ": activar para ordenar columna ascendente",
                    sortDescending: ": activar para ordenar columna descendente"
                },
                lengthMenu: "Cantidad registros _MENU_"
            },
            dom: 'ltipr' // Controla los elementos que se muestran (l: longitud del menú, t: tabla, i: información, p: paginación, r: procesamiento)
        });
	});
	
	document.addEventListener("DOMContentLoaded",function(){
		toggleClientes();
		let tabla = document.getElementById('table_id');
		let botones = tabla.querySelectorAll('input[type="button"]');
		
		//Se carga el EventListener de los botones de las celdas al cargar el DOM
		botones.forEach(function(btn){
			btn.addEventListener("click",function(e){
				let fila= btn.parentNode.parentNode;
				let dni = fila.cells[1].textContent;
				let nombre = fila.cells[4].textContent;
				let apellido = fila.cells[5].textContent;
				let usuario = fila.cells[6].textContent;
			
				if(e.target.value==="Eliminar"){
					let estado = 1;
					enviarDatosEliminar(dni,estado,usuario,nombre,apellido);
				}
				else if(e.target.value==="Habilitar"){
					let estado = 0;
					enviarDatosEliminar(dni,estado,usuario,nombre,apellido);
				}
				else if(e.target.value==="Modificar"){
					enviarDatosModificar(dni,usuario);
				}
				else if(e.target.value="Ver Detalles"){
					enviarDetalles(usuario);
				}
			})
		})
		
		
		//Se carga la funcionalidad del filtro al cargar el DOM
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
    })
    
    //Esta funcion es llamada desde el boton Eliminar/Habilitar y va al jsp con get EliminaCliente con parametros
    function enviarDatosEliminar(dni,estado,usuario,nombre,apellido){
			let xhr = new XMLHttpRequest();
			xhr.open("POST","EliminarCliente.jsp","true");
			xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
			let params="dniCliente1="+encodeURIComponent(dni)+"&estadoCliente1="+encodeURIComponent(estado)+"&usuario1="+encodeURIComponent(usuario)
						+"&nombre1="+encodeURIComponent(nombre)+"&apellido1="+encodeURIComponent(apellido);
			xhr.send(params);
			xhr.onreadystatechange = function(){
				if(xhr.readyState===4 && xhr.status===200){
					window.location.href='EliminarCliente.jsp?'+params;
				}
				else if(xhr.readyState===4){
					console.log("Error al enviar los datos");
				}
			}
		}
	
	//Esta funcion es llamada desde el boton Modificar y va al jsp con get ModificarCliente con parametros
	function enviarDatosModificar(dni,usuario){
		let xhr = new XMLHttpRequest();
		xhr.open("POST","ModificarCliente.jsp","true");
		xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		let params="dniCliente1="+encodeURIComponent(dni)+"&usuario1="+encodeURIComponent(usuario)	
		xhr.send(params);
		xhr.onreadystatechange = function(){
			if(xhr.readyState===4 && xhr.status===200){
				window.location.href='ModificarCliente.jsp?'+params;
			}
			else if(xhr.readyState===4){
				console.log("Error al enviar los datos");
			}
		}
	}
	
	//Esta funcion es llamada desde el boton Detalles y va al jsp EditarCliente mediante post con el parametro usuario
	function enviarDetalles(usuario){
		let form = document.createElement('form');
	    form.method = 'post';
	    form.action = 'EditarCliente';
	    
	    let input = document.createElement('input');
	    input.type = 'hidden';
	    input.name = 'usuario';
	    input.value = usuario;
	    
	    form.appendChild(input);
	    document.body.appendChild(form);
	    form.submit();
	}
	
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
                        <th>Nombre</th>
            			<th>Apellido</th>
                        <th>Usuario</th>
                        <th>Opciones</th>
                        <th>Contrasenia</th>
                        <th>Detalles</th>
                    </tr>
                </thead>
                <tbody id="clientesTableBody">
                    <%
                    ArrayList<Persona> listaPersona = (ArrayList<Persona>) session.getAttribute("listaPersonas");    
                    if(listaPersona!=null){
                    	for (Persona persona : listaPersona){
                    %>
                    <tr class="<%= persona.getUsuario().getHabilitado() == 1 ? "habilitado" : "no-habilitado" %>">
                    	<td><%= persona.getApellido() %>, <%= persona.getNombre() %></td>
                        <td><%= persona.getDni() %></td>
                        <td>
                            <%= persona.getDireccion().getCalle() %> <%= persona.getDireccion().getAltura() %>
                            <% if (persona.getDireccion().getPiso() != null && !persona.getDireccion().getPiso().isEmpty()) { %>
                                , Piso: <%= persona.getDireccion().getPiso() %>
                            <% } %>
                            <% if (persona.getDireccion().getDepartamento() != null && !persona.getDireccion().getDepartamento().isEmpty()) { %>
                                , Depto: <%= persona.getDireccion().getDepartamento() %>
                            <% } %>
                        </td>
                        <td><%= persona.getEmail() %></td>
                        <th><%= persona.getNombre() %></th>
            			<th><%= persona.getApellido() %></th>
                        <td><%= persona.getUsuario().getUsuario() %></td>
                        <td><input type="button" value="<%= persona.getUsuario().getHabilitado()==1 ? "Eliminar" : "Habilitar"%>" name="<%=persona.getUsuario().getHabilitado()==1 ? "btnListarEliminar" : "btnListarHabilitar" %>" class="btnEspecial"></td>
                        <% if (persona.getUsuario().getHabilitado() == 1) { %>
                           <td><input type="button" value="Modificar" name="btnListarModificar" class="btnEspecial"></td>
                         <%}else{%>
                         	<td><input type="button" value="Opcion Deshabilitada" name="sinFuncion" class="btnEspecial"></td>
                         <%} %>      
                           <td><input type="button" value="Ver Detalles" name="btnListarDetalles" class="btnEspecial"></td>
                    </tr>
                    	<%}%>
                    <%} else{%>
                    <tr>
                        <td colspan="7">No hay datos disponibles</td>
                    </tr>
                    <%}%>
                </tbody>
            </table>
        </div>
        <input type="button" value="Volver" name="btnVolver" onclick="window.location.href='ABMLclientes.jsp';">
    <%} else{%>
        <h1>No tiene permisos para trabajar en esta URL, presione <a href="Login.jsp">aquí</a> para volver al Login</h1>
    <%}%>
</body>

</html>
