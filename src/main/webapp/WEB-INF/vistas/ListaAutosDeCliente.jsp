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
		<h3>¡Hola ${cliente.nombre}!, ¿con que auto vas a reservar?</h3>
	  <thead>
	    <tr>
	      <th scope="col">ID</th>
	      <th scope="col">Patente</th>	      
	    </tr>
	  </thead>
	  <tbody>
	  <c:forEach var="autosSinGarage" 
	             items="${autosSinGarage}"
	             varStatus="status">
	                <tr>
	                    <td><b>${autosSinGarage.id}</b></td>
	                    <td>${autosSinGarage.patente}</td>	              	                  	                
						<td><a href="${pageContext.request.contextPath}/ElegirGaragesEst/${autosSinGarage.id}" role="button"> Elegir </a></td>
											
	                </tr>	                
	            </c:forEach>
	  </tbody>
	</table>
	<c:if test="${empty autosSinGarage}">
		<div class="alert alert-warning" role="alert">
  			${mensaje}
  			<a class="btn btn-link" role="button" href="mostrarRegistroAuto"> Agregar auto</a>
		</div>
	</c:if>

	<br>
	<a class="btn btn-primary" role="button" href="javascript:history.back()"> Volver</a>
		</div>
</body>
</html>