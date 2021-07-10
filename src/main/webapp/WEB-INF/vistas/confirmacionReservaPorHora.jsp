<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Confirmacion reserva por hora</title>
</head>
<body>
<%@ include file="header.jsp" %>

	<div class="container" style="margin-top:50px;">
	<h1 style="text-align:center;">Reserva exitosa!</h1>
		<table class="table" style="margin-top:50px;">
			<thead>
				<tr>
					<th scope="col"> N° de ticket </th>
					<th scope="col"> Cliente </th>
					<th scope="col"> Garage </th>
					<th scope="col"> Direccion </th>
					<th scope="col"> Monto pagado </th>
					<th scope="col"> Horas de reserva </th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td> ${estacionamiento.id} </td>
					<td> ${cliente.nombre} ${cliente.apellido} </td>
					<td> ${garage.nombre} </td>
					<td> ${garage.calle} ${garage.numero} ${garage.localidad} </td>
					<td> $${estacionamiento.precioAPagar} </td>
					<td> Desde: ${estacionamiento.horaDesde} hs. <br> 
					Hasta: ${estacionamiento.horaHasta} hs. </td>
				</tr>
			</tbody>
		</table>
		
		<a class="btn btn-primary" href="${pageContext.request.contextPath}/ticketsCliente/${cliente.id}" role="button">Imprimir Mi Ticket<a>
	</div>
	<a class="btn btn-primary" role="button" href="javascript:history.back()"> Volver</a>

</body>
</html>