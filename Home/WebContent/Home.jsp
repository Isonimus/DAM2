<%@ page language = "java" 
		 contentType = "text/html; charset=ISO-8859-1"
    	 pageEncoding = "ISO-8859-1"%>
    	 
<!DOCTYPE html>

<html>
	<head>
		<meta charset = "ISO-8859-1">
		<title>Portada: Top 10 libros m�s vendidos</title>
	</head>
	<!--  -->
	<body>
		<!--  AQU� IR� EL HEADER -->
		
		<!--  IMPORTS -->
		<%@ page
			import = "java.sql.ResultSet"
			import = "java.sql.SQLException"
		%>
		<table border = "2">
			<tr>
				<th>ISBN</th>
				<th>T�TULO</th>
				<th>CATEGOR�A</th>
				<th>EDITORIAL</th>
				<th>AUTORES</th>
				<th>U. VENDIDAS</th>
			</tr>
			
		<!--  SCRIPTLET -->
		<!--  GENERA UNA FILA DE LA TABLA POR CADA ELEMENTO
			  DEL RESULTSET PASADO COMO ATRIBUTO DE LA PETICI�N -->
		<%
			ResultSet rs = (ResultSet) request.getAttribute("lista"); 
		
			try {
			
				while(rs.next()) {
					
					String titulo = rs.getString(2);
					int isbn = rs.getInt(1);
					String categoria = rs.getString(3);
					String editorial = rs.getString(4);
					int unidades = rs.getInt(5);
		%>
					<tr>
						<td><%= isbn %></td>
						<td><%= titulo %></td>
						<td><%= categoria %></td>
						<td><%= editorial %></td>
						<td></td>
						<td><%= unidades %></td>
					</tr>
		<%
				}
				
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
		%>
		</table>
		<!--  AQU� IR� EL FOOTER -->
	</body>
</html>