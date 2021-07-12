<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous"/>
<title>Mi billetera</title>
</head>
<body>
<%@ include file="header.jsp" %>
	<div class="container" style="text-align: center;">
		<h1 style="margin-top: 30px; margin-bottom: 30px;">Mi billetera</h1>
		<h3>${nombre} ${apellido}</h3>
		<h3>Saldo: $${saldo}</h3>
		<a class="btn btn-primary mt-3 justify-content-md-start" role="button" href="javascript:history.back()"> Volver</a>
		<a class="btn btn-primary mt-3" href="formularioSaldo" role="button">Ingresar dinero</a>
	 </div>
</body>
</html>