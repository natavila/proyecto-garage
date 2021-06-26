<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
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
			<p>${file}</p>
			<div>${file}</div>
			<img src="/imagenes/${file}"/>
			<img src="<%=request.getContextPath()%>/src/main/webapp/imagenes/logo.jpg">
			<img src="/imagenes/logo.JPG"/>
			
</body>
</html>