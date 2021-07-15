<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
<title>Ingresar dinero</title>
</head>
<body>
<%@ include file="header.jsp" %>
	<div class="container mt-5">
	<form:form action="procesarSaldo" method="POST" modelAttribute="billetera" style="text-align:center;">
		<h1 class="mb-5">Recargar billetera</h1>	
		<label>Ingrese el monto</label>
		<input type="number" name="monto" id="saldo"/>
		<div class="d-grid gap-2 col-3 mx-auto mt-3">
		<button type="submit" class="btn btn-primary">Ingresar</button>
		</div>
	</form:form>
	<a class="btn btn-dark mt-5" role="button" href="javascript:history.back()"> Volver</a>
	</div>
	<c:if test="${not empty error}">
			<div class="alert alert-danger" role="alert">
				<h6>${error}</h6>

			</div>
		</c:if>
		
</body>
</html>