<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous"/>
<title>Billetera</title>
</head>
<body>
<%@ include file="header.jsp" %>
<div class="container">
<form:form action="${pageContext.request.contextPath}/realizarReservaEstadia/${cliente.id}/${auto.id}/${garage.id}" method="POST" modelAttribute="ticket">

<h2>Fecha Desde</h2>
<form:input type="date" name="fechaDesde" path="fechaDesde"/>
<h2>Fecha Hasta</h2>
<form:input type="date" name="fechaHasta" path="fechaHasta"/><br>

<button type="submit" class="btn btn-primary">Calcular</button>
</form:form>

</div>
</body>
</html>