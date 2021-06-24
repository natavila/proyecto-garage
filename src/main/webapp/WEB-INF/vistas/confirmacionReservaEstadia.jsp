<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
<title>Confirmacion reserva estadia</title>
</head>
<body>
<%@ include file="header.jsp" %>
	<div class="container">
		<table class="table">
			<thead>
				<tr>
					<th scope="col"> N° de ticket </th>
					<th scope="col"> Cliente </th>
					<th scope="col"> Garage </th>
					<th scope="col"> Direccion </th>
					<th scope="col"> Monto pagado </th>
					<th scope="col"> Dias de reserva </th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td> ${estacionamiento.id} </td>
					<td> ${cliente.nombre} ${cliente.apellido} </td>
					<td> ${garage.nombre} </td>
					<td> ${garage.calle} ${garage.numero} ${garage.localidad} </td>
					<td> ${estacionamiento.precioAPagar} </td>
					<td> Desde: ${estacionamiento.fechaDesde} <br> 
					Hasta: ${estacionamiento.fechaHasta}</td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>