<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<link rel="stylesheet" type="text/css"  href="styles.css" />
		<title>Registro</title>
		<script>
		
		function check_pass() {
		    if (document.getElementById('upass1').value ==
		            document.getElementById('upass2').value) {
		        document.getElementById('Enviar').disabled = false;
		    } else {
		        document.getElementById('Enviar').disabled = true;
		    }
		}
		
		</script>
	</head>
	<body>
		<!-- HEADER -->
		<%@include file="Header.jsp" %>
		<div class = "contenedor">
			<div class = "contenedorVertical">
				<H1 class = "centrado">NUEVO USUARIO</H1>
				<form action = "/Home/CrearUsuario" method = "GET">
					<div class = "contenedorVertical">
						<table class = "tablaEstilada">
							<thead>
								<tr>
									<th colspan="2">RELLENA TUS DATOS DE USUARIO</th>
								</tr>
							</thead>
							<tr>
								<td><label>Nombre</label></td>
								<td><input type = "text" name = "nombre" required><BR></td>
							</tr>
							<tr>
								<td><label>Apellido 1</label></td>
								<td><input type = "text" name = "ape1" required><BR></td>
							</tr>
							<tr>
								<td><label>Apellido 2</label></td>
								<td><input type = "text" name = "ape2" required><BR></td>
							</tr>
							<tr>
								<td><label>DNI</label></td>
								<td><input type = "text" name = "dni" required><BR></td>
							</tr>
							<tr>
								<td><label>Dirección</label></td>
								<td><input type = "text" name = "direccion" required><BR></td>
							</tr>
							<tr>
								<td><label>eMail</label></td>
								<td><input type = "email" name = "email" required><BR></td>
							</tr>
							<tr>
								<td><label>F.Nacimiento</label></td>
								<td><input type = "date" name = "fnac" required><BR></td>
							</tr>
							<tr>
								<td><label>Nombre Usuario</label></td>
								<td><input type = "text" name = "uname" required><BR></td>
							</tr>
							<tr>
								<td><label>Contraseña</label></td>
								<td><input type = "password" name = "upass1" id = "upass1" onkeyup = 'check_pass();' required></td>
							</tr>
							<tr>
								<td><label>Verificar Contraseña</label></td>
								<td><input type = "password" name = "upass2" id = "upass2" onkeyup = 'check_pass();' required><BR></td>
							</tr>
							<tr>
								<td><button type = "reset" value = "Limpiar" style = "width:100%">LIMPIAR</button><BR></td>
								<td><button type = "submit" value = "Enviar" id = "Enviar" style = "width:100%" disabled>REGISTRAR</button></td>
							</tr>
						</table><BR>
					</div>
				</form>
			</div>
		</div>
		<div class = "ajusteFooter"></div>
		<!-- FOOTER -->
		<%@include file="Footer.jsp" %>
	</body>
</html>