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
		<H1 class = "centrado">CERRAR SESIÓN</H1>
		<div class = "contenedor">
			<div class = "contenedorVertical">
				
				<form action = "/Home/Desconectar" method = "POST">
					<div class = "contenedorVertical">
						<table class = "tablaEstilada">
							<thead>
								<tr>
								 <th colspan="2">SALIR </th>
								</tr>
							</thead>
							<tr>
								<td colspan = "2" class = "centrado"><BR><img src = "img/salir.png"> <BR><BR><button type = "submit" value = "LOGOUT" style = "width:100%; background-color:red;">CERRAR SESIÓN</button></td>
							</tr>
							<%
								if(request.getAttribute("mensaje") != null){%>
		
								<tr><td colspan="2" class = "centrado" style = "color:red;"><%= request.getAttribute("mensaje") %></td></tr>
			
							  <%}%>
						</table><BR>
					</div>
				</form>
			</div>
		</div>
		<div class = "ajusteFooter"></div>
		<%@include file="Footer.jsp" %>
	</body>
</html>