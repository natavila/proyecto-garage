<<<<<<< HEAD
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
<div class="container mt-3">
		<table class="table table-hover">
		
		<h1>HOLA:  ${nombre} </h1>
		<h3>Garages registrados</h3>  
	  <thead>
	    <tr>
	       		<th scope="col">Id</th>
                <th scope="col">Nombre</th>
                <th scope="col">Localidad</th>
                <th scope="col">Calle</th>
                <th scope="col">Numero</th>
                <th scope="col">Precio Hora $$</th>
                <th scope="col">Precio Estadia $$</th>
                <th scope="col">Precio Mes $$</th>
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
                    <a href="${pageContext.request.contextPath}/mostrarFormularioReservaHora/${garage.id}">Reservar</a>
                    </td>                
                    <td>${garage.precioEstadia}
                    <br>
                    <a href="${pageContext.request.contextPath}/mostrarFormularioReservaEstadia/${garage.id}">Reservar</a>
                    </td>                                    
                    <td>${garage.precioMes}
                    <br>
                    <a href="${pageContext.request.contextPath}/mostrarFormularioReservaSemana/${garage.id}">Reservar</a>
                    </td>
                    
                    
						
	                </tr>
	                
	            </c:forEach>
	  </tbody>
	</table>
	<a class="btn btn-primary" href="home" role="button">Volver</a>
		</div>
    <footer th:replace="layout/layout::footer" class="bg-dark"></footer>
</body>
=======
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
<div class="container mt-3">
		<table class="table table-hover">
		<h1>Garages registrados</h1>  
	  <thead>
	    <tr>
	       		<th scope="col">Id</th>
                <th scope="col">Nombre</th>
                <th scope="col">Localidad</th>
                <th scope="col">Calle</th>
                <th scope="col">Numero</th>
                <th scope="col">Precio Hora $$</th>
                <th scope="col">Precio Estadia $$</th>
                <th scope="col">Precio Mes $$</th>
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
                    <a href="${pageContext.request.contextPath}/mostrarFormularioReservaHora/${garage.id}">Reservar</a>
                    </td>                
                    <td>${garage.precioEstadia}
                    <br>
                    <a href="${pageContext.request.contextPath}/mostrarFormularioReservaEstadia/${garage.id}">Reservar</a>
                    </td>                                    
                    <td>${garage.precioMes}
                    <br>
                    <a href="${pageContext.request.contextPath}/mostrarFormularioReservaSemana/${garage.id}">Reservar</a>
                    </td>
                    
                    
						
	                </tr>
	                
	            </c:forEach>
	  </tbody>
	</table>
	<a class="btn btn-primary" href="home" role="button">Volver</a>
		</div>
    <footer th:replace="layout/layout::footer" class="bg-dark"></footer>
</body>
>>>>>>> b7f08d3865a369885f4ff345531403514a4216cb
</html>