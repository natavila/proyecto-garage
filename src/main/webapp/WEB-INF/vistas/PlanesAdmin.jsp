<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x"
	crossorigin="anonymous">
<title>Lista de planes</title>
</head>
<body>
<%@ include file="headerAdmin.jsp" %>
	<div class="container mt-3">
		<table class="table table-hover">
<h2>Lista de planes</h2>


			<thead>
				<tr>
					<th scope="col">Id</th>
					<th scope="col">Nombre</th>
					<th scope="col">Cantidad de autos</th>
					<th scope="col">Cantidad de horas</th>
					<th scope="col">Precio</th>


				</tr>
			</thead>
			<tbody>
				<c:forEach var="plan" items="${planes}" varStatus="status">
					<tr>
						<td><b>${plan.id}</b></td>

						<td>${plan.nombre}</td>
						<td>${plan.cantidadAutosPermitidos}</td>
						<td>${plan.cantidadHorasPermitidas}</td>
						<td>${plan.precio}</td>
						<br>
						</td>
					</tr>

			

				</c:forEach>

			</tbody>
		</table>
		<a class="btn btn-primary" href="home" role="button">Volver</a>

	
	<footer th:replace="layout/layout::footer" class="bg-dark"></footer>
</body>
</html>