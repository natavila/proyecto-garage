<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
<title>Historial En Garage</title>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		  <div class="container">
		  
		    <a class="navbar-brand" href="#">Garage</a>
		    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
		      <span class="navbar-toggler-icon"></span>
		    </button>
		    
		    <div class="offcanvas offcanvas-start" data-bs-scroll="true" tabindex="-1" id="offcanvasWithBothOptions" aria-labelledby="offcanvasWithBothOptionsLabel">
		  <div class="offcanvas-header text-light bg-dark">
		    <h5 class="offcanvas-title" id="offcanvasWithBothOptionsLabel">¡Bienvenido ${cliente.nombre}!</h5>
		    <button type="button" class="btn-close btn-light text-reset" data-bs-dismiss="offcanvas" aria-label="Close"></button>
		  </div>
		 
		  <div class="container offcanvas-footer">
    		
  			</div>
		</div>
		    <div class="collapse navbar-collapse" id="navbarSupportedContent">
		      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
		        <li class="nav-item">
		          <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/home">Home</a>
		        </li>
		       
		        <li class="nav-item">
		          <a class="nav-link" href="${pageContext.request.contextPath}/cerrarSesion">Cerrar Sesion</a>
		        </li>
		      </ul>
		      
		      
		    </div>
		  </div>
		</nav>
	<div class="container mt-3">
		<table class="table table-hover">
		<h1>Hostorial De Garage</h1>
	  <thead>
	    <tr>
	      
	      
	    </tr>
	  </thead>
	  <tbody>
	  <c:forEach var="auto" 
	             items="${auto}"
	             varStatus="status">
	                <tr>
	                    <td><b>${auto.id}</b></td>
	                   
	                    <td><b>${auto.patente}</b></td>
	                  
	                  
	              
	                </tr>
	                
	            </c:forEach>
	  </tbody>
	</table>
	<a class="btn btn-primary" href="javascript:history.back()" role="button">Volver</a>
		</div>
</body>
</html></html>