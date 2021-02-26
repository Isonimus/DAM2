<%@ page language = "java" 
		 contentType = "text/html; charset=ISO-8859-1"
    	 pageEncoding = "ISO-8859-1"%>
    	 
<!DOCTYPE html>
<html>
	<head>
		<meta charset = "ISO-8859-1">
		<title>Catálogo</title>
		<link rel="stylesheet" type="text/css"  href="styles.css" />
		<!--  IMPORTS -->
		<%@ page
			import = "java.sql.ResultSet"
			import="java.sql.ResultSetMetaData"
			import = "java.sql.SQLException"
		%>
	</head>
	<!--  -->
	<body>
		<!--  EXTRACCIÓN DE DATOS DE LA REQUEST -->
		<%	
			String encabezado = "CATÁLOGO";
			ResultSet rs = null;
			ResultSetMetaData metadatos = null;
			int filas = 0;
			
			if(request.getAttribute("catalogo") != null){
				rs = (ResultSet) request.getAttribute("catalogo");
				metadatos = rs.getMetaData();	
				if (rs != null){
				  rs.beforeFirst();
				  rs.last();
				  filas = rs.getRow();
				  rs.beforeFirst();
				}
			}
			
			if(request.getAttribute("encabezado") != null){
				encabezado = (String)request.getAttribute("encabezado");
			}
		%>		
		<!--  AQUÍ IRÁ EL HEADER -->
		<%@include file="Header.jsp" %>
		<!--  LA FIESTA -->
		<H1 class = "centrado"><%= encabezado %></H1>
		<!-- LA BARRA DE BÚSQUEDA -->
		<%@include file="BarraBusqueda.jsp" %>
		<div class = "contenedor">
			<%
			if(filas > 0){
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
								
				%>			<th>COMPRAR</th>
						</tr>
					</thead>
					
				<!--  SCRIPTLET -->
				<!--  GENERA UNA FILA DE LA TABLA POR CADA ELEMENTO
					  DEL RESULTSET PASADO COMO ATRIBUTO DE LA PETICIÓN -->
				<% 
							while(rs.next()) {
								
								String titulo = rs.getString(2);
								int isbn = rs.getInt(1);
								String categoria = rs.getString(3);
								String editorial = rs.getString(4);
								String autores = rs.getString(5);
								Float precio = rs.getFloat(6);
								int stock = rs.getInt(7);
								String visibilidad = "";
								if(carrito.get(String.valueOf(isbn)) != null && stock == carrito.get(String.valueOf(isbn))){
									visibilidad = "disabled";
								}
				%>
							<tr>
								<td style = "padding: 3px;" class = "centrado"><img src = <%="img/portadas/" + isbn + ".jpg" %> height = "100px"></td>
								<td><%= isbn %></td>
								<td><%= titulo %></td>
								<td><%= categoria %></td>
								<td><%= editorial %></td>
								<td><%= autores %></td>
								<td><%= precio %> Eur.</td>
								<td><%= stock %></td>
								<td><button type="submit" name = <%= isbn %> value = "add" style = "width: 100%" <%= visibilidad %>><img src="img/carrito.png" alt="Comprar"></button></td>
							</tr>
				<%
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
							<th>NO SE HAN ENCONTRADO RESULTADOS</th>
						</tr>
					</thead>
					<tr>
						<td class = "centrado">¡Ooops...!<BR><BR><img src = "img/404.png"><BR><BR>Comprueba los términos de tu búsqueda, o prueba con otra diferente.</td>
					</tr>
				</table>
			
			<%} %>
		</div><BR>
		<div class = "ajusteFooter"></div>
		<!--  AQUÍ IRÁ EL FOOTER -->
		<%@include file="Footer.jsp" %>
	</body>
</html>