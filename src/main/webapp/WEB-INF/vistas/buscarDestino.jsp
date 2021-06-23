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
		<%@ include file="header.jsp" %>
<main class="container" style="margin-top:50px; margin-left:30%;">
		<h1> A que localidad queres viajar?</h1>
		<h5> Patente: ${auto.patente} </h5>
		<h5 class="mt-3">Buscar Localidad</h5>	
		 <form:form  action="${pageContext.request.contextPath}/ElegirGaragesEst/${cliente.id}/${auto.id}"  method="GET" lass="row g-3">	
		  <div class="col-md-6">			
		    <label for="palabraBuscada" class="form-label control-label"></label>
		   <input type="text" class="form-control"  id="palabraBuscada" name="palabraBuscada" placeholder=" Buscar Localidad"/>
		  </div>
		  <div class="col-12 mt-3">
		  	<a class="btn btn-primary" href="home" role="button">Volver</a>	  
		    <button type="submit" class="btn btn-primary control-label" style="margin-left:410px;">Buscar</button>	
			</div>
  		 </form:form>	
	</main>	
    <footer th:replace="layout/layout::footer" class="bg-dark"></footer>
</body>
</html>