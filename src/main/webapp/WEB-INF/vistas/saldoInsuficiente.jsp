<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Saldo insuficiente</title>
</head>
<body>
	<h1>Saldo insuficiente</h1>
	<h4>Usted no posee saldo suficiente para realizar la reserva.</h4>
	<h4>Por favor recarge su billetera.</h4>
	<a class="btn btn-primary" href="${pageContext.request.contextPath}/formularioSaldo/${cliente.id}" role="button">Recargar</a>
</body>
</html>