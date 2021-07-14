<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
<title>Lista de Autos</title>
</head>
<body>
<%@ include file="header.jsp" %>
	<div class="container mt-3">
		<table class="table table-hover">
		<h1 style="text-align:center">Mis Autos</h1>
		<h4 style="text-align:center">Cantidad de Autos: ${cantidad}</h4>
		<a class="btn btn-primary mb-3" href="mostrarRegistroAuto" role="button">Agregar Auto</a><br>
	  <thead>
	    <tr>
	      <th scope="col">ID</th>
	      <th scope="col">Patente</th> 
	    </tr>
	  </thead>
	  <tbody>
	  <c:forEach var="auto" 
	             items="${auto}"
	             varStatus="status">
	                <tr>
	                    <td><b>${auto.id}</b></td>
	                    <td>${auto.patente}</td>
						<td><a href="${pageContext.request.contextPath}/mostrarAutosClientes/eliminar/${auto.id}/${cliente.id}">Eliminar Auto</a></td>
	                </tr>
	                
	            </c:forEach>
	  </tbody>
	</table>
	<a class="btn btn-primary" role="button" href="javascript:history.back()"> Volver</a>
		</div>
</body>
</html>