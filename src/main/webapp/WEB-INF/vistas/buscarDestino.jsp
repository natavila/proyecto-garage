<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
	<head>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
</head>
<body>
<div class="container mt-3">
		<table class="table table-hover">
		<h3> A QUE LOCALIDAD QUERES VIAJAR??</h3>
		<h3> Auto: ${auto.patente} </h3>
		<h3 class="mt-3">Buscar Localidad</h3>
		<main class="container">
		
		 <form:form  action="${pageContext.request.contextPath}/ElegirGaragesEst/${cliente.id}/${auto.id}"  method="GET" lass="row g-3">
		
		  <div class="col-md-6">			
		    <label for="palabraBuscada" class="form-label control-label"></label>
		   <input type="text" class="form-control"  id="palabraBuscada" name="palabraBuscada" placeholder=" Buscar Localidad"/>
		  </div>
		  <div class="col-12">
		  
		  
		    <button type="submit" class="btn btn-primary control-label mb-3">Buscar</button>
  		 </form:form>
		     
		     
		       
	 
	<a class="btn btn-primary" href="home" role="button">Volver</a>
		</div>
    <footer th:replace="layout/layout::footer" class="bg-dark"></footer>
</body>
</html>