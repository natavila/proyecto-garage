<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
<title>Lista de clientes</title>
</head>
<%@ include file="headerAdmin.jsp" %>
<body>
	<div class="container mt-3">
		<table class="table table-hover">
		<h1>Clientes registrados</h1>
	  <thead>
	    <tr>
	      <th scope="col">ID</th>
	      <th scope="col">Nombre</th>
	      <th scope="col">Apellido</th>
	      <th scope="col">Email</th>
	       <th scope="col"></th>
	    </tr>
	  </thead>
	  <tbody>
	  <c:forEach var="cliente" 
	             items="${clientes}"
	             varStatus="status">
	                <tr>
	                    <td><b>${cliente.id}</b></td>
	                    <td>${cliente.nombre}</td>
	                    <td>${cliente.apellido}</td>
	                    <td>${cliente.email}</td>
	                    <td><a class="btn btn-success justify-content-md-end" href="${pageContext.request.contextPath}/misAutosAdmin/${cliente.id}">Mostrar Autos</a></td>
						
						<td><a class="btn btn-danger justify-content-md-end" href="${pageContext.request.contextPath}/mostrarClientes/eliminar/${cliente.id}">Eliminar Cliente</a></td>
	                </tr>
	                
	            </c:forEach>
	  </tbody>
	</table>
	<a class="btn btn-primary" href="javascript:history.back()" role="button">Volver</a>
		</div>
</body>
</html>