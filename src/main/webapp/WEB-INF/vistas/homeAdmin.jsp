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
		<%@ include file="headerAdmin.jsp" %>
		<div class="container row" >
		<div class="col-10">
		<h5>ADMIN: ${admin.nombre}</h5>
		</div>
		<div class ="col-2">
		<p><b>${fecha}</b></p>
		</div>
		
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
     		          
                    
                   
         
					<td> <a class="btn btn-success justify-content-md-end btn-sm"  href="${pageContext.request.contextPath}/AdministrarGarage/${garage.id}">Mostrar</a> </td>	
			
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



