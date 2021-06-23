<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Confirmacion reserva por hora</title>
</head>
<body>
<%@ include file="header.jsp" %>
	<h1>Ticket: ${estacionamiento.id}</h1>
	<h1>Cliente: ${cliente.nombre} ${cliente.apellido}</h1>
	<h1>Garage seleccionado: ${garage.nombre}</h1>
	<h1>Direccion: ${garage.calle} ${garage.numero} ${garage.localidad}</h1>
	<h1>Monto pagado: ${estacionamiento.precioAPagar}</h1>
</body>
</html>