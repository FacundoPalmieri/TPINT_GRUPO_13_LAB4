<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
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

<div id="General">
    <div id="Bienvenido">
        <div id="pantalla_principal_izquierda">
            <img src="img/Grupo 13.png" class="logo">
            <h1>Online Banking</h1>
        </div>
        <img src="img/gift.gif" id="gift-bienvenida">
    </div>

    <div id="Login">
        <h2>¡Hola! Te damos la bienvenida</h2>
        <h4>Completá tus datos y empezá a operar.</h4>

        <form method="post" action="ServletUsuario" id="formUsuario">
            <div>
                <p style="margin-top: 10%;">
                    <input id="usuario" type="text" placeholder="Usuario" required name="txtUsuario">
                </p>
                <p>
                    <input id="contrasenia" type="password" placeholder="Contraseña" name="txtContrasenia">
                </p>
                <p>
                    <input type="submit" name="btnIngresar" value="Ingresar"><br>
                </p>
	        	    	
			    <div id="popup" class="popup">
			        <span class="close-btn" onclick="closePopup()">&times;</span>
			        <p id="popupMessage"></p>
			    </div>
		 </div>
        </form>
    </div>
</div>

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
	    
	    
	    function enviarDatos(usuario,contrasenia){
	    	let xhr = new XMLHttpRequest();
			xhr.open("POST","ServletUsuario","true");
			xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
			let params="btnIngresar=1&txtUsuario="+encodeURIComponent(usuario)+"&txtContrasenia="+encodeURIComponent(contrasenia);
			xhr.onreadystatechange=function(){
				if(xhr.readyState===4 && xhr.status===200){
					
					if(xhr.responseText=="2"){
					<%--	showPopup("Bienvenido Cliente, aguarde unos segundos");
						setTimeout(function(){
							
						},2000) --%>
						window.location.href='InicioCliente.jsp';
					}
					else if(xhr.responseText=="1"){
						window.location.href='UsuarioAdministrador.jsp';
					}
					else if(xhr.responseText=="3"){
						showPopup("El usuario se encuentra bloqueado, comuniquese con el banco");
					}
					else if(xhr.responseText=="4"){
						showPopup("Usuario o contrasenia incorrectos");
					}
					else{
						showPopup("El usuario no existe");
					}
				}
				else if(xhr.readyState===4){
					console.log("Error al enviar los datos");
				}
			}
			xhr.send(params);
		}

	    document.addEventListener('DOMContentLoaded', function() {
	        let formulario = document.getElementById('formUsuario');
	        formulario.addEventListener("submit",function(e){
	        	e.preventDefault();
	        	let usuario = document.getElementById("usuario").value;
	        	let contrasenia = document.getElementById("contrasenia").value;
	        	enviarDatos(usuario,contrasenia);
	        })
	        
	        <%
	        	//Boolean validacion = (Boolean) request.getAttribute("validacionCliente");
	            //if (validacion != null && !validacion) //{
	        %>
	            //showPopup("Usuario o Contraseña Incorrecta");
	        <%
	            //}
	        %>
	    });
	</script>

</body>
</html>

