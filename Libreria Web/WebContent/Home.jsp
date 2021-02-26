<%@ page language = "java" 
		 contentType = "text/html; charset=ISO-8859-1"
    	 pageEncoding = "ISO-8859-1"%>
    	 
<!DOCTYPE html>
<html>
	<head>
		<meta charset = "ISO-8859-1">
		<title>Portada: Top 10 libros más vendidos</title>
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
			ResultSet rs = null;
			ResultSetMetaData metadatos = null;
			if(request.getAttribute("lista") != null){
				rs = (ResultSet) request.getAttribute("lista");
				metadatos = rs.getMetaData();			
			}
		%>		
		<!--  AQUÍ IRÁ EL HEADER -->
		<%@include file="Header.jsp" %>
		<!--  LA FIESTA -->
		<H1 class = "centrado">TOP 10 VENTAS</H1>
		<!-- LA BARRA DE BÚSQUEDA -->
		<%@include file="BarraBusqueda.jsp" %>
		<div class = "contenedor">
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
							int unidades = rs.getInt(6);
			%>
						<tr>
							<td style = "padding: 3px;" class = "centrado"><img src = <%="img/portadas/" + isbn + ".jpg" %> height = "100px"></td>
							<td><%= isbn %></td>
							<td><%= titulo %></td>
							<td><%= categoria %></td>
							<td><%= editorial %></td>
							<td><%= autores %></td>
							<td><%= unidades %></td>
						</tr>
			<%
						}
					
					} catch (SQLException e) {
					
						e.printStackTrace();
					}
			%>
			</table>
		</div><BR>
		<div class = "ajusteFooter"></div>
		<!--  AQUÍ IRÁ EL FOOTER -->
		<%@include file="Footer.jsp" %>
	</body>
</html>