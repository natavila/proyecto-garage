<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
	<h1>Monto pagado: ${estacionamiento.precioAPagar}</h1>
	<h1>AUTO INGRESADO</h1>
</body>
</html>