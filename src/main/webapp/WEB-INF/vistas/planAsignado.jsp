<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Plan asignado</title>
</head>
<body>
	<%@ include file="header.jsp" %>
	<div class="container m-5">
		<h1>�Ya podes disfrutar de los beneficios de tu plan!</h1>
		<h3>Tu plan actual es <b>${cliente.plan.nombre}</b>.</h3>
		<h3>Podes registrar <b>${cliente.plan.cantidadAutosPermitidos}</b> autos y un total de <b>${cliente.plan.cantidadHorasPermitidas}</b> horas para usar.</h3>
		<a class="btn btn-dark mt-5" href="home" role="button">Volver</a>
	</div>
</body>
</html>