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
		<title>Comprar</title>
	</head>
	<body>
		<!--  EXTRACCIÓN DE DATOS DE LA REQUEST -->
		<%	
			Float totalCompra = 0.0F;
			int totalArticulos = 0;
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
		<H1 class = "contenedor">FINALIZAR TU COMPRA</H1>
		<!-- LA BARRA DE BÚSQUEDA -->
		<%@include file="BarraBusqueda.jsp" %>
		<div class = "contenedor">
			<%
			if(request.getAttribute("infoCarrito") != null){
				%>
				<form action = "/Home/ConfirmarCompra" method = "POST">
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
									<th>SUBTOTAL</th>
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
									<td><%= cantidad * precio %> Eur.</td>
								</tr>
				<%				totalCompra += cantidad * precio;
								totalArticulos += cantidad;
								}
							}
						
						} catch (SQLException e) {
						
							e.printStackTrace();
						}
				%>
				</table>
				
				<div class = "contenedor">
					<table class = "tablaEstilada">
					
						<thead>
							<tr>
								<th>CLIENTE</th>
								<th>Nº ARTÍCULOS</th>
								<th>TOTAL EUR</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td class = "centrado"><b><%= session.getAttribute("userId") %></b></td>
								<td class = "centrado"><b><%= totalArticulos %></b></td>
								<td class = "centrado"><b><%= totalCompra %> Eur.</b></td>
							</tr>
							<tr>
								<td class = "centrado" colspan = "3"><button type = "submit" style = "width:100%">COMPRAR</button></td>
							</tr>
						</tbody>
					</table>
					</div>
				</form>
				
			<%}else{ %>
			
				<table class = "tablaEstilada">
					<thead>
						<tr>
							<th>¡TODAVÍA NO HAS AÑADIDO LIBROS A TU CARRITO!</th>
						</tr>
					</thead>
					<tr>
						<td class = "centrado"><BR><img src = "img/compra.png"> <BR><BR>&#128214; Cuando lo hagas, tu compra aparecerá aquí.</td>
					</tr>
				</table>
			
			<%} %>
		</div><BR>
		<%
			if(request.getAttribute("mensaje") != null){
				String color;
				if(request.getAttribute("mensaje").equals("Compra realizada correctamente.")){
					color = "color:var(--main-green)";
				}else{
					color = "color:red";
				}
		%>	
				<div class = "centrado" style = "<%= color %>"><%= request.getAttribute("mensaje") %></div>
		<%
			}
		%>
		<div class = "ajusteFooter"></div>
		<%@include file="Footer.jsp" %>
	</body>
</html>