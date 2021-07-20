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
<br>
<form:form  action="procesarModificarGarage" method="POST" modelAttribute="garage" class="row g-3 border border-3 bg-light">
		<div class="col-dm-12">
		<p><b>Nombre</b></p>
			<form:input type="text" class="form-control" id="nombre" path="nombre" placeholder="Nombre"/>
			</div>
		  <div class="col-dm-12">
		  <p><b>Calle</b></p>
		    <form:input type="text" class="form-control" id="calle" path="calle" placeholder="Calle"/>
		  </div>
		  <div class="col-dm-12">
		  <p><b>Localidad</b></p>
		    <form:input type="text" class="form-control" id="localidad" path="localidad" placeholder="Localidad"/>
		  </div>
		  <div class="col-dm-12">
		  <p><b>Capacidad</b></p>
		    <form:input type="text" class="form-control" id="capacidad" path="capacidad" placeholder="capacidad"/>
		  </div>
		  <div class="col-dm-12">
		   <p><b>Precio Hora $</b></p>
		    <form:input type="text" class="form-control" id="precioHora" path="precioHora" placeholder="precioHora"/>
		  </div>
		   <div class="col-dm-12">
		   <p><b>Precio Estadia $</b></p>
		    <form:input type="text" class="form-control" id="precioEstadia" path="precioEstadia" placeholder="precioEstadia"/>
		  </div>
		  <div class="col-dm-12">
		  <p><b>Latitud</b></p>
		    <form:input type="text" class="form-control" id="latitud" path="latitud" placeholder="latitud"/>
		  </div>
		  <div class="col-dm-12">
		   <p><b>Longitud</b></p>
		    <form:input type="text" class="form-control" id="longitud" path="longitud" placeholder="longitud"/>
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