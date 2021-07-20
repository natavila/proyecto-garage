<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<h1>Ticket: ${estacionamiento.id}</h1>
	<h1>Cliente: ${cliente.nombre} ${cliente.apellido}</h1>
	<h1>Garage seleccionado: ${garage.nombre}</h1>
	<h1>Direccion: ${garage.calle} ${garage.numero} ${garage.localidad}</h1>
	<h1><c:if test="${empty cliente.plan}">
					 ${estacionamiento.precioAPagar}
					</c:if>
					<c:if test="${not empty cliente.plan}">
						${cliente.plan.nombre}
					</c:if></h1>
	<h1>AUTO INGRESADO</h1>
</body>
</html>