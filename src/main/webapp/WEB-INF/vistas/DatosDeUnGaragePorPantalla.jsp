<<<<<<< HEAD
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head th:replace="layout/layout::head">
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous"/>
</head>
<body>
<main class="container">    
<h1>Garge Seleccionado</h1>

<h5>Nombre: ${garage.nombre} </h5>
<h5>Localidad: ${garage.localidad }</h5>
<h5>Calle: ${garage.calle }</h5>
<h5>Altura: ${garage.numero }</h5>
<h5>Precio hora: ${garage.precioHora }</h5>
<h5>Precio estadia: ${garage.precioEstadia }</h5>
<h5>Precio mes: ${garage.precioMes }</h5>
<h5>Capacidad: ${garage.capacidad }</h5>

 <!-- Clase de Bootstrap. Hace que los elementos no lleguen hasta el borde -->
			<form:form  action="${pageContext.request.contextPath}/agregarAutoAGarage/${garage.id}" method="POST" modelAttribute="auto" class="row g-3">
			<h1 class="mt-3">Agregar Auto</h1>
				<div class="col-md-6">			
		    <label for="patente" class="form-label control-label">Patente</label>
		    <form:input type="text" class="form-control" id="patente" path="patente"/>
		 
		  </div>
		  
		  <div class="col-12">
		    <button type="submit" class="btn btn-primary control-label mb-3">Asignar</button>
		  </div>
  		</form:form>

</main>

</body>
=======
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head th:replace="layout/layout::head">
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous"/>
</head>
<body>
<main class="container">    
<h1>Garges Seleccionado</h1>

<h5>Nombre: ${garage.nombre} </h5>
<h5>Localidad: ${garage.localidad }</h5>
<h5>Calle: ${garage.calle }</h5>
<h5>Altura: ${garage.numero }</h5>
<h5>Precio hora: ${garage.precioHora }</h5>
<h5>Precio estadia: ${garage.precioEstadia }</h5>
<h5>Precio mes: ${garage.precioMes }</h5>
<h5>Capacidad: ${garage.capacidad }</h5>

 <!-- Clase de Bootstrap. Hace que los elementos no lleguen hasta el borde -->
			<form:form  action="${pageContext.request.contextPath}/agregarAutoAGarage/${garage.id}" method="POST" modelAttribute="auto" class="row g-3">
			<h1 class="mt-3">Agregar Auto</h1>
				<div class="col-md-6">			
		    <label for="patente" class="form-label control-label">Patente</label>
		    <form:input type="text" class="form-control" id="patente" path="patente"/>
		 
		  </div>
		  
		  <div class="col-12">
		    <button type="submit" class="btn btn-primary control-label mb-3">Asignar</button>
		  </div>
  		</form:form>

</main>

</body>
>>>>>>> b7f08d3865a369885f4ff345531403514a4216cb
</html>