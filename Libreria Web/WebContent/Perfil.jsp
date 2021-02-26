<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<link rel="stylesheet" type="text/css"  href="styles.css" />
		<!--  IMPORTS -->
		<%@ page
			import = "java.sql.Date"
			import = "java.sql.ResultSet"
			import="java.sql.ResultSetMetaData"
			import = "java.sql.SQLException"
		%>
		<title>Perfil</title>
	</head>
	<body>
		<!-- DESGLOSAR LA INFO DEL USUARIO DESDE EL RESULTSET -->
		<%
		String dni = null;
		String nombre = null;
		String ape1 = null;
		String ape2 = null;
		String direccion = null;
		String email = null;
		Date fNac = null;
		String userName = null;
		
		ResultSet userHistory = null;
		ResultSetMetaData metadatos = null;
		
		ResultSet rs = (ResultSet)request.getAttribute("userData");
		
		if(request.getAttribute("userHistory") != null){
			userHistory = (ResultSet) request.getAttribute("userHistory");
			metadatos = userHistory.getMetaData();
		}
		
		while(rs.next()){
			dni = rs.getString(1);
			nombre = rs.getString(2);
			ape1 = rs.getString(3);
			ape2 = rs.getString(4);
			direccion = rs.getString(5);
			email = rs.getString(6);
			fNac = rs.getDate(7);
			userName = rs.getString(8);
		}
		
		%>
		<!-- HEADER -->
		<%@include file="Header.jsp" %>
		<div class = "contenedor">
			<div class = "contenedorVertical">
				<H1 class = "centrado">TU CUENTA</H1>
				<form action = "#" method = "POST">
					<div class = "contenedorVertical">
						<table class = "tablaEstilada">
							<thead>
								<tr>
									<th colspan="2">TUS DATOS DE USUARIO</th>
								</tr>
							</thead>
							<tr>
								<td><label>Nombre</label></td>
								<td><input type = "text" name = "nombre" value = "<%= nombre %>" required><BR></td>
							</tr>
							<tr>
								<td><label>Apellido 1</label></td>
								<td><input type = "text" name = "ape1" value = "<%= ape1 %>" required><BR></td>
							</tr>
							<tr>
								<td><label>Apellido 2</label></td>
								<td><input type = "text" name = "ape2" value = "<%= ape2 %>" required><BR></td>
							</tr>
							<tr>
								<td><label>DNI</label></td>
								<td><input type = "text" name = "dni" value = "<%= dni %>" required><BR></td>
							</tr>
							<tr>
								<td><label>Dirección</label></td>
								<td><input type = "text" name = "direccion" value = "<%= direccion %>" required><BR></td>
							</tr>
							<tr>
								<td><label>eMail</label></td>
								<td><input type = "email" name = "email" value = "<%= email %>" required><BR></td>
							</tr>
							<tr>
								<td><label>F.Nacimiento</label></td>
								<td><input type = "date" name = "fnac" value = "<%= fNac %>" required><BR></td>
							</tr>
							<tr>
								<td><label>Nombre Usuario</label></td>
								<td><input type = "text" name = "uname" value = "<%= userName %>" required><BR></td>
							</tr>
							<tr>
								<td colspan = "2"><button type = "submit" value = "Guardar" style = "width:100%">GUARDAR</button></td>
							</tr>
						</table><BR>
					</div>
				</form>
			</div>
		</div>
		<div class = "contenedor">
			<div class = "contenedorVertical">
				<H1 class = "centrado">TU HISTORIAL DE COMPRAS</H1>
					<div class = "contenedorVertical">
					<table class = "tablaEstilada">
						<thead>
							<tr>
								<%  		
								try{
				
									for (int i = 1; i <= metadatos.getColumnCount(); i++) { 
								%>
										<th><%=metadatos.getColumnLabel(i) %></th>
								<%	}
								%>
								
							</tr>
						</thead>
						<!--  SCRIPTLET -->
			<!--  GENERA UNA FILA DE LA TABLA POR CADA ELEMENTO
				  DEL RESULTSET PASADO COMO ATRIBUTO DE LA PETICIÓN -->
								<% 
									while(userHistory.next()) {
										
										int referencia = userHistory.getInt(1);
										Date fecha = userHistory.getDate(2);
										int numArticulos = userHistory.getInt(3);
										String articulos = userHistory.getString(4);
								%>
									
									<tr>
										<td>Ref#: <%= referencia %></td>
										<td><%= fecha %></td>
										<td><%= numArticulos %></td>
										<td><%= articulos %></td>
									</tr>
									
								<%
									}
								
								} catch (SQLException e) {
								
									e.printStackTrace();
								}
								%>
					</table>
				</div>
			</div>
		</div>
		<div class = "ajusteFooter"></div>
		<!-- FOOTER -->
		<%@include file="Footer.jsp" %>
	</body>
</html>