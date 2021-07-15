<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
	<head>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
</head>
<body>
<%@ include file="headerAdmin.jsp" %>



<div class="container mt-3">
		<table class="table table-hover">
		<h1>Garages registrados</h1>
		
		<h5><a class="btn btn-success justify-content-md-end" href="formularioAgregarGarage">Agregar Garage</a></h5>
	  <thead>
	    <tr>
	       		
                <th scope="col">Nombre</th>
                <th scope="col">Localidad</th>
                <th scope="col">Calle</th>
                <th scope="col">Numero</th>
                <th scope="col"><a href="listaPorHora">Precio Hora $$</a></th>
                <th scope="col"><a href="listaPorEstadia">Precio Estadia $$</a></th>
                <th scope="col">Cupo</th>
                <th scope="col">Cantidad de autos</th>
                
	    </tr>
	  </thead>
	  <tbody>
	  <c:forEach var="garage" 
	             items="${garages}"
	             varStatus="status">
	                <tr>
	                    
                    <td>${garage.nombre}</td>
                    <td>${garage.localidad}</td>
                    <td>${garage.calle}</td>
                    <td>${garage.numero}</td>
                    <td>${garage.precioHora}</td>
                    <td>${garage.precioEstadia}</td>
                    <td>${garage.capacidad}</td>
                    <td>${garage.contador}</td>
                    <td><a class="btn btn-danger justify-content-md-end" href="${pageContext.request.contextPath}/lista/eliminar/${garage.id}" role="button">Eliminar Garage</a></td>
     
   					<td> <a class="btn btn-warning justify-content-md-end" href="${pageContext.request.contextPath}/modificarGarage/${garage.id}" role="button">Modificar datos</a></td>
					
					
					
	                </tr>
	                
	            </c:forEach>
	  </tbody>
	</table>
	<a class="btn btn-primary" href="javascript:history.back()" role="button">Volver</a>
		</div>
    <footer th:replace="layout/layout::footer" class="bg-dark"></footer>
</body>
</html>