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
	                    <td>${auto.id}</td>
	                    <td><b>${auto.patente}</b></td>
						<td><a class="btn btn-danger justify-content-md-end" href="${pageContext.request.contextPath}/mostrarAutosClientesAdmin/eliminar/${auto.id}/${cliente.id}">Eliminar Auto</a></td>
	                </tr>
	                
	            </c:forEach>
	  </tbody>
	</table>
	<c:if test="${empty auto}">
	<div class="alert alert-warning" role="alert">
  			${mensaje}
		</div>
	</c:if>
	<a class="btn btn-primary" role="button" href="javascript:history.back()"> Volver</a>
		</div>
</body>
</html>