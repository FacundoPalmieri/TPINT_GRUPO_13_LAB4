<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
<div id="General">
        <div class="banner">
            <div class="logo_encabezado_izquierda">
                <img src="img/Grupo 13_encabezado.png" alt="Logo" class="logo_encabezado">
                <h3>Mis Cuentas</h3>
            </div>
            <div class="logo_encabezado_derecha">
                <%= (String) session.getAttribute("nombre") %>
                <a href="ServletCerrarSesion" class="logout">
                    <img src="img/logout.png" alt="Logout" class="logo_encabezado">
                </a>
            </div>
        </div>

        <table class="table">
            <thead>
                <tr>
                    <th>N�mero de Cuenta</th>
                    <th>Tipo de Cuenta</th>
                    <th>Saldo</th>
                </tr>
           
        </table>

        <div class="button-container">
            <input type="button" value="Volver" name="btnVolver" onclick="window.location.href='InicioCliente.jsp';"> 
        </div>
    </div>
</body>
</html>