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
<div class="container">
<h4>Garage: "${garage.nombre}"</h4>
<h4>Patente: ${auto.patente}</h4>
<h4>Nombre: ${cliente.nombre}</h4>


<form:form action="${pageContext.request.contextPath}/realizarReservaHora/${cliente.id}/${auto.id}/${garage.id}" method="GET" modelAttribute="ticket">

<h2>Horario Desde</h2>
<form:input type="time" name="horaDesde" path="horaDesde"/>
<h2>Horario Hasta</h2>
<form:input type="time" name="horaHasta" path="horaHasta"/>


<button type="submit">Reservar</button><br>
</form:form>

</div>
</body>
</html>