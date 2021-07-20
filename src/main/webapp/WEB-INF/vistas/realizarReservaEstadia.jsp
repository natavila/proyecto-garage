<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous"/>
<title>Monto a Pagar</title>
</head>
<body>
<%@ include file="header.jsp" %>
	<div class="container">
	<h1>El precio es: </h1>
		<h3>$${precio}</h3><br>
		<h1>Dias del auto en el garaje:</h1>
		<h3>${dias}</h3>
		<a class="btn btn-primary" href="${pageContext.request.contextPath}/pagarReservaEstadia/${auto.id}/${garage.id}" role="button">Pagar</a><br>
		<br>
		<a class="btn btn-dark mt-5" href="javascript:history.back()"> Volver</a>
		</div>
		
</body>
</html>