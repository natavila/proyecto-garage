<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Modificar Garage</title>
</head>
<body>
<%@ include file="headerAdmin.jsp" %>

<div class="container">
<h1>Modificar Garage</h1>
<form:form  action="${pageContext.request.contextPath}/procesarModificarGarage/${garage.id}" method="POST" modelAttribute="garage" class="row g-3 border border-3 bg-light">
		<div class="col-dm-12">
			<form:input type="text" class="form-control" id="nombre" path="nombre" placeholder="Nombre"/>
			</div>
		  <div class="col-dm-12">
		    <form:input type="text" class="form-control" id="calle" path="calle" placeholder="Calle"/>
		  </div>
		  <div class="col-dm-12">
		    <form:input type="number" class="form-control" id="numero" path="numero" placeholder="Numero"/>
		  </div>
		  <div class="col-dm-12">
		    <form:input type="text" class="form-control" id="localidad" path="localidad" placeholder="Localidad"/>
		  </div>
		  <div class="col-dm-12">
		    <form:input type="text" class="form-control" id="capacidad" path="capacidad" placeholder="Capacidad"/>
		  </div>
		  <div class="col-dm-12">
		    <form:input type="text" class="form-control" id="precioHora" path="precioHora" placeholder="Precio hora"/>
		  </div>
		   <div class="col-dm-12">
		    <form:input type="text" class="form-control" id="precioEstadia" path="precioEstadia" placeholder="Precio estadia"/>
		  </div>
		   <br>
		  <div class="col-dm-12">
		    <button type="submit" class="btn btn-outline-dark mb-3">Modificar</button>		    
		  </div>
  		</form:form>
  		<a class="btn btn-primary" role="button" href="javascript:history.back()"> Volver</a>
  		</div>
</body>
</html>