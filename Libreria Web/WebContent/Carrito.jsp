<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<link rel="stylesheet" type="text/css"  href="styles.css" />
		<!--  IMPORTS -->
		<%@ page
			import = "java.util.List"
			import = "java.sql.ResultSet"
			import="java.sql.ResultSetMetaData"
			import = "java.sql.SQLException"
		%>
		<title>Carrito</title>
	</head>
	<body>
		<!--  EXTRACCIÓN DE DATOS DE LA REQUEST -->
		<%	
			List<ResultSet> infoCarrito = null;
			ResultSetMetaData metadatos = null;
			if(request.getAttribute("infoCarrito") != null){
				 
				infoCarrito = (List<ResultSet>)request.getAttribute("infoCarrito");
				if(infoCarrito.get(0) != null){
					metadatos = infoCarrito.get(0).getMetaData();
				}
			}
		%>		
		<%@include file="Header.jsp" %>
		<H1 class = "centrado">CARRITO</H1>
		<!-- LA BARRA DE BÚSQUEDA -->
		<%@include file="BarraBusqueda.jsp" %>
		<div class = "contenedor">
			<%
			if(request.getAttribute("infoCarrito") != null){
				%>
				<form action = "/Home/ControlCarrito" method = "GET">
				<table class = "tablaEstilada">
					<thead>
						<tr>
							<th>PORTADA</th>
				<%  		try{
					
								for (int i = 1; i <= metadatos.getColumnCount(); i++) { 
				%>
									<th><%=metadatos.getColumnLabel(i) %></th>
				<%				}
				%>
									<th>CANTIDAD</th>
									<th>RETIRAR</th>
						</tr>
					</thead>
					
				<!--  SCRIPTLET -->
				<!--  GENERA UNA FILA DE LA TABLA POR CADA ELEMENTO
					  DEL RESULTSET PASADO COMO ATRIBUTO DE LA PETICIÓN -->
				<% 
							for(ResultSet rs : infoCarrito) {
								while(rs.next()){
									
									String titulo = rs.getString(2);
									int isbn = rs.getInt(1);
									String categoria = rs.getString(3);
									String editorial = rs.getString(4);
									String autores = rs.getString(5);
									Float precio = rs.getFloat(6);
									int cantidad = carrito.get(String.valueOf(isbn));
				%>
								<tr>
									<td style = "padding: 3px;" class = "centrado"><img src = <%="img/portadas/" + isbn + ".jpg" %> height = "100px"></td>
									<td><%= isbn %></td>
									<td><%= titulo %></td>
									<td><%= categoria %></td>
									<td><%= editorial %></td>
									<td><%= autores %></td>
									<td><%= precio %> Eur.</td>
									<td><%= cantidad %></td>
									<td><button type="submit" name = <%= isbn %> value = "remove" style = "width: 100%"><img src="img/sacarcarrito.png" alt="Quitar"></button></td>
								</tr>
				<%
								}
							}
						
						} catch (SQLException e) {
						
							e.printStackTrace();
						}
				%>
				</table>
				</form>
			<%}else{ %>
			
				<table class = "tablaEstilada">
					<thead>
						<tr>
							<th>¡TODAVÍA NO HAS AÑADIDO LIBROS A TU CARRITO!</th>
						</tr>
					</thead>
					<tr>
						<td class = "centrado"><BR><img src = "img/libros.png"> <BR> <BR>&#128214; Cuando lo hagas, aparecerán aquí.</td>
					</tr>
				</table>
			
			<%} %>
		</div><BR>
		<div class = "ajusteFooter"></div>
		<%@include file="Footer.jsp" %>
	</body>
</html>