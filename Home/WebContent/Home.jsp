<%@ page language = "java" 
		 contentType = "text/html; charset=ISO-8859-1"
    	 pageEncoding = "ISO-8859-1"%>
    	 
<!DOCTYPE html>
<html>
	<head>
		<meta charset = "ISO-8859-1">
		<title>Portada: Top 10 libros m�s vendidos</title>
		<link rel="stylesheet" type="text/css"  href="styles.css" />
	</head>
	<!--  -->
	<body>
		<!--  AQU� IR� EL HEADER -->
		
		<!--  IMPORTS -->
		<%@ page
			import = "java.sql.ResultSet"
			import = "java.sql.SQLException"
		%>
		
		<!--  LA FIESTA -->
		<H1><CENTER>TOP 10 VENTAS</CENTER></H1>
		<CENTER><table border = "2">
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
					String autores = rs.getString(6);
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
		</table></CENTER>
		<!--  AQU� IR� EL FOOTER -->
	</body>
</html>