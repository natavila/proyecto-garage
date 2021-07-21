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
	     <script type="text/javascript" src="https://maps.google.com/maps/api/js"></script>


<script src="https://maps.google.com/maps/api/js?sensor=false"></script>
 <script src="http://maps.googleapis.com/maps/api/js"> </script>
<script src="https://maps.google.com/maps/api/js?sensor=false"></script>
<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false"></script>
<script src = "http://maps.googleapis.com/maps/api/js?key=AIzaSyAiq3xISXSZYgkd9GDAOdajy4NK2d3L7dY"></script>


</head>
<body onload="mapa.initMap()">
	</head>
	<body>
	<%@ include file="headerAdmin.jsp" %>
			
		<div style="text-align:center; margin-top:50px;">
		<c:if test="${not empty alerta}">
		<div class="alert alert-warning alert-dismissible fade show" role="alert">
		  <strong>¡Atencion! Pocos Lugares Disponibles</strong>
		</div>
		</c:if>
		</div>
		<div style="text-align:center; margin-top:50px;">
		<c:if test="${not empty ConLugar}">
		<div class="alert alert-success alert-dismissible fade show" role="alert">
		  <strong>¡Servicio Sin problemas!</strong>
		</div>
		</c:if>
		</div>
		<div style="text-align:center; margin-top:50px;">
		<c:if test="${not empty Lleno}">
		<div class="alert alert-danger alert-dismissible fade show" role="alert">
		  <strong>¡Atencion! Garage SIN cocheras disponibles</strong>
		</div>
		</c:if>
		</div>
		<div style="text-align:center; margin-top:50px;">
		<c:if test="${not empty dinero}">
		<div class="alert alert-warning alert-dismissible fade show" role="alert">
		  <strong>Recaudacion del dia:$ ${dinero} </strong>
		</div>
		</c:if>
		</div>
		<h3 style="text-align:center; margin-bottom:50px;">${garage.nombre}</h3>
		
		<div class = "row">
		<div class="col-12">
		
		<table class="table table-hover">
		
		<thead>
	    <tr>      		
                
                <th scope="col">Localidad</th>
                <th scope="col">Calle</th>
                <th scope="col">Numero</th>
                <th scope="col">Cupo</th>
                <th scope="col">Lugares Disponibles</th>       
	    </tr>
	  </thead>
	  <tbody>
	 
	                <tr>
                    
                    <td>${garage.localidad}</td>
                    <td>${garage.calle}</td>
                    <td>${garage.numero}</td>
                    <td>${garage.capacidad}</td>
                    <td>${lugar}</td>
	                </tr>	         
	                  
	                       
	  </tbody>
	</table>
	</div>
	<div class ="col-12">
	<h1>Autos En Garage</h1>
	</div>
	<div class="col-3" >
	
		<table class="table table-hover">
		
	  <thead>
	    <tr>
	       <th scope="col">Patente</th>
	       
	    </tr>
	  </thead>
	  <tbody>
	  <c:forEach var="auto" 
	             items="${autos}"
	             varStatus="status">
	                <tr>
	                    <td><b>${auto.patente}</b></td>
	                </tr>
	            </c:forEach>
	  </tbody>
	</table>
	
	
	<a class="btn btn-danger" href="${pageContext.request.contextPath}/sacarAuto/${garage.id}" role="button">Sacar Auto</a>
	<a class="btn btn-success" href="${pageContext.request.contextPath}/mostrarHistoricoDeUnGarage/${garage.id}">Historial</a> 
</div>
<div class="col-3" >
	
		<table class="table table-hover">
		
	  <thead>
	    <tr>
	       <th scope="col">N° Ticket</th>
	       
	    </tr>
	  </thead>
	  <tbody>
	  <c:forEach var="tickets" 
	             items="${tickets}"
	             varStatus="status">
	                <tr>
	                    <td><b>${tickets}</b></td>
	                </tr>
	            </c:forEach>
	  </tbody>
	</table>
	
</div>

	<div class="col-3">
	<h5>Ubicacion</h5>
	<div id="showMap" style="width: 450px; height: 350px;"> </div>
	<script type="text/javascript"> 
function showGoogleMaps()
{
    //Creamos el punto a partir de la latitud y longitud de una dirección:
    var point = new google.maps.LatLng(${garage.latitud}, ${garage.longitud});
 
    //Configuramos las opciones indicando zoom, punto y tipo de mapa
    var myOptions = {
        zoom: 15, 
        center: point, 
        mapTypeId: google.maps.MapTypeId.ROADMAP
    };
 
    //Creamos el mapa y lo asociamos a nuestro contenedor
    var map = new google.maps.Map(document.getElementById("showMap"),  myOptions);
 
    //Mostramos el marcador en el punto que hemos creado
    var marker = new google.maps.Marker({
        position:point,
        map: map,
        title: "${garage.nombre} - ${garage.calle} ${garage.numero}, ${garage.localidad}",
        animation:google.maps.Animation.BOUNCE
    });
}
showGoogleMaps();
</script>
	</div>
	</div>
	
	
	</body>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
		<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
		<script src="js/bootstrap.min.js" type="text/javascript"></script>
		

		
</html>