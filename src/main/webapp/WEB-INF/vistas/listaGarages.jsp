<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
	<head>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.25/css/jquery.dataTables.css"> 
</head>
<body>
<%@ include file="header.jsp" %>
<div class="container mt-3">
		<table class="table table-hover" id="datatable">
		<h1>Eliga un garage</h1>
		<h4>Cliente: ${cliente.nombre} </h4>
		<h4>Patente del auto: ${auto.patente} </h4>
		<h4>Garages registrados</h4>  


	  <thead>
	    <tr>
	       		<th scope="col">Id</th>
                <th scope="col">Nombre</th>
                <th scope="col">Localidad</th>
                <th scope="col">Calle</th>
                <th scope="col">Numero</th>
                <th scope="col">Precio Hora $$</th>
                <th scope="col">Precio Estadia $$</th>
        
	    </tr>
	  </thead>
	  <tbody>
	  <c:forEach var="garage" 
	             items="${garages}"
	             varStatus="status">
	                <tr>
	                <td><b>${garage.id}</b></td>
                    <td>${garage.nombre}</td>
                    <td>${garage.localidad}</td>
                    <td>${garage.calle}</td>
                    <td>${garage.numero}</td>
                    <td>${garage.precioHora}
                    <br>
                    <a href="${pageContext.request.contextPath}/mostrarFormularioReservaHora/${auto.id}/${garage.id}">Reservar</a>
                    </td>                
                    <td>${garage.precioEstadia}
                    <br>
                    <a href="${pageContext.request.contextPath}/mostrarFormularioReservaEstadia/${auto.id}/${garage.id}">Reservar</a>
                    </td>                                    
	                </tr>	                
	            </c:forEach>
	  </tbody>
	</table>
	<a class="btn btn-primary" href="home" role="button">Volver</a>
		</div>
    <footer th:replace="layout/layout::footer" class="bg-dark"></footer>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
		<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
		<script src="js/bootstrap.min.js" type="text/javascript"></script>
		<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.25/js/jquery.dataTables.js"></script>
		<script type="text/javascript">
		$(document).ready( function () {
		    $('#datatable').DataTable({
		        language: {
		        	"url": "//cdn.datatables.net/plug-ins/1.10.16/i18n/Spanish.json"
		        }
		    } );
		    } );</script>
</html>