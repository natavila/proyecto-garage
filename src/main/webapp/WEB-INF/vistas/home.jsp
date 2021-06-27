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
		<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.25/css/jquery.dataTables.css"> 
		
		
	</head>
	<body>
		<%@ include file="header.jsp" %>
		<c:if test="${empty billetera}">
		<div class="alert alert-warning alert-dismissible fade show" role="alert">
		  <strong>¿Todavia no tenes una billetera?</strong> Genera una <a href="${pageContext.request.contextPath}/registroBilletera/${cliente.id}">aqui</a>.
		  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
		</div>
		</c:if>
		<div style="text-align:center; margin-top:50px;">
		<h3>¡Bienvenido/a ${cliente.nombre}! </h3>
		<c:if test="${not empty billetera}">			
		<h5>Saldo disponible: $${billetera.saldo} </h5>	 				        	        
		</c:if>	
		</div>
		<div style="margin-top:50px; margin-bottom:50px; text-align:center;">
		
		</div>
		<div class="container" style="display:grid; grid-template-columns:200px 20px 200px 20px 200x; grid-template-rows: 250px; margin-top:50px;">
		<div class="card" style="width: 18rem; grid-column:1; grid-row:1;">
		  <img src="..." class="card-img-top" alt="...">
		  <div class="card-body">
		    <h5 class="card-title">Plan plata</h5>
		    <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
		    <a href="#" class="btn btn-primary">Suscribirme</a>
		  </div>
		</div>
		<div class="card" style="width: 18rem; grid-column:3; grid-row:1;">
		  <img src="..." class="card-img-top" alt="...">
		  <div class="card-body">
		    <h5 class="card-title">Plan oro</h5>
		    <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
		    <a href="#" class="btn btn-primary">Suscribirme</a>
		  </div>
		</div>
		<div class="card" style="width: 18rem; grid-column:5; grid-row:1;">
		  <img src="..." class="card-img-top" alt="...">
		  <div class="card-body">
		    <h5 class="card-title">Plan platino</h5>
		    <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
		    <a href="#" class="btn btn-primary">Suscribirme</a>
		  </div>
		</div>
		</div>

		
		
		

		<div style="margin-top:50px; margin-bottom:50px; text-align:center;">
		<a class="btn btn-primary" href="${pageContext.request.contextPath}/mostrarGarages/${cliente.id}/${cliente.nombre}" role="button">Hacer reserva</a>
		<a class="btn btn-primary" href="${pageContext.request.contextPath}/misAutos/${cliente.id}" role="button">Mis Autos<a>
		<a class="btn btn-primary" href="${pageContext.request.contextPath}/mostrarBilletera/${cliente.id}" role="button">Mi billetera</a>
		<a class="btn btn-primary" href="planes/${cliente.id}" role="button">Elegir Plan</a>
		</div>
		<h3 style="text-align:center; margin-bottom:50px;">Garages</h3>
		<h6>Filtrar garage por:</h6>
		<select name="localidades">
		<option value="value1">San Justo</option>
  		<option value="value2" selected>Merlo</option>
  		<option value="value3">Moron</option>
		</select>
		<div class="container" style="display:grid; grid-template-columns:500px 50px 600px; grid-template-rows:500px;">
		<h3 style="text-align:center; margin-bottom:50px;">Garages cercanos a tu zona</h3>

		<div class="container" style="display:grid; grid-template-columns:650px 50px 400px; grid-template-rows:500px;">

		<div style="grid-column:1; grid-row:1;">
		<table class="table table-hover" id="datatable">
		
		<thead>
	    <tr>      		
                <th scope="col">Nombre</th>
                <th scope="col">Localidad</th>
                <th scope="col">Calle</th>
                <th scope="col">Numero</th>
                <th scope="col">Hora</th>
                <th scope="col">Estadia</th>      
	    </tr>
	  </thead>
	  <tbody>
	  
	  <c:forEach var="garagesCercanos"
	             items="${garagesCercanos}"
	             varStatus="status">
	                <tr>
	                
                    <td>${garagesCercanos.nombre}</td>
                    <td>${garagesCercanos.localidad}</td>
                    <td>${garagesCercanos.calle}</td>
                    <td>${garagesCercanos.numero}</td>
                    <td>$${garagesCercanos.precioHora}</td>
                    <td>$${garagesCercanos.precioEstadia}</td>
                  
	                </tr>	                
	            </c:forEach>
	       
	  </tbody>
	</table>
	<div class="d-grid gap-2 col-6 mx-auto mt-3">
	<a class="btn btn-primary" href="${pageContext.request.contextPath}/mostrarGarages/${cliente.id}/${cliente.nombre}" role="button">Hacer reserva</a>
	</div>
	</div>
	<div style="grid-column:3; grid-row:1;">
		<iframe src="https://www.google.com/maps/embed?pb=!1m14!1m12!1m3!1d13116.788103434623!2d-58.7946042!3d-34.725427749999994!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!5e0!3m2!1ses-419!2sar!4v1624460773831!5m2!1ses-419!2sar" width="400" height="300" style="border:0;" allowfullscreen="" loading="lazy"></iframe>	</div>
	</div>
		
	</body>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
		<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
		<script src="js/bootstrap.min.js" type="text/javascript"></script>
		<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.25/js/jquery.dataTables.js"></script>
		<script type="text/javascript">
		$(document).ready( function () {
		    $('#datatable').DataTable({
		        language: {
		            search: "Buscar:",
		            zeroRecords: "No se encontró resultados"
		        }
		    } );
		    } );</script>
</html>