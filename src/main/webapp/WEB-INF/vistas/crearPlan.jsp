<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Creacion de plan</title>
<!-- Bootstrap core CSS 
		    <link href="css/bootstrap.min.css" rel="stylesheet" >-->
<!-- Bootstrap theme
		    <link href="css/bootstrap-theme.min.css" rel="stylesheet"> -->
<link href="css/estilos.css" rel="stylesheet">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4"
	crossorigin="anonymous"></script>
</head>
<body>
	<main class="container">
		<!-- Clase de Bootstrap. Hace que los elementos no lleguen hasta el borde -->
		<form:form action="crearPlan" method="POST" modelAttribute="plan"
			class="row g-3">
			<h1 class="mt-3">Creacion de plan</h1>
			<div class="col-md-6">
				<label for="nombre" class="form-label control-label">Nombre</label>
				<form:input type="text" class="form-control" id="nombre"
					path="nombre" />
			</div>
			<div class="col-md-6">
				<label for="cantidadAutosPermitidos"
					class="form-label control-label">Cantidad de autos
					permitidos</label>
				<form:input type="number" class="form-control"
					id="cantidadAutosPermitidos" path="cantidadAutosPermitidos" />
			</div>
			<div class="col-12">
				<label for="cantidadHorasPermitidas"
					class="form-label control-label">Cantidad de horas
					permitidas</label> <input type="number" class="form-control"
					id="cantidadHorasPermitidas" name="cantidadHorasPermitidas" />
			</div>

			<button type="submit" class="btn btn-primary control-label mb-3">Crear</button>
			</div>
		</form:form>
		<%--Bloque que es visible si el elemento error no está vacío	--%>

		<c:if test="${not empty mensajeOk}">
			<div class="alert alert-success" role="alert">
				<h6>${mensajeOk}</h6>

			</div>
		</c:if>

		<c:if test="${not empty mensajeError}">
			<div class="alert alert-danger" role="alert">
				<h6>${mensajeError}</h6>

			</div>
		</c:if>
	</main>
</body>
</html>