<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<link rel="stylesheet" type="text/css"  href="styles.css" />
		<title>Redirigiendo...</title>
	</head>
	<body>
		<%
			String mensaje = (String)request.getAttribute("mensaje");
			String redirection = (String)request.getAttribute("redireccion");
		%>
	
		<%@include file="Header.jsp" %>
		<div class = "contenedor">
			<div class = "contenedorVertical">
				<H1 class = "centrado"><%= mensaje %></H1>
				<div class = "contenedor">
					<form action = "/Home/Homepage" method = "POST">
						<table class = "tablaEstilada">
							<tbody>
								<tr>
								<td><button type = "submit" name = "<%= redirection %>" value = "<%= redirection %>" style = "width:100%">VOLVER</button></td>
								</tr>
							</tbody>
						</table>
					</form>
				</div>
			</div>
		</div>
		<div class = "ajusteFooter"></div>
		<%@include file="Footer.jsp" %>
	</body>
		
</html>