<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<jsp:useBean id="persona" scope="page" class="prueba.beans.Persona" />
	<jsp:setProperty name="persona" property="nombre" value="Juan" />
	<jsp:setProperty name="persona" property="apellido" value="Igartua" />
	Nombre:
	<jsp:getProperty name="persona" property="nombre" />
	<br /> Apellido:
	<jsp:getProperty name="persona" property="apellido" />

</body>
</html>