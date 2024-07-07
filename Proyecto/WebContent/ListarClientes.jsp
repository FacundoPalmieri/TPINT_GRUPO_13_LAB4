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
<title>Listado de Clientes</title>
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
	<% if(session.getAttribute("tipoUsuario")!=null){%>
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
	
   <div class="filtro-container">
        <label for="txtFiltro">Filtrar</label>
        <input type="text" id="txtFiltro">
    </div>
    
<div style= "margin: 0.5%;">
   <table id="table_id" class="display">
        <tr>
         
            <th>DNI</th>
            <th>Nombre</th>
            <th>Apellido</th>
            <th>Dirección</th>
            <th>Altura</th>
            <th>Email</th>
            <th>Usuario</th>
            <th>Opciones</th>
        </tr>
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
        <tr>
          
               <td><%= persona.getDni() %></td>
               <td><%= persona.getNombre() %></td>
               <td><%= persona.getApellido() %></td>
               <td><%= direccion.getCalle() %></td>
               <td><%= direccion.getAltura() %></td>
               <td><%= direccion.getPiso() %></td>
               <td><%= direccion.getDepartamento() %></td>
               <td><%= persona.getEmail() %></td>
               <td><%= usuario.getUsuario() %></td>
 

			<td><input type="button" value="<%= usuario.getHabilitado()==1 ? "Eliminar" : "Habilitar"%>" name="btnEliminar" class="btnEspecial"></td>
			<% if (usuario.getHabilitado()==1){%>
			<td><input type="button" value="Modificar Contraseña" name="btnModificar" class="btnEspecial"></td>
			<%}%>
        </tr>
        <%
                }
            } else {
        %>
   
        <%
            }
        %>
       
    </table>
    <input type="button" value="Volver" name="btnVolver" onclick="window.location.href='ABMLclientes.jsp';">
    <%}else{%>
 	<h1>No tiene permisos para trabajar en esta URL, presione <a href="Login.jsp">aquí</a> para volver al Login</h1>
 <%}%>
 
 
 </div>
</body>
</html>