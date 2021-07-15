<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Billetera</title>
</head>
<body>
<%@ include file="header.jsp" %>
	<div class="container" style="text-align: center;">
	<h1>Billetera generada exitosamente!</h1>
	<h3>Su saldo es: ${saldo}</h3>
	<a class="btn btn-primary mt-3" href="formularioSaldo" role="button">Ingresar dinero</a>
	<a href = "home">Volver</a>
	</div>
</body>
</html>