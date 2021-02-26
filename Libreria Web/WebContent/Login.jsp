<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<link rel="stylesheet" type="text/css"  href="styles.css" />
		<title>Login</title>
	</head>
	<body>
		<%@include file="Header.jsp" %>
		<H1 class = "centrado">INICIO DE SESI�N</H1>
		<div class = "contenedor">
			<div class = "contenedorVertical">
				<form action = "/Home/Authentication" method = "POST">
					<div class = "contenedorVertical">
						<table class = "tablaEstilada">
							<thead>
								<tr>
								 <th colspan="2">IDENTIF�CATE PARA ACCEDER </th>
								</tr>
							</thead>
							<tr>
								<td colspan="2"><input type = "text" name = "user" class = "login" placeholder="Nombre de Usuario" required><BR></td>
							</tr>
							<tr>
								<td colspan="2"><input type = "password" name = "pass" class = "login" placeholder="Contrase�a" required><BR></td>
							</tr>
							<tr>
								<td><button type = "reset" value = "Limpiar" style = "width:100%">LIMPIAR</button><BR></td>
								<td><button type = "submit" value = "Enviar" style = "width:100%">ACCEDER</button></td>
							</tr>
							<%
								if(request.getAttribute("mensaje") != null){%>
		
								<tr><td colspan="2" class = "centrado" style = "color:red;"><%= request.getAttribute("mensaje") %></td></tr>
			
							  <%}%>
							<tr>
								<td colspan="2" class = "centrado">�No tienes una cuenta? <a href = "/Home/Registro">�Reg�strate!</a></td>
							</tr>
						</table><BR>
					</div>
				</form>
			</div>
		</div>
		<div class = "ajusteFooter"></div>
		<%@include file="Footer.jsp" %>
	</body>
</html>