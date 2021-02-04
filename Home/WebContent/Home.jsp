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
		
		<!--  LA FIESTA -->
		<H1><CENTER>TOP 10 VENTAS</CENTER></H1>
		
		<div id = "contenedor">
			<table border = "2">
				<tr>
			<%  	try{
				
						for (int i = 1; i <= metadatos.getColumnCount(); i++) { 
			%>
							<th><%=metadatos.getColumnLabel(i) %></th>
			<%			}
			%>
				</tr>
				
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
							<td><CENTER><%= isbn %></CENTER></td>
							<td><CENTER><%= titulo %></CENTER></td>
							<td><CENTER><%= categoria %></CENTER></td>
							<td><CENTER><%= editorial %></CENTER></td>
							<td><CENTER><%= autores %></CENTER></td>
							<td><CENTER><%= unidades %></CENTER></td>
						</tr>
			<%
						}
					
					} catch (SQLException e) {
					
						e.printStackTrace();
					}
			%>
			</table>
		</div>
		<!--  AQUÍ IRÁ EL FOOTER -->
	</body>
</html>