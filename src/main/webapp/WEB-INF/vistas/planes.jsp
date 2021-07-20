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
<%@ include file="header.jsp" %>

	<div class="container mt-3">
		<h1>  Hola ${cliente.nombre}, ¿qué plan querés elegir?</h1>
		<h3>  Tu saldo es: $${billetera.saldo}</h3> <a href="formularioSaldo">Recargar billetera</a>
		<table class="table table-hover">



			<thead>
				<tr>
					<th scope="col">Nombre</th>
					<th scope="col">Cantidad de autos</th>
					<th scope="col">Cantidad de horas</th>
					<th scope="col">Precio</th>


				</tr>
			</thead>
			<tbody>
				<c:forEach var="plan" items="${planes}" varStatus="status">
					<tr>
						<td><b>${plan.nombre}</b></td>
						<td>${plan.cantidadAutosPermitidos}</td>
						<td>${plan.cantidadHorasPermitidas}</td>
						<td>$${plan.precio}</td>

						<td>
						<!-- Button trigger modal -->
						<c:if test ="${empty mensaje}">
						  <a class="btn btn-primary" href="${pageContext.request.contextPath}/asignarplan/${plan.id}" role="button">Pagar</a>
						</button>
						</c:if>
						</td>

					</tr>

			

				</c:forEach>

			</tbody>
		</table>
		<a class="btn btn-primary mb-5" role="button" href="javascript:history.back()"> Volver</a>

	</div>
		<c:if test="${not empty mensajeExito}">
			<div class="alert alert-success" role="alert">
				<h6>${mensajeExito}</h6>

			</div>
		</c:if>

		<c:if test="${not empty mensajeTienePlan}">
			<div class="alert alert-danger" role="alert">
				<h6>${mensajeTienePlan}</h6>

			</div>
		</c:if>
		
		<c:if test="${not empty mensaje}">
			<div class="alert alert-danger" role="alert">
				<h6>${mensaje} <a href="registroBilletera"> aquí.</a></h6>

			</div>
		</c:if>
		
		<c:if test="${not empty fondoInsuficiente}">
			<div class="alert alert-danger" role="alert">
				<h6>${fondoInsuficiente} <a href="formularioSaldo"> aquí.</a></h6>

			</div>
		</c:if>
	<footer th:replace="layout/layout::footer" class="bg-dark"></footer>
</body>
</html>