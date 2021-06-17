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
		    <button class="btn btn-dark" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasWithBothOptions" aria-controls="offcanvasWithBothOptions">Menu</button>
		<div class="offcanvas offcanvas-start" data-bs-scroll="true" tabindex="-1" id="offcanvasWithBothOptions" aria-labelledby="offcanvasWithBothOptionsLabel">
		  <div class="offcanvas-header text-light bg-dark">
		    <h5 class="offcanvas-title" id="offcanvasWithBothOptionsLabel">¡Bienvenido ${cliente.nombre}!</h5>
		    <button type="button" class="btn-close btn-light text-reset" data-bs-dismiss="offcanvas" aria-label="Close"></button>
		  </div>
		  <div class="offcanvas-body">
		    <p>Saldo actual: $${billetera.saldo}</p>
		    <p><a href="${pageContext.request.contextPath}/mostrarBilletera/${cliente.id}">Mi billetera</a></p>
		   	<p><a href="${pageContext.request.contextPath}/formularioSaldo/${cliente.id}">Ingresar dinero</a></p>
		    <p><a href="${pageContext.request.contextPath}/mostrarGarages/${cliente.id}/${cliente.nombre}">Hacer reserva</a></p>
		  	<p><a href="${pageContext.request.contextPath}/mostrarAutosClientes/${cliente.id}">Mis autos<a></p>
		  </div>
		  <div class="container offcanvas-footer">
    		<p>Configuracion</p>
  			</div>
		</div>
		    <div class="collapse navbar-collapse" id="navbarSupportedContent">
		      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
		        <li class="nav-item">
		          <a class="nav-link active" aria-current="page" href="#">Home</a>
		        </li>
		        <li class="nav-item">
		          <a class="nav-link" href="#">Garajes</a>
		        </li>
		        <li class="nav-item">
		          <a class="nav-link" href="#">Mi perfil</a>
		        </li>
		        <li class="nav-item dropdown">
		          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
		            Dropdown
		          </a>
		          <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
		            <li><a class="dropdown-item" href="#">Action</a></li>
		            <li><a class="dropdown-item" href="#">Another action</a></li>
		            <li><hr class="dropdown-divider"></li>
		            <li><a class="dropdown-item" href="#">Something else here</a></li>
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
		<c:if test="${empty billetera}">
		<div class="alert alert-warning alert-dismissible fade show" role="alert">
		  <strong>¿Todavia no tenes una billetera?</strong> Genera una <a href="${pageContext.request.contextPath}/registroBilletera/${cliente.id}">aqui</a>.
		  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
		</div>
		</c:if>
		<div style="text-align:center; margin-top:50px;">
		<h3>¡Bienvenido ${cliente.nombre}! </h3>
		<c:if test="${not empty billetera}">			
		<h5>Saldo disponible: $${billetera.saldo} </h5>	 				        	        
		</c:if>	
		</div>
		<div style="margin-top:50px; margin-bottom:50px; text-align:center;">
		<a class="btn btn-primary" href="${pageContext.request.contextPath}/mostrarGarages/${cliente.id}/${cliente.nombre}" role="button">Hacer reserva</a>
		<a class="btn btn-primary" href="${pageContext.request.contextPath}/mostrarAutosClientes/${cliente.id}" role="button">Mostrar Mis Autos<a>
		<a class="btn btn-primary" href="${pageContext.request.contextPath}/mostrarRegistroAuto/${cliente.id}/${cliente.nombre}" role="button">Agregar Auto</a>
		<a class="btn btn-primary" href="${pageContext.request.contextPath}/mostrarBilletera/${cliente.id}" role="button">Mi billetera</a>
		<a class="btn btn-primary" href="planes" role="button">Elegir Plan</a>
		</div>
		<h3 style="text-align:center; margin-bottom:50px;">Garages cercanos</h3>
		<div class="container" style="display:grid; grid-template-columns:500px 50px 600px; grid-template-rows:500px;">
		<div style="grid-column:1; grid-row:1;">
		<table class="table table-hover">
		
		<thead>
	    <tr>      		
                <th scope="col">Nombre</th>
                <th scope="col">Localidad</th>
                <th scope="col">Calle</th>
                <th scope="col">Numero</th>
                <th scope="col"></th>       
	    </tr>
	  </thead>
	  <tbody>
	  <c:forEach var="garage" 
	             items="${garages}"
	             varStatus="status">
	                <tr>
                    <td>${garage.nombre}</td>
                    <td>${garage.localidad}</td>
                    <td>${garage.calle}</td>
                    <td>${garage.numero}</td>
                    <td><a href="">Ver</a></td>
	                </tr>	                
	            </c:forEach>
	  </tbody>
	</table>
	</div>
	<div style="grid-column:3; grid-row:1;">
	<iframe src="https://www.google.com/maps/embed?pb=!1m14!1m12!1m3!1d13116.788103434623!2d-58.7946042!3d-34.725427749999994!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!5e0!3m2!1ses-419!2sar!4v1623952540791!5m2!1ses-419!2sar" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy"></iframe>
	</div>
	</div>
		
	</body>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
		<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
		<script src="js/bootstrap.min.js" type="text/javascript"></script>
</html>