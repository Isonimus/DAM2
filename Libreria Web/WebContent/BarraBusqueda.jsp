<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div id = "barraBusqueda">
		<form action = "/Home/Catalogo" method = "POST">
			<div class = "contenedor">
				&nbsp;
				<input type="text" name = "busqueda" placeholder="Buscar..." required></input>&nbsp;
				<button type="submit"><img src="img/buscar.png" alt="Buscar"></button>
			</div>
			<div>
				<label>
		            <input type="radio" name="tipoBusqueda" value="isbn" checked> ISBN
		        </label>
		        <label>
		            <input type="radio" name="tipoBusqueda" value="titulo"> Título
		        </label>
	        </div>
        </form>
	</div>
</body>
</html>