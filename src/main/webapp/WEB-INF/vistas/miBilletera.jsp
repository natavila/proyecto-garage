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
	<div class="container">
		<h1>Mi billetera</h1>
		<h3>${nombre} ${apellido}</h3>
		<h3>Saldo: ${saldo}</h3>
		<a class="btn btn-primary" href="${pageContext.request.contextPath}/formularioSaldo/${cliente.id}" role="button">Ingresar dinero</a>
	 </div>
</body>
</html>