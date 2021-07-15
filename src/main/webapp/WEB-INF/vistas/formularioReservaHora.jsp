<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous"/>
<title>Reservar</title>
</head>
<body>
<%@ include file="header.jsp" %>
<div class="container" style="margin-left: 30%; margin-top: 100px">
<form:form action="${pageContext.request.contextPath}/realizarReservaHora/${auto.id}/${garage.id}" method="POST" modelAttribute="ticket">
<div style="display:grid; grid-template-columns: 200px 185px 200px; grid-template-rows: auto;">
<div style="grid-column:1; grid-row: 1;">
<h2>Hora Desde</h2>
<form:input type="time" name="horaDesde" path="horaDesde"/>
</div>
<div style="grid-column:3; grid-row: 1;">
<h2>Hora Hasta</h2>
<form:input type="time" name="horaHasta" path="horaHasta"/><br>
</div>
</div>
<div class="d-grid gap-2 col-6" style="margin-top:50px;">
<button type="submit" class="btn btn-primary mt-3">Calcular</button>
</div>
</form:form>
<a class="btn btn-dark mt-5" role="button" href="javascript:history.back()"> Volver</a>
</div>
</body>
</html>