<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Insert title here</title>
		<!--  IMPORTS -->
		<%@ page
			import = "java.util.Enumeration"
			import = "java.util.Hashtable"
			import="java.sql.ResultSetMetaData"
			import = "java.sql.SQLException"
		%>
		<!-- PREPARACIÓN DE LA SESIÓN -->
		<%! @SuppressWarnings("unchecked") %>
		<%
			if(session.isNew()){
				
				session.setAttribute("carrito", new Hashtable<String, Integer>());
				session.setAttribute("logged", false);
				session.setAttribute("nombre", "invitado");
				session.setAttribute("userId", "00000000X");
				
			}
		
			Hashtable<String, Integer> carrito = (Hashtable<String, Integer>)session.getAttribute("carrito");
			int cantidadProductos = 0;
			if(carrito != null){
				for (int cantidad : carrito.values()) {
					cantidadProductos += cantidad;
				}
			}
			
		%>
	</head>
	<body>
		<div id = "header">
			<div class = "centrado">
				<a href="/Home/Homepage"><img src = "img/logo.png"></a>
			</div>
			
			<form action = "Homepage" method = "POST">
				<div class = "contenedor">
					<button type = "submit" value = "HOME" name = "HOME" ${param["HOME"] == 'HOME' ? 'disabled':''} >HOME</button>
					<button type = "submit" value = "CATALOGO" name = "CATALOGO" ${param["CATALOGO"] == 'CATALOGO' ? 'disabled':''}>CATÁLOGO</button>
					<button type = "submit" value = "CARRITO" name = "CARRITO" ${param["CARRITO"] == 'CARRITO' ? 'disabled':''}>CARRITO</button>
					<button type = "submit" value = "COMPRAR" name = "COMPRAR" ${param["COMPRAR"] == 'COMPRAR' ? 'disabled':''}>COMPRAR</button>
					<%
					if(!(boolean)session.getAttribute("logged")){
					%>
						<button type = "submit" value = "LOG-IN" name = "LOG-IN" ${param["LOG-IN"] == 'LOG-IN' ? 'disabled':''}>LOG-IN</button>
						<button type = "submit" value = "REGISTRO" name = "REGISTRO" ${param["REGISTRO"] == 'REGISTRO' ? 'disabled':''}>REGISTRO</button>
					<% 
					}else{
					%>
						<button type = "submit" value = "LOGOUT" name = "LOGOUT" ${param["LOGOUT"] == 'LOGOUT' ? 'disabled':''}>LOGOUT</button>
						<button type = "submit" value = "PERFIL" name = "PERFIL" ${param["PERFIL"] == 'PERFIL' ? 'disabled':''}>PERFIL</button>
					
					<%
					}
					%>
				</div>
				<div id="saludo" class = "contenedorVertical">
					<%
					String linkNombre;
					String linkCarro;
					String valueNombre;
					String valueCarrito;
					String nombreUsuario = (String)session.getAttribute("nombre");
					if((boolean)session.getAttribute("logged") == false){
						
						valueNombre = "LOG-IN";
						valueCarrito = "CARRITO";
						
					}else{
						
						valueNombre = "PERFIL";
						valueCarrito = "CARRITO";
					}
					%>
					<button type = "submit" value = "<%= valueNombre %>" name = "<%= valueNombre %>" style = "width:100%">¡Hola, <%= session.getAttribute("nombre") %>! &#128104;</button><BR>
					<button type = "submit" value = "<%= valueCarrito %>" name = "<%= valueCarrito %>" style = "width:100%">Carrito (<%= cantidadProductos %> &#128214; )</button>
				</div>
			</form>
			
		</div>
	</body>
</html>