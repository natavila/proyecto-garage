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
	<div class="container mt-3">
		<table class="table table-hover">
		<h1>Mis Autos</h1>
		<h4>Cantidad de Autos: ${cantidad}</h4>
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
	<a class="btn btn-primary" href="homeAdmin" role="button">Volver</a>
		</div>
</body>
</html>