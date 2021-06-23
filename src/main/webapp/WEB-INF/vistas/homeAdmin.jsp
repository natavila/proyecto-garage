<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<!-- Bootstrap core CSS -->
	    <!--<link href="css/bootstrap.min.css" rel="stylesheet" >-->
	    <!-- Bootstrap theme -->
	    <!--<link href="css/bootstrap-theme.min.css" rel="stylesheet">-->
	    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous"/>
	    <link href="css/estilos.css"/>
	    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>
	</head>
	<body>
		<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		  <div class="container">
		    <a class="navbar-brand" href="#">Navbar</a>
		    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
		      <span class="navbar-toggler-icon"></span>
		    </button>
		    <div class="collapse navbar-collapse" id="navbarSupportedContent">
		      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
		        <li class="nav-item">
		          <a class="nav-link active" aria-current="page" href="#">Home</a>
		        </li>
		        <li class="nav-item">
		          <a class="nav-link" href="lista">Garages</a>
		        </li>
		        <li class="nav-item">
		          <a class="nav-link" href="mostrarClientes">Clientes</a>
		        </li>
		        <li class="nav-item dropdown">
		          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
		            Administrar
		          </a>
		          <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
		            <li><a class="dropdown-item" href="mostrarRegistroLocalidad">Agregar Localidad</a></li>
		            <li><a class="dropdown-item" href="crearPlan">Crear Plan</a></li>
		            <li><hr class="dropdown-divider"></li>
		            <li><a class="dropdown-item" href="planesAdmin">Planes</a></li>
		          </ul>
		        </li>
		        <li class="nav-item">
		          <a class="nav-link" href="cerrarSesion">Cerrar Sesion</a>
		        </li>
		      </ul>
		      <form class="d-flex">
		        <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
		        <button class="btn btn-outline-success" type="submit">Search</button>
		      </form>
		      
		    </div>
		  </div>
		</nav>
		<div>
		<h5>ADMIN: ${admin.nombre}</h5>
		</div>
		
<h1>Garages</h1>
						<c:if test="${not empty notifNuevos && notifNuevos != 0}">
						<div class="alert alert-primary" role="alert">
  						 ¡Atencion! Clientes Nuevos: <a href="mostrarClientes" class="alert-link">( ${notifNuevos} )</a> (Click para ver Clientes).
						</div>
						</c:if> 
						
						<c:if test="${not empty ganancia}">
						<div class="alert alert-success " role="alert">
		 			 	<strong>Dinero Recaudado en el Dia: ${ganancia}</strong>
						</div>
						</c:if> 
						<c:if test="${not empty Lleno}">
						<div class="alert alert-danger alert-dismissible fade show" role="alert">
		 				 <strong>¡Atencion! Algun Garage SIN cocheras disponibles</strong>
						</div>
						</c:if>
						<c:if test="${not empty ConLugar}">
						<div class="alert alert-success alert-dismissible fade show" role="alert">
		 				 <strong>Servicio Sin Problemas</strong>
						</div>
						</c:if>
						<c:if test="${not empty alerta}">
						<div class="alert alert-warning alert-dismissible fade show" role="alert">
		 			 	<strong>¡Atencion!  Algun GARAGE con Pocos Lugares Disponibles</strong>
		 			 	 <button type="button" class="close" data-dismiss="alert" aria-label="Close">
    					<span aria-hidden="true">&times;</span></button>
						</div>
						</c:if> 
<div class="container col-12">
<div class="row">
<div class= "col-6">
<div class="container ">
		<table class="table table-hover">
	  <thead>
	    <tr>
	       		<th scope="col">Id</th>
                <th scope="col">Nombre</th>
                <th scope="col">Localidad</th>
                <th scope="col">Calle</th>
                <th scope="col">Numero</th>
                
                
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
     		          
                    
                   
         
					<td> <a href="${pageContext.request.contextPath}/AdministrarGarage/${garage.id}">Mostrar</a> </td>	
			
			</c:forEach>
	        </tbody>
	        </table>
	        </div>
	        </div> 
	               
	    <div class ="col-6">       
		<div class="container fluid">
		<table class="table table-hover">
	 		<thead>
	    			<tr>
	    			<th scope="col">Lugares Disponibles</th>
	    			
	    			</tr>
	    	</thead>
					<c:forEach var="ocup" 
								 items="${ocupacion}"
	            				 varStatus="status">
	              		<tr>
                    			<td id="oc"><b>${ocup}</b></td>
                    	
                    	<td>
						</td>
                    	
                    	</tr>
       				</c:forEach>	
	  		</tbody>
		</table>
		</tr>
		</div>
		</div>
</div>
</div>
		
						
		

	</body>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
		<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
		<script src="js/bootstrap.min.js" type="text/javascript"></script>
</html>



