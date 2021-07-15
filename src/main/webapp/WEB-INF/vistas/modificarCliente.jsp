<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Modificar mis datos</title>
</head>
<body>
<%@ include file="header.jsp" %>
<div class="container">
<h1 style="margin-top: 20px; margin-bottom: 20px;">Modificar mis datos</h1>
<form:form  action="procesarModificarCliente" method="POST" modelAttribute="cliente" class="row g-3 border border-3 bg-light">
		<div class="col-dm-12">
			<form:input type="text" class="form-control" id="nombre" path="nombre" placeholder="Nombre"/>
			</div>
		  <div class="col-dm-12">
		    <form:input type="text" class="form-control" id="apellido" path="apellido" placeholder="Apellido"/>
		  </div>
		  <div class="col-dm-12">
		    <form:input type="text" class="form-control" id="localidad" path="localidad" placeholder="Localidad"/>
		  </div>
		  <div class="col-dm-12">
		    <form:input type="email" class="form-control" id="email" path="email" placeholder="usuario@ejemplo.com"/>
		  </div>
		   <br>
		  <div class="col-dm-12">
		    <button type="submit" class="btn btn-outline-dark mb-3">Modificar</button>		    
		  </div>
  		</form:form>
  		<a class="btn btn-dark mt-5" role="button" href="javascript:history.back()"> Volver</a>
  		</div>
</body>
</html>