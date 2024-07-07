

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

        <form method="post" action="ServletUsuario">
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

	    document.addEventListener('DOMContentLoaded', function() {
	        <%
	        	Boolean validacion = (Boolean) request.getAttribute("validacionCliente");
	            if (validacion != null && !validacion) {
	        %>
	            showPopup("Usuario o Contraseña Incorrecta");
	        <%
	            }
	        %>
	    });
	</script>

</body>
</html>

